/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;

/**
 *
 * @author marcospedro
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;



import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaGraficoMateriais extends JFrame {

    public TelaGraficoMateriais(int mes, int ano) {

        setTitle("Materiais Vendidos no Mês");
        setSize(800, 600);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset =
            new DefaultCategoryDataset();

        List<Object[]> dados =
            new VendaDAO().relatorioMateriaisMensal(mes, ano);

        for (Object[] d : dados) {

            String nome = (String) d[0];
            double quantidade = (double) d[1];
            double custo = (double) d[2];
            double lucro = (double) d[3];

            dataset.addValue(quantidade,
                "Quantidade", nome);

            dataset.addValue(custo,
                "Venda", nome);

            dataset.addValue(lucro,
                "Lucro", nome);
        }

        JFreeChart chart =
            ChartFactory.createBarChart(
                "Vendas por Material",
                "Material",
                "Valores",
                dataset
            );

        ChartPanel painel = new ChartPanel(chart);
        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }
}