package com.mycompany.projetocf;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author marcospedro
 */
public class TelaCadastroCor extends JFrame {
    
    private JTextField txtNome;
    
    public TelaCadastroCor(){
    
        setTitle("Cadastro de cor");
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
        
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        painelForm.add(txtNome, gbc);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        painelBotoes.add(btnSalvar);

        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        txtNome.requestFocus();
        setVisible(true);
    }
    
    
    private void salvar(){
        try {
            String nome = txtNome.getText();
            
            new CadastroCores().cadastrarCor(nome);
            
            JOptionPane.showMessageDialog(this, "Cor cadastrada!");
            dispose();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
