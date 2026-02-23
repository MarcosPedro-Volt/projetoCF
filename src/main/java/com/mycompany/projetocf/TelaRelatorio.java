/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;


import javax.swing.*;
import java.awt.*;

public class TelaRelatorio extends JFrame {

    private JTextField txtMes, txtAno;
    private JLabel lblResultado;

    public TelaRelatorio() {

        setTitle("Lucro Mensal");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== Painel do formulário =====
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMes = new JTextField(5);
        txtAno = new JTextField(6);

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Mês:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtMes, gbc);

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        painelForm.add(new JLabel("Ano:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtAno, gbc);

        // ===== Resultado =====
        gbc.gridx = 0; gbc.gridy = 2;
        painelForm.add(new JLabel("Lucro:"), gbc);

        gbc.gridx = 1;
        lblResultado = new JLabel("R$ 0,00");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        painelForm.add(lblResultado, gbc);

        // ===== Botão =====
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> calcular());
        painelBotoes.add(btnCalcular);
        
        //botao do grafico
        JButton btnGrafico = new JButton("Ver Gráfico");
        btnGrafico.addActionListener(e -> abrirGrafico());
        painelBotoes.add(btnGrafico);

        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        txtMes.requestFocus();
        setVisible(true);
    }

    private void calcular() {
        try {
            int mes = Integer.parseInt(txtMes.getText());
            int ano = Integer.parseInt(txtAno.getText());

            if (mes < 1 || mes > 12) {
                JOptionPane.showMessageDialog(this, "Mês inválido.");
                return;
            }

            double lucro = new VendaDAO().lucroMensal(mes, ano);

            lblResultado.setText(
                String.format("R$ %.2f", lucro)
            );

            lblResultado.setForeground(
                lucro >= 0 ? new Color(0, 130, 0) : Color.RED
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Informe mês e ano válidos!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void abrirGrafico() {
    try {
        int mes = Integer.parseInt(txtMes.getText());
        int ano = Integer.parseInt(txtAno.getText());

        new TelaGraficoMateriais(mes, ano);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            this,
            "Informe mês e ano válidos!"
        );
    }
}
}
