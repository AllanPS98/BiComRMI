/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import impl.BilheteImpl;
import interfaces.BilheteInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author User
 */
public class Servidor {
    public static void main(String args[]) throws RemoteException{
        BilheteInterface bilhe = new BilheteImpl();
        Registry registry = LocateRegistry.createRegistry(12345);
        registry.rebind("BilheteService", bilhe);
        System.out.println("Servidor Iniciado");
    }
}
