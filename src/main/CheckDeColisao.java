/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author user88
 */
public class CheckDeColisao {

    Painel janela;

    public CheckDeColisao(Painel pnl) {
        janela = pnl;
    }

    public void checkTile(Entity entity) {
        int xEsquerdoDaEntidadeDoMundo = entity.worldX + entity.areaSolida.x;
        int xDireitoDaEntidadeDoMundo = entity.worldX + entity.areaSolida.x + entity.areaSolida.width;
        int yTopoDaEntidadeDoMundo = entity.worldY + entity.areaSolida.y;
        int yChaoDaEntidadeDoMundo = entity.worldY + entity.areaSolida.y + entity.areaSolida.height;

        int colunaEsquerda = xEsquerdoDaEntidadeDoMundo / janela.tamanhoDePixeis;
        int colunaDireita = xDireitoDaEntidadeDoMundo / janela.tamanhoDePixeis;
        int linhaTopo = yTopoDaEntidadeDoMundo / janela.tamanhoDePixeis;
        int linhaChao = yChaoDaEntidadeDoMundo / janela.tamanhoDePixeis;

        int tile1, tile2;

        switch (entity.direcao)
        {
            case "cima":
                linhaTopo = (yTopoDaEntidadeDoMundo - entity.velocidade) / janela.tamanhoDePixeis;
                tile1 = janela.tile.cordenadasDoMapa[linhaTopo][colunaEsquerda];
                tile2 = janela.tile.cordenadasDoMapa[linhaTopo][colunaDireita];
                if (janela.tile.chao[tile1].collisao == true || janela.tile.chao[tile2].collisao == true)
                {
                    entity.colisaoLigada = true;
                }
                break;
            case "baixo":
                linhaChao = (yChaoDaEntidadeDoMundo + entity.velocidade) / janela.tamanhoDePixeis;
                tile1 = janela.tile.cordenadasDoMapa[linhaChao][colunaEsquerda];
                tile2 = janela.tile.cordenadasDoMapa[linhaChao][colunaDireita];
                if (janela.tile.chao[tile1].collisao == true || janela.tile.chao[tile2].collisao == true)
                {
                    entity.colisaoLigada = true;
                }
                break;
            case "esquerda":
                colunaEsquerda = (xEsquerdoDaEntidadeDoMundo - entity.velocidade) / janela.tamanhoDePixeis;
                tile1 = janela.tile.cordenadasDoMapa[linhaTopo][colunaEsquerda];
                tile2 = janela.tile.cordenadasDoMapa[linhaChao][colunaEsquerda];
                if (janela.tile.chao[tile1].collisao == true || janela.tile.chao[tile2].collisao == true)
                {
                    entity.colisaoLigada = true;
                }
                break;
            case "direita":
                colunaDireita = (xDireitoDaEntidadeDoMundo + entity.velocidade) / janela.tamanhoDePixeis;
                tile1 = janela.tile.cordenadasDoMapa[linhaTopo][colunaDireita];
                tile2 = janela.tile.cordenadasDoMapa[linhaChao][colunaDireita];
                if (janela.tile.chao[tile1].collisao == true || janela.tile.chao[tile2].collisao == true)
                {
                    entity.colisaoLigada = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < janela.so.length; i++)
        {
            if (janela.so[i] != null)
            {
                //Trazendo a area Solida da entidade
                entity.areaSolida.x = entity.worldX + entity.areaSolida.x;
                entity.areaSolida.y = entity.worldY + entity.areaSolida.y;
                //Trazendo a posisao da area Solida do objecto
                janela.so[i].areaSolida.x = janela.so[i].XNoMundo + janela.so[i].areaSolida.x;
                janela.so[i].areaSolida.y = janela.so[i].YNoMundo + janela.so[i].areaSolida.y;
                switch (entity.direcao)
                {
                    case "cima":
                        janela.so[i].areaSolida.y += entity.velocidade;
                        if (entity.areaSolida.intersects(janela.so[i].areaSolida))
                        {
                            if (janela.so[i].colisao)
                            {
                                entity.colisaoLigada = true;
                            } else
                            {
                                entity.colisaoLigada = false;
                            }
                            if (player)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "baixo":
                        janela.so[i].areaSolida.y -= entity.velocidade;
                        if (entity.areaSolida.intersects(janela.so[i].areaSolida))
                        {
                            if (janela.so[i].colisao)
                            {
                                entity.colisaoLigada = true;
                            } else
                            {
                                entity.colisaoLigada = false;
                            }
                            if (player)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "esquerda":
                        janela.so[i].areaSolida.x += entity.velocidade;
                        if (entity.areaSolida.intersects(janela.so[i].areaSolida))
                        {
                            if (janela.so[i].colisao)
                            {
                                entity.colisaoLigada = true;
                            } else
                            {
                                entity.colisaoLigada = false;
                            }
                            if (player)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "direita":
                        janela.so[i].areaSolida.x -= entity.velocidade;
                        if (entity.areaSolida.intersects(janela.so[i].areaSolida))
                        {
                            entity.colisaoLigada = true;
                            if (janela.so[i].colisao)
                            {
                                entity.colisaoLigada = true;
                            } else
                            {
                                entity.colisaoLigada = false;
                            }
                            if (player)
                            {
                                index = i;
                            }
                        }
                        break;
                }
                entity.areaSolida.x = entity.areaSolidaDefaultX;
                entity.areaSolida.y = entity.areaSolidaDefaultY;
                janela.so[i].areaSolida.x = janela.so[i].areaSolidaDefaultX;
                janela.so[i].areaSolida.y = janela.so[i].areaSolidaDefaultY;
            }
        }
        return index;
    }
}
