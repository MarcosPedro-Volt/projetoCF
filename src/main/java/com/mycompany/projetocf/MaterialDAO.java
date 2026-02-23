/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author marcospedro
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public void cadastrar(String nome, double custo, double venda) {
        String sql = "INSERT INTO material (nome, preco_custo, preco_venda) VALUES (?, ?, ?)";
        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setDouble(2, custo);
            ps.setDouble(3, venda);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> listar() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM material";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_custo"),
                        rs.getDouble("preco_venda")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(int id, String nome, double custo, double venda) {
        String sql = "UPDATE material SET nome=?, preco_custo=?, preco_venda=? WHERE id=?";
        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setDouble(2, custo);
            ps.setDouble(3, venda);
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM material WHERE id=?";
        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
