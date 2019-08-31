/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Usuario implements Serializable{
    static final long serialVersionUID = 1L;
    LinkedList<Bilhete> bilhetesComprados;
    String nome;
    String login;
    String senha;
    String regiao;

    public Usuario(String nome, String login, String senha, String regiao) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.regiao = regiao;
        this.bilhetesComprados = new LinkedList<>();
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LinkedList<Bilhete> getBilhetesComprados() {
        return bilhetesComprados;
    }

    public void setBilhetesComprados(LinkedList<Bilhete> bilhetesComprados) {
        this.bilhetesComprados = bilhetesComprados;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
