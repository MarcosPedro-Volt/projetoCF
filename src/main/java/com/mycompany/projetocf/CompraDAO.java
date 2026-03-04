/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Timestamp;

/**
 *
 * @author marcospedro
 */
public class CompraDAO {
    
    public void registrarCompra(
            Date data,
            int cor,
            double quantidade
            
    ){
        String sqlCor = "SELECT nome FROM cor WHERE idcor=?";
        String sqlCompra = "INSERT INTO registro_compra"+
                "(data, cor, quantidade)VALUES(?,?,?)";
        
            try (Connection c = Conexao.conectar();
                PreparedStatement psCor = c.prepareStatement(sqlCor);
                PreparedStatement psCompra = c.prepareStatement(sqlCompra)){

            
            psCor.setInt(1, cor);
            ResultSet rs = psCor.executeQuery();

            if (rs.next()) {

                psCompra.setDate(1, data);
                psCompra.setInt(2, cor);
                psCompra.setDouble(3, quantidade);
                psCompra.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public List<Object[]> listar() {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            "SELECT c.idregistro_compra, c.data, cr.nome AS cor, c.quantidade " +
            
            "FROM registro_compra c " +
            "JOIN cor cr ON c.cor = cr.idcor " +
            "ORDER BY c.data DESC";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("idregistro_compra"),
                        rs.getDate("data"),
                        rs.getDouble("quantidade"),
                        rs.getString("cor"),
                        
                        
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;}
    
    
     public void excluir(int idregistro_compra) {

        String sql = "DELETE FROM registro_compra WHERE idregistro_compra = ?";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idregistro_compra);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
 public List<Object[]> relatorioComprasPeriodo(LocalDate dataInicial,LocalDate dataFinal) {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            
            "SELECT cr.nome AS cor, SUM(c.quantidade) AS quantidade " +
            "FROM registro_compra c " +
            "JOIN cor cr ON c.cor = cr.idcor " +
            "WHERE c.data >= ? AND c.data <= ? " +
            "GROUP BY cr.nome";
            

        try (Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setTimestamp(1,Timestamp.valueOf(dataInicial.atStartOfDay()));
                

            ps.setTimestamp(2,Timestamp.valueOf(dataFinal.atTime(23, 59, 59)));
                

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getDouble("quantidade"),
                    rs.getString("cor"),
                    
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
   

}
