package main;

import java.awt.event.*;

public class Teclado implements KeyListener {

            public boolean cima, esquerda , direita, baixo, speedUp, coodernadas;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode()== KeyEvent.VK_W) {
                                    cima = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_S) {
                                    baixo = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_A) {
                                    esquerda = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_D) {
                                    direita = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                                    speedUp = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                    System.exit(0);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_F1) {
                                    coodernadas=true;
                        }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_W) {
                                    cima = false;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_S) {
                                    baixo = false;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_A) {
                                    esquerda = false;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_D) {
                                    direita = false;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                                    speedUp = false;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_F1) {
                                    coodernadas=false;
                        }
            }
}
