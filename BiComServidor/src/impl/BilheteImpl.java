/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import interfaces.BilheteInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bilhete;
import model.Usuario;

/**
 *
 * @author User
 */
public class BilheteImpl extends UnicastRemoteObject implements BilheteInterface {

    static final long serialVersionUID = 1L;
    static LinkedList<Bilhete> bilhetes = new LinkedList<>();
    static String PATH = "dados\\bilhetes";

    public BilheteImpl() throws RemoteException {
        super();
    }

    /**
     * Método que armazena os dados em um arquivo
     *
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
     *
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
                bilhetes = (LinkedList<Bilhete>) obj;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ainda não existe nenhum arquivo com esse caminho => " + nome);
        }

    }

    @Override
    public void adicionarBilhetes(String origem, String destino, float preco, String companhia, String horario) throws RemoteException {
        Bilhete b = new Bilhete(origem, destino, preco);
        b.setCompanhia(companhia);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date datax = new Date();
        String data = sdf.format(datax);
        b.setData(data);
        b.setHorario_voo(horario);
        b.setId(bilhetes.size() + 1);
        bilhetes.add(b);
        try {
            escreverArquivoSerial(PATH, bilhetes);
        } catch (IOException ex) {
            Logger.getLogger(BilheteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(b.toString() + "adicionado!");
    }

    @Override
    public LinkedList<Bilhete> listarBilhetes() throws RemoteException {
        try {
            lerArquivoSerial(PATH);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(BilheteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bilhetes;
    }

}
