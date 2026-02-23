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

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        setTitle("Controle Financeiro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarMenu();

       
        JLabel lbl = new JLabel(
            "Sistema de Controle Financeiro",
            SwingConstants.CENTER
        );
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        add(lbl, BorderLayout.CENTER);

        setVisible(true);
    }

    private void criarMenu() {

        JMenuBar menuBar = new JMenuBar();

    
        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemMaterial = new JMenuItem("Material");

        itemMaterial.addActionListener(e -> new TelaMaterial());

        menuCadastro.add(itemMaterial);

  
        JMenu menuVendas = new JMenu("Vendas");

        JMenuItem itemNovaVenda = new JMenuItem("Registrar Venda");
        JMenuItem itemGerenciarVendas = new JMenuItem("Gerenciar Vendas");

        itemNovaVenda.addActionListener(e -> new TelaVenda());
        itemGerenciarVendas.addActionListener(e -> new TelaGerenciarVendas());

        menuVendas.add(itemNovaVenda);
        menuVendas.add(itemGerenciarVendas);

       
        JMenu menuRelatorio = new JMenu("Relatórios");
        JMenuItem itemLucroMensal = new JMenuItem("Lucro Mensal");

        itemLucroMensal.addActionListener(e -> new TelaRelatorio());

        menuRelatorio.add(itemLucroMensal);

      
        JMenu menuGerenciar = new JMenu("Gerenciar");
        JMenuItem itemMateriais = new JMenuItem("Materiais");

        itemMateriais.addActionListener(e -> new TelaGerenciarMateriais());

        menuGerenciar.add(itemMateriais);

        
        menuBar.add(menuCadastro);
        menuBar.add(menuVendas);
        menuBar.add(menuRelatorio);
        menuBar.add(menuGerenciar);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipal::new);
    }
}
