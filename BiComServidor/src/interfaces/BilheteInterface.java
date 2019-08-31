/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import model.Bilhete;

/**
 *
 * @author User
 */
public interface BilheteInterface extends Remote{
    public void adicionarBilhetes(String origem, String destino, float preco, String companhia, String horario) throws RemoteException;
    public LinkedList<Bilhete> listarBilhetes(String companhia) throws RemoteException;
}
