package org.example.apresentacao;

import org.example.scenario.Scenario;
import org.example.individual.Individual;
import org.example.individual.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.*;


public class VisualizarCenario extends Frame {

    private Scenario scenario;
    private Population population;
    private Individual exiba;
    private int indice = 0;
    private JLabel indiceTxt = new JLabel();
    private JLabel custoTxt = new JLabel();

    public VisualizarCenario(Scenario scenario, Population population) {

        super("Visualizando cenario");

        setSize(1000, 1000);

        setVisible(true);

        if(population.size() != 0)
            this.exiba = population.get(0);

        this.scenario = scenario;
        this.population = population;

        indiceTxt.setText(String.format("(%d)",this.indice));

        JPanel barraSuperior = new JPanel();

        barraSuperior.setLayout(new GridLayout(1, 2, 1, 2));

        barraSuperior.add(custoTxt);
        barraSuperior.add(indiceTxt);

        add(barraSuperior, BorderLayout.NORTH);


        JButton proximo = new JButton("proximo");
        JButton anterior = new JButton("anterior");

        JPanel panelInferior = new JPanel();

        panelInferior.setLayout(new GridLayout(1, 2, 1, 2));

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_LEFT){

                    desenhaAnterior(population);
                }
                else if(key == KeyEvent.VK_RIGHT){
                    desenhaProximo(population);
                }
            }
        });

        anterior.addActionListener(a ->{

            desenhaAnterior(population);

        });

        proximo.addActionListener(a ->{

            desenhaProximo(population);
        });

        panelInferior.add(anterior);
        panelInferior.add(proximo);

        this.add(panelInferior, BorderLayout.SOUTH);

        AreaDesenho area = new AreaDesenho();

        this.add(area, BorderLayout.CENTER);

      //  this.add(caminhos, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void desenhaProximo(Population population) {
        ++indice;
        if(indice >= population.size()){
            indice = 0;
        }

        exiba = population.get(indice);
        this.indiceTxt.setText(String.format("(%d)",indice));
        this.custoTxt.setText(String.format("(%f)", population.get(indice).getCost()));
        repaint();
    }

    private void desenhaAnterior(Population population) {
        --indice;
        if(indice < 0){
            indice = population.size()-1;
        }

        exiba = population.get(indice);
        this.indiceTxt.setText(String.format("(%d)",indice));
        this.custoTxt.setText(String.format("(%f)", population.get(indice).getCost()));
        repaint();
    }

    public class AreaDesenho extends JPanel{

        private final int pontoInicialDesenhoX = 5;
        private final int pontoInicialDesenhoY = 5;

        private final int larguraCelula = 50;
        private final int alturaCelula = 50;

        private final int posicaoStringX = 10;
        private final int posicaoStringY = 25;

        private void desenharCaminhos(Graphics g) {

            if (population == null || population.size() < 1)
                return;

            var individuo = exiba;

            Point pInicial = individuo.getPosition(0);

            Point pFinal;

            for (int ponto = 1; ponto < individuo.size(); ++ponto) {

                pFinal = individuo.getPosition(ponto);

                var xPost = pInicial.x * larguraCelula + pontoInicialDesenhoX + larguraCelula/2;
                var yPost = pInicial.y * alturaCelula + pontoInicialDesenhoY + alturaCelula/2;

                var xPosFinal = pFinal.x * larguraCelula + pontoInicialDesenhoX + larguraCelula/2;;
                var yPosFinal = pFinal.y * alturaCelula + pontoInicialDesenhoY  + alturaCelula/2;;

                g.setColor(Color.red);
                g.drawLine(xPost, yPost, xPosFinal, yPosFinal);
                pInicial = pFinal;
            }
        }

        public void paint(Graphics g) {

            g.setColor(Color.black);
            for (int altura = 0; altura < scenario.getMaxHeight(); ++altura) {
                for (int largura = 0; largura < scenario.getMaxLength(); ++largura) {

                    int xPos = alturaCelula * (altura) + pontoInicialDesenhoX;
                    int yPos = larguraCelula * (largura) + pontoInicialDesenhoY;

                    if(scenario.getCost(altura, largura) == Float.POSITIVE_INFINITY){
                        g.fillRect(xPos, yPos, larguraCelula, alturaCelula);
                    }
                    else{

                        g.drawRect(xPos, yPos, larguraCelula, alturaCelula);
                        g.drawString(String.format("%.2f", scenario.getCost(altura, largura)),
                                xPos + posicaoStringX, yPos + posicaoStringY);
                    }
                }
            }

       /*
       g.drawLine(50+25, 50+25, 100, 100);
       g.drawLine(100, 100, 125, 125);
       g.drawLine(125, 125, 150, 125);*/

            desenharCaminhos(g);


        }
    }

}

