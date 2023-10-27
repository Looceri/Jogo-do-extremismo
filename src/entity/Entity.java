
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

            public int worldX, worldY, velocidade;
            public int hp, defence, attack;
            
            public BufferedImage cima, baixo,esquerda,direita;
            public String direcao = new String();
            public String direcaoLast = new String();
            
            public Rectangle areaSolida;
            public int areaSolidaDefaultX;
            public int areaSolidaDefaultY;
            public boolean colisaoLigada = false;
            
}
