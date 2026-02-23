/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author marcospedro
 */
public class Conexao {

    private static final String URL =
        "jdbc:mysql://localhost:3306/controle_financeiro";
    private static final String USER = "root";
    private static final String PASSWORD = "alice171290";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}