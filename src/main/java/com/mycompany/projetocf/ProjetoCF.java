/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetocf;

/**
 *
 * @author marcospedro
 */
import javax.swing.SwingUtilities;


public class ProjetoCF {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal();
        });

    }
}
