/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import javax.imageio.ImageIO;

/**
 *
 * @author user88
 */
public class Sapatilhas extends SuperObject {

    public Sapatilhas() {
        colisao = true;
        nome = "sapatilhas";
        try
        {
            imagem = ImageIO.read(getClass().getResourceAsStream("/sprites/objectos/sapatilha.png"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
