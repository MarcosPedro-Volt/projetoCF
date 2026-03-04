/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCampoDataCompras extends JFrame {

    private JTextField txtDataInicial;
    private JTextField txtDataFinal;
    

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d/M/yyyy");

    public TelaCampoDataCompras() {

        setTitle("Relatório por Período");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel painelCentro = new JPanel(new GridLayout(3,2,10,10));
        
        
        

        txtDataInicial = new JTextField();
        txtDataFinal = new JTextField();
        

        JButton btnBuscar = new JButton("Ver grafico");

        btnBuscar.addActionListener(e -> abrirGrafico());

        painelCentro.add(new JLabel("Data Inicial (dia/mes/ano):"));
        painelCentro.add(txtDataInicial);

        painelCentro.add(new JLabel("Data Final (dia/mes/ano):"));
        painelCentro.add(txtDataFinal);
        painelCentro.add(btnBuscar);
        
        add(painelCentro, BorderLayout.CENTER);

        setVisible(true);
    }

    private void abrirGrafico() {
        try {

            LocalDate dataInicial =
                LocalDate.parse(txtDataInicial.getText(), formatter);

            LocalDate dataFinal =
                LocalDate.parse(txtDataFinal.getText(), formatter);

            

            
            new TelaGraficoCompras(dataInicial, dataFinal);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Data inválida! Use o formato dia/mes/ano");
        }
    }
}