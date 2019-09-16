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
public interface UsuarioInterface extends Remote{
    public String cadastroUsuario(String nome, String cpf, String senha, String regiao) throws RemoteException;
    public boolean loginUsuario(String cpf, String senha) throws RemoteException;
    public Bilhete comprarBilhete(String cpf, int id, String data) throws RemoteException;
    public LinkedList<Bilhete> listarBilhetes(String cpf) throws RemoteException;
    public boolean salvarCompra(Bilhete b, String cpf) throws RemoteException;
}
