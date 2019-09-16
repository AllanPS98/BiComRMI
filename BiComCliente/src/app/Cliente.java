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
    
    /**
     * Construtor vazio para ajudar no uso dos métodos na interface
     */
    public Cliente() {
    }
    /**
     * Se conecta com um dos servidores para fazer o cadastro de um usuário
     * @param nome
     * @param cpf
     * @param senha
     * @param regiao
     * @param ip
     * @param porta
     * @return
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public String cadastrarUsuario(String nome, String cpf, String senha, String regiao, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        System.out.println(ip);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        String resultado = usu.cadastroUsuario(nome, cpf, senha, regiao);
        return resultado;
    }
    /**
     * Se conecta com um dos servidores para fazer o login
     * @param cpf
     * @param senha
     * @param ip
     * @param porta
     * @return
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public boolean login(String cpf, String senha, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        System.out.println(ip);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        boolean resultado = usu.loginUsuario(cpf, senha);
        return resultado;
    }
    /**
     * Se conecta com um dos servidores para retornar os bilhetes comprados pelo usuário logado
     * @param cpf
     * @param ip
     * @param porta
     * @return
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public LinkedList<Bilhete> listarBilhetesComprados(String cpf, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        return usu.listarBilhetes(cpf);
    }
    /**
     * Se conecta com um dos servidores para listar seus bilhetes disponíveis
     * @param ip
     * @param porta
     * @param companhia
     * @return
     * @throws RemoteException
     * @throws NotBoundException 
     */
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
    /**
     * Se conecta com um dos servidores para comprar um bilhete
     * @param cpf
     * @param id
     * @param ip
     * @param porta
     * @param data
     * @return
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public synchronized Bilhete comprarBilhete(String cpf, int id, String ip, int porta, String data) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        return usu.comprarBilhete(cpf, id,data);
    }
    
    public synchronized boolean salvarCompra(Bilhete b, String cpf, String ip, int porta) throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(ip, porta);
        UsuarioInterface usu = (UsuarioInterface) reg.lookup("UsuarioService");
        return usu.salvarCompra(b, cpf);
    }
}
