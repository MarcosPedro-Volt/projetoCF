/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author marcospedro
 */
public class TelaRegistrarCompra extends JFrame{
    
    private JComboBox<ItemCores> cbCor;
    private JTextField txtQuantidade;
    
    public TelaRegistrarCompra() {

        setTitle("Registro de Compra");
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

        cbCor = new JComboBox<>();
        txtQuantidade = new JTextField(10);
        

        carregarCores();

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Selecione a Cor:"), gbc);

        gbc.gridx = 1;
        painelForm.add(cbCor, gbc);

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        painelForm.add(new JLabel("Quantidade:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtQuantidade, gbc);

      

        // ===== Botão =====
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Registrar Compra");
        btnSalvar.addActionListener(e -> registrar());
        painelBotoes.add(btnSalvar);

        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        txtQuantidade.requestFocus();
        setVisible(true);
    }
    
    private void carregarCores() {
        try (Connection c = Conexao.conectar();
             PreparedStatement ps =
                 c.prepareStatement("SELECT idcor, nome FROM cor");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cbCor.addItem(
                    new ItemCores(
                        rs.getInt("idcor"),
                        rs.getString("nome")
                    )
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar cores",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
    
    private void registrar() {
        try {
            ItemCores cores =
                (ItemCores) cbCor.getSelectedItem();

            if (cores == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma cor.");
                return;
            }

            double quantidade =
                Double.parseDouble(txtQuantidade.getText());

            new CompraDAO().registrarCompra(
                new java.sql.Date(new Date().getTime()),
              
                cores.getId(),
                quantidade
                
            );

            JOptionPane.showMessageDialog(this, "Compra registrada!");
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
