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
public class Voo implements Serializable{
    static final long serialVersionUID = 1L;
    LinkedList<Bilhete> listaBilhetes;

    public LinkedList<Bilhete> getListaBilhetes() {
        return listaBilhetes;
    }

    public void setListaBilhetes(LinkedList<Bilhete> listaBilhetes) {
        this.listaBilhetes = listaBilhetes;
    }

    @Override
    public String toString() {
        return "Voo{" + "listaBilhetes=" + listaBilhetes + '}';
    }
    
    
}
