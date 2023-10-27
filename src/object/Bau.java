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
public class Bau extends SuperObject {

    public Bau() {
        colisao = true;
        nome = "bau";
        try
        {
            imagem = ImageIO.read(getClass().getResourceAsStream("/sprites/objectos/bau.png"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
