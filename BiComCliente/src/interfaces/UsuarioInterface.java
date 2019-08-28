/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author User
 */
public interface UsuarioInterface extends Remote{
    public String cadastroUsuario(String nome, String cpf, String senha, String regiao) throws RemoteException;
    public boolean loginUsuario(String cpf, String senha) throws RemoteException;
    public String comprarBilhete() throws RemoteException;
    public void carregarDados() throws RemoteException;
}
