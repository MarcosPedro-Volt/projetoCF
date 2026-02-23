/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaGerenciarVendas extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel lblLucroTotal;

    public TelaGerenciarVendas() {

        setTitle("Gerenciar Vendas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

      
        modelo = new DefaultTableModel(
            new Object[]{"ID", "Data", "Cliente", "Material", "Quantidade", "Total", "Lucro"}, 0
        );

        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

     
        lblLucroTotal = new JLabel();
        lblLucroTotal.setFont(new Font("Arial", Font.BOLD, 14));

       
        JPanel painelSul = new JPanel(new BorderLayout(10, 10));
        painelSul.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnExcluir = new JButton("Excluir Venda");
        btnExcluir.addActionListener(e -> excluir());

        painelSul.add(btnExcluir, BorderLayout.WEST);
        painelSul.add(lblLucroTotal, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(painelSul, BorderLayout.SOUTH);

        carregar();

        setVisible(true);
    }

    private void carregar() {
        modelo.setRowCount(0);

        for (Object[] v : new VendaDAO().listar()) {
            modelo.addRow(v);
        }

        atualizarLucroTotal();
    }

    private void excluir() {

        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma venda.");
            return;
        }

        int id = (int) modelo.getValueAt(linha, 0);

        int op = JOptionPane.showConfirmDialog(
            this,
            "Excluir venda?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            new VendaDAO().excluir(id);
            carregar();
        }
    }

    private void atualizarLucroTotal() {

        double lucro = new VendaDAO().lucroTotal();

        lblLucroTotal.setText(
            String.format("Lucro total: R$ %.2f", lucro)
        );

        lblLucroTotal.setForeground(
            lucro >= 0 ? new Color(0, 130, 0) : Color.RED
        );
    }
}
