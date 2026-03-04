/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetocf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author marcospedro
 */
public class TelaGraficoCompras extends JFrame{
    
    public TelaGraficoCompras(LocalDate dataInicial, LocalDate dataFinal) {

        setTitle("Quantidade comprada por cores");
        setSize(800, 600);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset =
            new DefaultCategoryDataset();

        List<Object[]> dados =
            new CompraDAO().relatorioComprasPeriodo(dataInicial, dataFinal);

        for (Object[] d : dados) {

            
            double quantidade = (double) d[0];
            String cor = (String) d[1];

            dataset.addValue(quantidade,
                "Quantidade", cor);

          
        }

        JFreeChart chart =
            ChartFactory.createBarChart(
                "Compras por Material",
                "Cores",
                "Quantidade",
                dataset
            );

        ChartPanel painel = new ChartPanel(chart);
        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }
}
