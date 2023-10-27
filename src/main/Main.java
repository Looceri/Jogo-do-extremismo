
package main;

import java.awt.Color;
import javax.swing.JFrame;

public class Main {

            public static void main(String[] args) {
                        JFrame janela = new JFrame();
                        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        janela.setResizable(false);
                        janela.setTitle("As aventuras de um pequeno extremista");
                        janela.getContentPane().setBackground(Color.BLACK);

                        Painel painelDoJogo = new Painel();

                        janela.add(painelDoJogo);

                        janela.pack();

                        janela.setLocationRelativeTo(null);
                        janela.setVisible(true);
                        painelDoJogo.setupgame();
                        painelDoJogo.comecaOFluxoDoJogo();
            }

}
