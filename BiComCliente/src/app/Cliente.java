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
import java.util.LinkedList;
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
        String resultado = usu.cadastroUsuario(nome, cpf, senha, regiao);
        return resultado;
    }
    
    public boolean login(String cpf, String senha, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        boolean resultado = usu.loginUsuario(cpf, senha);
        return resultado;
    }
    
    public LinkedList<Bilhete> listarBilhetesComprados(String cpf, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        return usu.listarBilhetes(cpf);
    }
    
    public LinkedList<Bilhete> listarBilhetesCompanhia(String ip, int porta, int companhia) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        BilheteInterface bil = (BilheteInterface) reg.lookup("BilheteService");
        String comp = null;
        switch (companhia) {
            case 1:
                comp = "Norte - Nordeste";
                break;
            case 2:
                comp = "Centro-Oeste";
                break;
            case 3:
                comp = "Sul - Sudeste";
                break;
        }
        return bil.listarBilhetes(comp);
    }
    
    public boolean comprarBilhete(String cpf, int id, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        return usu.comprarBilhete(cpf, id);
    }
}
