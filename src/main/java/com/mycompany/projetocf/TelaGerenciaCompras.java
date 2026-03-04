/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author marcospedro
 */
public class TelaGerenciaCompras extends JFrame {
   
    
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel lblLucroTotal;

    public TelaGerenciaCompras() {

        setTitle("Gerenciar Compras");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

      
        modelo = new DefaultTableModel(
            new Object[]{"ID", "Data", "Quantidade", "Cor"}, 0
        );

        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

     
        lblLucroTotal = new JLabel();
        lblLucroTotal.setFont(new Font("Arial", Font.BOLD, 14));

       
        JPanel painelSul = new JPanel(new BorderLayout(10, 10));
        painelSul.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnExcluir = new JButton("Excluir Compra");
        btnExcluir.addActionListener(e -> excluir());
        
        JButton btnGrafico = new JButton("Ver Gráfico");
        btnGrafico.addActionListener(e -> new TelaCampoDataCompras());
        
        

        painelSul.add(btnExcluir, BorderLayout.WEST);
        painelSul.add(btnGrafico, BorderLayout.CENTER);
        painelSul.add(lblLucroTotal, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(painelSul, BorderLayout.SOUTH);

        carregar();

        setVisible(true);
    }
    
    private void carregar() {
        modelo.setRowCount(0);

        for (Object[] c : new CompraDAO().listar()) {
            modelo.addRow(c);
        }       
    }
    

    private void excluir() {

        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma Compra.");
            return;
        }

        int idregistro_compra = (int) modelo.getValueAt(linha, 0);

        int op = JOptionPane.showConfirmDialog(
            this,
            "Excluir Compra?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            new CompraDAO().excluir(idregistro_compra);
            carregar();
        }
    }
    
   

    
}
