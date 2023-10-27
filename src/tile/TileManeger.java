package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.imageio.ImageIO;
import main.Painel;

public class TileManeger {

            Painel janela;
            public Tile[] chao;
            int nrDeTiles;
            public int cordenadasDoMapa[][];
            int nr = 0;

            public TileManeger(Painel pnl) {
                        janela = pnl;
                        nrDeTiles = janela.tamanhoMaximoDeColunasDoMundo * janela.tamanhoMaximoDeLinhasDoMundo;
                        chao = new Tile[nrDeTiles];
                        System.out.println(chao.length);
                        cordenadasDoMapa = new int[janela.tamanhoMaximoDeLinhasDoMundo][janela.tamanhoMaximoDeColunasDoMundo];
                        getTileImage();
            }

            public void getTileImage() {
                        try {
                                    carregarOMapa("/mapas/mapa50x50.txt");
                                    nr = 0;
                                    for (int k = 0; k < janela.tamanhoMaximoDeLinhasDoMundo; k++) {
                                                for (int j = 0; j < janela.tamanhoMaximoDeColunasDoMundo; j++) {
                                                            chao[nr] = new Tile();
                                                            switch (cordenadasDoMapa[k][j]) {
                                                                        case 0:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/grama.png"));
                                                                                    break;
                                                                        case 1:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/paredeDEPedra.png"));
                                                                                    break;
                                                                        case 2:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/arvore.png"));
                                                                                    chao[nr].collisao = true;
                                                                                    break;
                                                                        case 3:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/areia.png"));
                                                                                    break;
                                                                        case 4:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/terra.png"));
                                                                                    break;
                                                                        case 5:
                                                                                    chao[nr].imagem = ImageIO.read(getClass().getResourceAsStream("/enviroment/agua.png"));
                                                                                    chao[nr].collisao = true;
                                                                                    break;
                                                                        default:
                                                                                    throw new AssertionError();
                                                            }
                                                            cordenadasDoMapa[k][j] = nr;
                                                            nr++;
                                                }
                                    }
                                    System.out.println(nr);
                        } catch (IOException e) {
                                    e.printStackTrace();
                        }
            }

            public void carregarOMapa(String mapaEmUso) {
                        try {
                                    InputStream in = getClass().getResourceAsStream(mapaEmUso);
                                    BufferedReader leitor = new BufferedReader(new InputStreamReader(in));
                                    for (int i = 0; i < janela.tamanhoMaximoDeLinhasDoMundo; i++) {
                                                String linha = leitor.readLine();
                                                String numeros[] = linha.split(" ");
                                                for (int j = 0; j < janela.tamanhoMaximoDeColunasDoMundo; j++) {
                                                            cordenadasDoMapa[i][j] = Integer.parseInt(numeros[j]);
                                                }
                                                System.out.println(Arrays.toString(numeros));
                                    }

                                    leitor.close();
                        } catch (IOException | NumberFormatException e) {
                                    e.printStackTrace();
                        }
            }

            public void desenha(Graphics2D g2) {
                        nr = 0;
                        for (int i = 0; i < janela.tamanhoMaximoDeLinhasDoMundo; i++) {
                                    for (int j = 0; j < janela.tamanhoMaximoDeColunasDoMundo; j++) {
                                                int worldX = j * janela.tamanhoDePixeis;
                                                int worldY = i * janela.tamanhoDePixeis;
                                                int screenX = worldX - janela.jogador.worldX + janela.jogador.screenX;
                                                int screenY = worldY - janela.jogador.worldY + janela.jogador.screenY;
                                                if (worldX + janela.tamanhoDePixeis > janela.jogador.worldX - janela.jogador.screenX
                                                        && worldX - janela.tamanhoDePixeis < janela.jogador.worldX + janela.jogador.screenX
                                                        && worldY + janela.tamanhoDePixeis > janela.jogador.worldY - janela.jogador.screenY
                                                        && worldY - janela.tamanhoDePixeis < janela.jogador.worldY + janela.jogador.screenY) {
                                                            g2.drawImage(chao[nr].imagem, screenX, screenY, janela.tamanhoDePixeis, janela.tamanhoDePixeis, null);
                                                }
                                                nr++;
                                    }
                        }
            }

}
