/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;

/**
 *
 * @author marcospedro
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    
    public void registrarVenda(
            Date data,
            String cliente,
            int materialId,
            double quantidadeKg
    ) {

        String sqlMaterial =
            "SELECT preco_custo, preco_venda FROM material WHERE id = ?";

        String sqlVenda =
            "INSERT INTO venda " +
            "(data_venda, cliente, material_id, preco_custo, preco_venda, " +
            "quantidade_kg, total_venda, lucro) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Conexao.conectar();
             PreparedStatement psMat = c.prepareStatement(sqlMaterial);
             PreparedStatement psVenda = c.prepareStatement(sqlVenda)) {

            
            psMat.setInt(1, materialId);
            ResultSet rs = psMat.executeQuery();

            if (rs.next()) {

                double precoCusto = rs.getDouble("preco_custo");
                double precoVenda = rs.getDouble("preco_venda");

                double totalVenda = quantidadeKg * precoVenda;
                double lucro = (precoVenda - precoCusto) * quantidadeKg;

             
                psVenda.setDate(1, data);
                psVenda.setString(2, cliente);
                psVenda.setInt(3, materialId);
                psVenda.setDouble(4, precoCusto);
                psVenda.setDouble(5, precoVenda);
                psVenda.setDouble(6, quantidadeKg);
                psVenda.setDouble(7, totalVenda);
                psVenda.setDouble(8, lucro);

                psVenda.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public List<Object[]> listar() {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            "SELECT v.id, v.data_venda, v.cliente, m.nome AS material, " +
            "v.quantidade_kg, v.total_venda, v.lucro " +
            "FROM venda v " +
            "JOIN material m ON v.material_id = m.id " +
            "ORDER BY v.data_venda DESC";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getDate("data_venda"),
                        rs.getString("cliente"),
                        rs.getString("material"),
                        rs.getDouble("quantidade_kg"),
                        rs.getDouble("total_venda"),
                        rs.getDouble("lucro")
                        
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

  
    public void excluir(int id) {

        String sql = "DELETE FROM venda WHERE id = ?";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double lucroMensal(int mes, int ano) {

    double total = 0;

    String sql =
        "SELECT SUM(lucro) AS total " +
        "FROM venda " +
        "WHERE MONTH(data_venda) = ? " +
        "AND YEAR(data_venda) = ?";

    try (Connection c = Conexao.conectar();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, mes);
        ps.setInt(2, ano);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble("total");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
    }
    
    public double lucroTotal() {

    double total = 0;

    String sql = "SELECT SUM(lucro) AS total FROM venda";

    try (Connection c = Conexao.conectar();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            total = rs.getDouble("total");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}
    public List<Object[]> relatorioMateriaisMensal(int mes, int ano) {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            "SELECT m.nome, " +
            "SUM(v.quantidade_kg) as quantidade, " +
            "SUM(v.total_venda) as total_custo, " +
            "SUM(v.lucro) as lucro " +
            "FROM venda v " +
            "JOIN material m ON v.material_id = m.id " +
            "WHERE MONTH(v.data_venda) = ? AND YEAR(v.data_venda) = ? " +
            "GROUP BY m.nome";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, mes);
            ps.setInt(2, ano);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("nome"),
                    rs.getDouble("quantidade"),
                    rs.getDouble("total_custo"),
                    rs.getDouble("lucro")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}
