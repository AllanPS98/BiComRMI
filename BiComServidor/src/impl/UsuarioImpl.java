/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import interfaces.UsuarioInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bilhete;
import model.Usuario;

/**
 *
 * @author User
 */
public class UsuarioImpl extends UnicastRemoteObject implements UsuarioInterface {
    static final long serialVersionUID = 1L;
    LinkedList<Usuario> usuarios = new LinkedList<>();
    String PATH = "dados\\usuarios"; 
    public UsuarioImpl() throws RemoteException{
        super();
    }
    /**
     * Método que armazena os dados em um arquivo
     * @param nome
     * @param obj
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void escreverArquivoSerial(String nome, Object obj) throws FileNotFoundException, IOException {
        //Classe responsavel por inserir os objetos
        try (FileOutputStream arquivo = new FileOutputStream(nome)) {
            //Grava o objeto cliente no arquivo
            try ( //Classe responsavel por inserir os objetos
                    ObjectOutputStream objGravar = new ObjectOutputStream(arquivo)) {
                //Grava o objeto cliente no arquivo
                objGravar.writeObject(obj);
                objGravar.flush();
            }
            arquivo.flush();
        }
    }
    /**
     * Método que ler os dados do arquivo
     * @param nome
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void lerArquivoSerial(String nome) throws FileNotFoundException, IOException, ClassNotFoundException {
        Object obj;
        try {
            // Classe responsavel por recuperar os objetos do arquivo
            try (FileInputStream arquivo = new FileInputStream(nome); // Classe responsavel por recuperar os objetos do arquivo
                    ObjectInputStream leitura = new ObjectInputStream(arquivo)) {
                obj = leitura.readObject();
            }
            if (nome.equals(PATH)) {
                usuarios = (LinkedList<Usuario>) obj;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ainda não existe nenhum arquivo com esse caminho => " + nome);
        }

    }
    
    @Override
    public boolean loginUsuario(String cpf, String senha) throws RemoteException {
        try {
            lerArquivoSerial(PATH);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < usuarios.size(); i++){
            if(cpf.equals(usuarios.get(i).getLogin()) && senha.equals(usuarios.get(i).getSenha())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String comprarBilhete() throws RemoteException {
        return "nada";
    }

    @Override
    public String cadastroUsuario(String nome, String cpf, String senha, String regiao) throws RemoteException {
        try {
            lerArquivoSerial(PATH);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean achouIgual = false;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getLogin().equals(cpf)){
                achouIgual = true;
            }
        }
        if(!achouIgual){
            try {
                Usuario u = new Usuario(nome,cpf,senha, regiao);
                usuarios.add(u);
                escreverArquivoSerial(PATH,usuarios);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Cadastro efetuado com sucesso.";
        }
        return "Já existe alguém com esse CPF.";
    }
    

    @Override
    public LinkedList<Bilhete> listarBilhetes(String cpf) throws RemoteException {
        try {
            lerArquivoSerial(PATH);
            for(int i = 0; i < usuarios.size(); i++){
                if(usuarios.get(i).getLogin().equals(cpf)){
                    return usuarios.get(i).getBilhetesComprados();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
