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
import java.io.IOException;
import java.net.BindException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Servidor {

    static int PORTA = 12345;

    public static void main(String args[]) throws RemoteException {
        startRMI();
    }

    public static void startRMI() {
        new Thread() {
            @Override
            public void run() {
                boolean erro = true;
                while (erro) {
                    try {
                        UsuarioInterface usu = new UsuarioImpl();
                        Registry reg;
                        reg = LocateRegistry.createRegistry(PORTA);
                        reg.rebind("UsuarioService", usu);
                        System.out.println("Servidor da porta " + PORTA + " foi iniciado.");
                        erro = false;
                    } catch (RemoteException ex) {
                        PORTA++;
                    }
                }
            }
        }.start();
    }

}
