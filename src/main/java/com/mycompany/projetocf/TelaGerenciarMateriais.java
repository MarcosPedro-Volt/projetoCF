/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;

/**
 *
 * @author marcospedro
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaGerenciarMateriais extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaGerenciarMateriais() {
        setTitle("Gerenciar Materiais");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Custo", "Venda"}, 0);

        tabela = new JTable(modelo);
        carregar();

        JPanel botoes = new JPanel();
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnEditar.addActionListener(e -> editar());
        btnExcluir.addActionListener(e -> excluir());

        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregar() {
        modelo.setRowCount(0);
        for (Object[] m : new MaterialDAO().listar()) {
            modelo.addRow(m);
        }
    }

    private void editar() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;

        int id = (int) modelo.getValueAt(linha, 0);

        String nome = JOptionPane.showInputDialog(
                "Nome:", modelo.getValueAt(linha, 1));
        double custo = Double.parseDouble(
                JOptionPane.showInputDialog("Custo:", modelo.getValueAt(linha, 2)));
        double venda = Double.parseDouble(
                JOptionPane.showInputDialog("Venda:", modelo.getValueAt(linha, 3)));

        new MaterialDAO().atualizar(id, nome, custo, venda);
        carregar();
    }

    private void excluir() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;

        int id = (int) modelo.getValueAt(linha, 0);

        if (JOptionPane.showConfirmDialog(
                this, "Excluir material?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            new MaterialDAO().excluir(id);
            carregar();
        }
    }
}
