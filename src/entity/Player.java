package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Painel;
import main.Teclado;

public class Player extends Entity {

    Painel janela;
    Teclado tec;
    public final int screenX;
    public final int screenY;
    int keys = 0;

    public Player(Painel janela, Teclado tec) {
        this.screenY = (janela.alturaDoEcra - janela.tamanhoDePixeis) / 2;
        this.screenX = (janela.larguraDoEcra - janela.tamanhoDePixeis) / 2;
        this.janela = janela;
        this.tec = tec;

        areaSolida = new Rectangle(4 * janela.scala, 9 * janela.scala, 5 * janela.scala, 6 * janela.scala);
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
        valoresDefults();
        getPlayerImage();
    }

    public void valoresDefults() {
        worldX = janela.tamanhoDePixeis * 16;
        worldY = janela.tamanhoDePixeis * 16;
        velocidade = 4;
    }

    public void update() {

        if (tec.cima == true)
        {
            direcao = "cima";
        } else if (tec.baixo == true)
        {
            direcao = "baixo";
        } else if (tec.esquerda == true)
        {
            direcao = "esquerda";
        } else if (tec.direita == true)
        {
            direcao = "direita";
        } else
        {
            direcaoLast = direcao;
            direcao = "";
        }
        if (tec.speedUp == true)
        {
            if (velocidade != 14)
            {
                velocidade += 5;
                System.out.println(velocidade);
            }
        }

        // checaodor de colisao
        colisaoLigada = false;
        //Tile
        janela.checkDeColisao.checkTile(this);
        //Objecto
        int objIndex = janela.checkDeColisao.checkObject(this, true);
        tratamentoDeObjecto(objIndex);
        if (colisaoLigada == false)
        {
            switch (direcao)
            {
                case "cima":
                    worldY -= velocidade;
                    break;
                case "baixo":
                    worldY += velocidade;
                    break;
                case "esquerda":
                    worldX -= velocidade;
                    break;
                case "direita":
                    worldX += velocidade;
                    break;
            }
        }

        if (tec.speedUp == false)
        {
            if (velocidade == 14)
            {
                velocidade = 4;
                System.out.println(velocidade);
            }
        }
        if (tec.coodernadas == true)
        {
            System.out.println("X: " + janela.jogador.worldX + " e Y: " + janela.jogador.worldY);
        }
        if (direcao == "")
        {
            direcao = direcaoLast;
        }
    }

    public void tratamentoDeObjecto(int i) {
        if (i != 999)
        {
            switch (janela.so[i].nome)
            {
                case "chave":
                    janela.so[i] = null;
                    keys++;
                    break;
                case "sapatilhas":
                    janela.so[i] = null;
                    velocidade+=2;
                    break;
                case "porta":
                    if (keys > 0)
                    {
                        janela.so[i].setColisao(false);
                        janela.so[i].setImagem("/sprites/objectos/porta_aberta.png");
                        keys--;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void getPlayerImage() {
        try
        {
            cima = ImageIO.read(getClass().getResourceAsStream("/sprites/3.png"));
            baixo = ImageIO.read(getClass().getResourceAsStream("/sprites/2.png"));
            esquerda = ImageIO.read(getClass().getResourceAsStream("/sprites/2.png"));
            direita = ImageIO.read(getClass().getResourceAsStream("/sprites/1.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void desenhar(Graphics g2) {
        BufferedImage imagem = null;
        switch (direcao)
        {

            case "cima":
                imagem = cima;
                break;

            case "baixo":
                imagem = baixo;
                break;

            case "esquerda":
                imagem = esquerda;
                break;

            case "direita":
                imagem = direita;
                break;

            default:
                imagem = direita;
                break;

        }
        g2.drawImage(imagem, screenX, screenY, janela.tamanhoDePixeis, janela.tamanhoDePixeis, null);
    }

}
