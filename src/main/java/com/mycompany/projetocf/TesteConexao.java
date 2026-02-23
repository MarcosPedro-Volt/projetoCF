/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
  import java.sql.Connection;
/**
 *
 * @author marcospedro
 */

  

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conn = Conexao.conectar()) {
            System.out.println("✅ Conectado ao MySQL com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Falha na conexão!");
            e.printStackTrace();
        }
    }
}

