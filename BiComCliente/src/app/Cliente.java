/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import interfaces.BilheteInterface;
import interfaces.UsuarioInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.Bilhete;

/**
 *
 * @author User
 */
public class Cliente {

    public Cliente() {
    }
    
    public String cadastrarUsuario(String nome, String cpf, String senha, String regiao, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        usu.carregarDados();
        String resultado = usu.cadastroUsuario(nome, cpf, senha, regiao);
        return resultado;
    }
    
    public boolean login(String cpf, String senha, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        usu.carregarDados();
        boolean resultado = usu.loginUsuario(cpf, senha);
        return resultado;
    }
}
