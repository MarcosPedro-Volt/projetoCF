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
import java.awt.*;
import java.sql.*;
import java.util.Date;

public class TelaVenda extends JFrame {

    private JComboBox<ItemMaterial> cbMaterial;
    private JTextField txtCliente, txtQuantidade;

    public TelaVenda() {

        setTitle("Registro de Venda");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== Painel do formulário =====
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCliente = new JTextField(20);
        txtQuantidade = new JTextField(10);
        cbMaterial = new JComboBox<>();

        carregarMateriais();

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Cliente:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtCliente, gbc);

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        painelForm.add(new JLabel("Material:"), gbc);

        gbc.gridx = 1;
        painelForm.add(cbMaterial, gbc);

        // Linha 3
        gbc.gridx = 0; gbc.gridy = 2;
        painelForm.add(new JLabel("Quantidade (Kg):"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtQuantidade, gbc);

        // ===== Botão =====
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Registrar Venda");
        btnSalvar.addActionListener(e -> registrar());
        painelBotoes.add(btnSalvar);

        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        txtCliente.requestFocus();
        setVisible(true);
    }

    private void carregarMateriais() {
        try (Connection c = Conexao.conectar();
             PreparedStatement ps =
                 c.prepareStatement("SELECT id, nome FROM material");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cbMaterial.addItem(
                    new ItemMaterial(
                        rs.getInt("id"),
                        rs.getString("nome")
                    )
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar materiais",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void registrar() {
        try {
            ItemMaterial material =
                (ItemMaterial) cbMaterial.getSelectedItem();

            if (material == null) {
                JOptionPane.showMessageDialog(this, "Selecione um material.");
                return;
            }

            double quantidade =
                Double.parseDouble(txtQuantidade.getText());

            new VendaDAO().registrarVenda(
                new java.sql.Date(new Date().getTime()),
                txtCliente.getText(),
                material.getId(),
                quantidade
            );

            JOptionPane.showMessageDialog(this, "Venda registrada!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Quantidade inválida!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
