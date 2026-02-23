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

public class TelaMaterial extends JFrame {

    private JTextField txtNome, txtCusto, txtVenda;

    public TelaMaterial() {

        setTitle("Cadastro de Material");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

       
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNome = new JTextField(20);
        txtCusto = new JTextField(10);
        txtVenda = new JTextField(10);

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtNome, gbc);

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        painelForm.add(new JLabel("Preço de Custo:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtCusto, gbc);

        // Linha 3
        gbc.gridx = 0; gbc.gridy = 2;
        painelForm.add(new JLabel("Preço de Venda:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtVenda, gbc);

     
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        painelBotoes.add(btnSalvar);

        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        txtNome.requestFocus();
        setVisible(true);
    }

    private void salvar() {
        try {
            String nome = txtNome.getText();
            double custo = Double.parseDouble(txtCusto.getText());
            double venda = Double.parseDouble(txtVenda.getText());

            new MaterialDAO().cadastrar(nome, custo, venda);

            JOptionPane.showMessageDialog(this, "Material cadastrado!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Preços devem ser numéricos!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
