package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.Painel;

public class SuperObject {

    public BufferedImage imagem;
    public String nome;
    public boolean colisao = false;
    public int XNoMundo, YNoMundo;
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);

    public int areaSolidaDefaultX = 0;
    public int areaSolidaDefaultY = 0;

    public void draw(Graphics2D gd, Painel p) {
        int screenX = XNoMundo - p.jogador.worldX + p.jogador.screenX;
        int screenY = YNoMundo - p.jogador.worldY + p.jogador.screenY;
        if (XNoMundo + p.tamanhoDePixeis > p.jogador.worldX - p.jogador.screenX
                && XNoMundo - p.tamanhoDePixeis < p.jogador.worldX + p.jogador.screenX
                && YNoMundo + p.tamanhoDePixeis > p.jogador.worldY - p.jogador.screenY
                && YNoMundo - p.tamanhoDePixeis < p.jogador.worldY + p.jogador.screenY)
        {
            gd.drawImage(imagem, screenX, screenY, p.tamanhoDePixeis, p.tamanhoDePixeis, null);
        }
    }

    public boolean isColisao() {
        return colisao;
    }

    public void setColisao(boolean colisao) {
        this.colisao = colisao;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        
        try
        {
            this.imagem =  ImageIO.read(getClass().getResourceAsStream(imagem));
        } catch (IOException ex)
        {
            Logger.getLogger(SuperObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
