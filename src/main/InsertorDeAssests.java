
package main;

import object.Bau;
import object.Chave;
import object.Porta;
import object.Sapatilhas;

public class InsertorDeAssests {
            Painel painel;

            public InsertorDeAssests(Painel painel) {
                        this.painel = painel;
            }
            
            public void setObject(){
                        painel.so[0]= new Chave();
                        painel.so[0].XNoMundo= 20*painel.tamanhoDePixeis;
                        painel.so[0].YNoMundo= 20*painel.tamanhoDePixeis;
                        painel.so[1]= new Bau();
                        painel.so[1].XNoMundo= 17*painel.tamanhoDePixeis;
                        painel.so[1].YNoMundo= 17*painel.tamanhoDePixeis;
                        painel.so[2]= new Porta();
                        painel.so[2].XNoMundo= 23*painel.tamanhoDePixeis;
                        painel.so[2].YNoMundo= 17*painel.tamanhoDePixeis;
                        painel.so[3]= new Sapatilhas();
                        painel.so[3].XNoMundo= 22*painel.tamanhoDePixeis;
                        painel.so[3].YNoMundo= 21*painel.tamanhoDePixeis;
            }
            public static void main(String[] args) {
                        
            }
}
