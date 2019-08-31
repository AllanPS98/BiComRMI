/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Bilhete implements Serializable{
    static final long serialVersionUID = 1L;
    String origem;
    String destino;
    float preco;
    int id;
    String data;
    String horario_voo;
    String companhia;

    public Bilhete(String origem, String destino, float preco) {
        super();
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario_voo() {
        return horario_voo;
    }

    public void setHorario_voo(String horario_voo) {
        this.horario_voo = horario_voo;
    }
    
    

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bilhete{" + "origem=" + origem + ", destino=" + destino + ", preco=" + preco + ", id=" + id + '}';
    }
    
    
    
}
