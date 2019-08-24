/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import interfaces.BilheteInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import model.Bilhete;

/**
 *
 * @author User
 */
public class Cliente {
    public static void main(String args[]) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
        BilheteInterface bilhe = (BilheteInterface) registry.lookup("BilheteService");
        bilhe.adicionarBilhetes("fsa", "ssa", 300);
        bilhe.adicionarBilhetes("fsa", "sp", 300);
        bilhe.adicionarBilhetes("fsa", "rj", 300);
        LinkedList<Bilhete> lista = bilhe.listarBilhetes();
        System.out.println(lista.toString());
    }
}
