/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import interfaces.BilheteInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import model.Bilhete;

/**
 *
 * @author User
 */
public class BilheteImpl extends UnicastRemoteObject implements BilheteInterface {
    
    static final long serialVersionUID = 1L;
    static LinkedList<Bilhete> bilhetes = new LinkedList<>();
    public BilheteImpl() throws RemoteException{
        super();
    }
    
    @Override
    public void adicionarBilhetes(String origem, String destino, float preco) throws RemoteException {
        Bilhete b = new Bilhete(origem, destino, preco);
        b.setId(bilhetes.size()+1);
        bilhetes.add(b);
        System.out.println(b.toString() + "adicionado!");
    }

    @Override
    public LinkedList<Bilhete> listarBilhetes() throws RemoteException {
        return bilhetes;
    }
    
}
