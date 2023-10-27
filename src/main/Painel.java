package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManeger;

/**
 *
 * @author user88
 */
public class Painel extends JPanel implements Runnable {

            //Opções do jogo
            public CheckDeColisao checkDeColisao = new CheckDeColisao(this);

            final int tamanhoDePixeisOriginal = 16; //16x16 pixeis
            public final int scala = 3;

            public final int tamanhoDePixeis = tamanhoDePixeisOriginal * scala;//48 x 48 pixeis

            public final int tamanhoMaximoDeColunas = 16;
            public final int tamanhoMaximoDeLinhas = 16;
            public final int larguraDoEcra = tamanhoDePixeis * tamanhoMaximoDeColunas; //768 pixels
            public final int alturaDoEcra = tamanhoDePixeis * tamanhoMaximoDeLinhas; //576 pixels            

            public final int tamanhoMaximoDeColunasDoMundo = 50;
            public final int tamanhoMaximoDeLinhasDoMundo = 50;

            //ratio 4x3
            public final int larguraDoMundo = tamanhoDePixeis * tamanhoMaximoDeColunasDoMundo; //768 pixels
            public final int alturaDoMundo = tamanhoDePixeis * tamanhoMaximoDeColunasDoMundo; //576 pixels            

            Teclado teclasDoJogo = new Teclado();
            Thread fluxoDoJogo;
            public Player jogador = new Player(this, teclasDoJogo);
            //FPS
            int fps = 60;

            TileManeger tile = new TileManeger(this);
            public SuperObject so[] = new SuperObject[10];
            public InsertorDeAssests insertorDeAssests  = new InsertorDeAssests(this);
            

            public Painel() {
                        setPreferredSize(new Dimension(larguraDoEcra, alturaDoEcra));
                        setBackground(Color.BLACK);
                        setDoubleBuffered(true);
                        addKeyListener(teclasDoJogo);
                        setFocusable(true);
            }

            public void setupgame(){
                        insertorDeAssests.setObject();
            }
            
            //Fluxo do Jogo
            public void comecaOFluxoDoJogo() {
                        fluxoDoJogo = new Thread(this);
                        fluxoDoJogo.start();
            }

            //Loop do jogo
            @Override
            public void run() {
                        double intervaloDeDesenho = 1_000_000_000 / fps; // 0.16666666666666 segundos
                        double proximoTempoDeDesenho = System.nanoTime() + intervaloDeDesenho;

                        while (fluxoDoJogo != null) {
//                                    System.out.println("Loop funcionando carai em "+fps+"fps");
                                    //Update da infomação do jogo
                                    update();

                                    //Desenhar o ecã com a informação actualizada
                                    repaint();

                                    //Ponto de interupção para continuar o jogo
                                    double tempoRestante = (proximoTempoDeDesenho - System.nanoTime()) / 1_000_000;
                                    if (tempoRestante < 0) {
                                                tempoRestante = 0;
                                    }
                                    try {
                                                Thread.sleep((long) tempoRestante);
                                    } catch (InterruptedException ex) {
                                                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    proximoTempoDeDesenho += intervaloDeDesenho;
                        }
            }

            public void update() {
                        // System.out.println("update");
                        jogador.update();
            }

            @Override
            public void paintComponent(Graphics g) {
                        //System.out.println("Pintando");
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;
                        //Tile
                        tile.desenha(g2);
                       
                        //Object
                        for (int i = 0; i < so.length; i++) {
                                    if(so[i] != null) so[i].draw(g2, this);
                        }
                        
                        //jogador
                        jogador.desenhar(g2);
                        g2.dispose();
            }

}
