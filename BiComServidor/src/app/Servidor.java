/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import impl.BilheteImpl;
import impl.UsuarioImpl;
import interfaces.BilheteInterface;
import interfaces.UsuarioInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author User
 */
public class Servidor {

    static int PORTA = 12345;

    public Servidor() {
    }
    
    public void startRMI() {
        new Thread() {
            @Override
            public void run() {
                boolean erro = true;
                while (erro) {
                    try {
                        UsuarioInterface usu = new UsuarioImpl();
                        BilheteInterface bil = new BilheteImpl();
                        Registry reg;
                        reg = LocateRegistry.createRegistry(PORTA);
                        reg.rebind("UsuarioService", usu);
                        reg.rebind("BilheteService", bil);
                        System.out.println("Servidor da porta " + PORTA + " foi iniciado.");
                        erro = false;
                    } catch (RemoteException ex) {
                        PORTA++;
                    }
                }
            }
        }.start();
    }
    
    public void gerarBilhetes(int companhia) throws RemoteException{
        switch (companhia) {
            case 1:
                BilheteInterface bilhetes = new BilheteImpl();
                for(int i = 0; i < 5; i++){
                   bilhetes.adicionarBilhetes("Belém", "Salvador", 3500, "Norte - Nordeste", "08:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes.adicionarBilhetes("Boa Vista", "Fortaleza", 3500, "Norte - Nordeste", "08:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes.adicionarBilhetes("Fortaleza", "Brasília", 3500, "Norte - Nordeste", "08:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes.adicionarBilhetes("Salvador", "Rio de Janeiro", 3500, "Norte - Nordeste", "08:00");
                }
                break;
            case 2:
                BilheteInterface bilhetes2 = new BilheteImpl();
                for(int i = 0; i < 5; i++){
                   bilhetes2.adicionarBilhetes("Goiânia", "Salvador", 2000, "Centro-Oeste", "12:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes2.adicionarBilhetes("Cuiabá", "São Paulo", 2000, "Centro-Oeste", "12:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes2.adicionarBilhetes("Campo Grande", "Manaus", 2000, "Centro-Oeste", "12:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes2.adicionarBilhetes("Brasília", "Curitiba", 2000, "Centro-Oeste", "12:00");
                }
                break;
            case 3:
                BilheteInterface bilhetes3 = new BilheteImpl();
                for(int i = 0; i < 5; i++){
                   bilhetes3.adicionarBilhetes("São Paulo", "Porto Alegre", 2800, "Sul - Sudeste", "18:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes3.adicionarBilhetes("Belo Horizonte", "Florianópolis", 2800, "Sul - Sudeste", "18:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes3.adicionarBilhetes("Rio de Janeiro", "Rio Branco", 2800, "Sul - Sudeste", "18:00");
                }
                for(int i = 0; i < 5; i++){
                   bilhetes3.adicionarBilhetes("Vitória", "Goiânia", 2800, "Sul - Sudeste", "18:00");
                }
                break;
        }
    }

}
