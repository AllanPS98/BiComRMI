/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import app.Cliente;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Bilhete;

/**
 *
 * @author User
 */
public class MenuUsuario extends javax.swing.JFrame {

    /**
     * Creates new form MenuUsuario
     */
    LinkedList<Bilhete> meusBilhetes = new LinkedList<>();
    LinkedList<Bilhete> origensList = new LinkedList<>();
    LinkedList<Bilhete> destinosList = new LinkedList<>();
    LinkedList<Bilhete> listaNorte = new LinkedList<>();
    LinkedList<Bilhete> listaCentro = new LinkedList<>();
    LinkedList<Bilhete> listaSul = new LinkedList<>();
    DefaultListModel modelo = new DefaultListModel();
    DefaultListModel modelo2 = new DefaultListModel();
    public static int companhia = 0;
    public static MenuUsuario menu;
    int qtdTrechos;

    public MenuUsuario() throws IOException, ClassNotFoundException, RemoteException, NotBoundException {
        initComponents();
        adicionarElementos();
    }

    private void adicionarElementos() throws IOException, ClassNotFoundException, RemoteException, NotBoundException {
        Cliente c = new Cliente();
        listaTrechosCompletos.removeAll();
        modelo2.removeAllElements();
        listaTrechosCompletos.setModel(modelo2);
        meusBilhetes = c.listarBilhetesComprados(TelaLogin.loginAux, TelaInicial.ip_a, TelaInicial.porta_a);
        if (meusBilhetes.isEmpty()) {
            meusBilhetes = c.listarBilhetesComprados(TelaLogin.loginAux, TelaInicial.ip_b, TelaInicial.porta_b);
            if (meusBilhetes.isEmpty()) {
                meusBilhetes = c.listarBilhetesComprados(TelaLogin.loginAux, TelaInicial.ip_c, TelaInicial.porta_c);
            }
        }
        if (!meusBilhetes.isEmpty()) {
            System.out.println("Os bilhetes existem");
            listaBilhetes.removeAll();
            if (!meusBilhetes.isEmpty()) {
                for (int i = 0; i < meusBilhetes.size(); i++) {
                    modelo.addElement("Código: " + meusBilhetes.get(i).getId()
                            + "| Origem: " + meusBilhetes.get(i).getOrigem()
                            + "| Destino: " + meusBilhetes.get(i).getDestino()
                            + "| Preço: R$" + meusBilhetes.get(i).getPreco()
                            + "| Data: " + meusBilhetes.get(i).getData() + " # " + meusBilhetes.get(i).getHorario_voo()
                    );
                }
            }
        }

        listaBilhetes.setModel(modelo);
        listaNorte = c.listarBilhetesCompanhia(TelaInicial.ip_a, TelaInicial.porta_a, 1);
        listaCentro = c.listarBilhetesCompanhia(TelaInicial.ip_b, TelaInicial.porta_b, 2);
        listaSul = c.listarBilhetesCompanhia(TelaInicial.ip_c, TelaInicial.porta_c, 3);

        //preenchendo combobox origem
        for (int i = 0; i < listaNorte.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < origensList.size(); j++) {
                if (listaNorte.get(i).getOrigem().equals(origensList.get(j).getOrigem())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                origensList.add(listaNorte.get(i));
            }
        }
        for (int i = 0; i < listaCentro.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < origensList.size(); j++) {
                if (listaCentro.get(i).getOrigem().equals(origensList.get(j).getOrigem())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                origensList.add(listaCentro.get(i));
            }
        }

        for (int i = 0; i < listaSul.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < origensList.size(); j++) {
                if (listaSul.get(i).getOrigem().equals(origensList.get(j).getOrigem())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                origensList.add(listaSul.get(i));
            }
        }

        System.out.println(origensList);
        origensbox.removeAllItems();
        for (int i = 0; i < origensList.size(); i++) {
            origensbox.addItem(origensList.get(i).getOrigem());
        }

        //preenchendo combobox do destino
        for (int i = 0; i < listaNorte.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < destinosList.size(); j++) {
                if (listaNorte.get(i).getDestino().equals(destinosList.get(j).getDestino())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                destinosList.add(listaNorte.get(i));
            }
        }
        for (int i = 0; i < listaCentro.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < destinosList.size(); j++) {
                if (listaCentro.get(i).getDestino().equals(destinosList.get(j).getDestino())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                destinosList.add(listaCentro.get(i));
            }
        }

        for (int i = 0; i < listaSul.size(); i++) {
            boolean podeAdd = true;
            for (int j = 0; j < destinosList.size(); j++) {
                if (listaSul.get(i).getDestino().equals(destinosList.get(j).getDestino())) {
                    podeAdd = false;
                }
            }
            if (podeAdd) {
                destinosList.add(listaSul.get(i));
            }
        }

        System.out.println(destinosList);
        destinosbox.removeAllItems();
        for (int i = 0; i < destinosList.size(); i++) {
            destinosbox.addItem(destinosList.get(i).getDestino());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        norte = new javax.swing.JButton();
        centro = new javax.swing.JButton();
        sul = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaBilhetes = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        origensbox = new javax.swing.JComboBox<>();
        destinosbox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTrechosCompletos = new javax.swing.JList<>();
        comprarCompleto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu do Usuário");
        setResizable(false);

        norte.setText("Companhia Norte - Nordeste");
        norte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                norteActionPerformed(evt);
            }
        });

        centro.setText("Companha Centro-Oeste");
        centro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centroActionPerformed(evt);
            }
        });

        sul.setText("Companhia Sul - Suldeste");
        sul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sulActionPerformed(evt);
            }
        });

        listaBilhetes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaBilhetes);

        jLabel1.setText("Meus Bilhetes");

        origensbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        destinosbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Origem");

        jLabel3.setText("Destino");

        pesquisar.setText("Pesquisar");
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });

        listaTrechosCompletos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaTrechosCompletos);

        comprarCompleto.setText("Comprar Trecho Completo");
        comprarCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprarCompletoActionPerformed(evt);
            }
        });

        jLabel4.setText("Resultados da Pesquisa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(origensbox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(destinosbox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 465, Short.MAX_VALUE)
                                .addComponent(pesquisar))))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(norte, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comprarCompleto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(centro, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145)
                                .addComponent(sul, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(norte)
                    .addComponent(centro)
                    .addComponent(sul))
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origensbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinosbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comprarCompleto)
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void norteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_norteActionPerformed
        companhia = 1;
        try {
            this.setVisible(false);
            ListarBilhetes.listarBilhetes = new ListarBilhetes();
            ListarBilhetes.listarBilhetes.setVisible(true);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MenuUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_norteActionPerformed

    private void centroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centroActionPerformed
        companhia = 2;
        try {
            this.setVisible(false);
            ListarBilhetes.listarBilhetes = new ListarBilhetes();
            ListarBilhetes.listarBilhetes.setVisible(true);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MenuUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_centroActionPerformed

    private void sulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sulActionPerformed
        companhia = 3;
        try {
            this.setVisible(false);
            ListarBilhetes.listarBilhetes = new ListarBilhetes();
            ListarBilhetes.listarBilhetes.setVisible(true);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MenuUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sulActionPerformed

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        qtdTrechos = 0;
        if (origensbox.getSelectedItem().toString().equals(destinosbox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Origem e destino devem ser diferentes.");
        } else {
            listaTrechosCompletos.removeAll();
            modelo2.removeAllElements();
            // caso precise apenas de um trecho pra formar o caminho completo
            boolean precisaUm = false;
            for (int i = 0; i < listaNorte.size(); i++) {
                if (origensbox.getSelectedItem().toString().equals(listaNorte.get(i).getOrigem())
                        && destinosbox.getSelectedItem().toString().equals(listaNorte.get(i).getDestino())) {
                    modelo2.addElement(listaNorte.get(i).toString());
                    precisaUm = true;
                }
            }
            for (int i = 0; i < listaCentro.size(); i++) {
                if (origensbox.getSelectedItem().toString().equals(listaCentro.get(i).getOrigem())
                        && destinosbox.getSelectedItem().toString().equals(listaCentro.get(i).getDestino())) {
                    modelo2.addElement(listaCentro.get(i).toString());
                    precisaUm = true;
                }
            }
            for (int i = 0; i < listaSul.size(); i++) {
                if (origensbox.getSelectedItem().toString().equals(listaSul.get(i).getOrigem())
                        && destinosbox.getSelectedItem().toString().equals(listaSul.get(i).getDestino())) {
                    modelo2.addElement(listaSul.get(i).toString());
                    precisaUm = true;
                }
            }
            if (!precisaUm) {
                LinkedList<Bilhete> origensAux = new LinkedList<>();
                LinkedList<Bilhete> interAux = new LinkedList<>();
                LinkedList<Bilhete> destinosAux = new LinkedList<>();
                //adicionando bilhetes com a origem selecionada
                for (int i = 0; i < listaNorte.size(); i++) {
                    if (listaNorte.get(i).getOrigem().equals(origensbox.getSelectedItem().toString())) {
                        origensAux.add(listaNorte.get(i));
                    }
                }
                for (int i = 0; i < listaCentro.size(); i++) {
                    if (listaCentro.get(i).getOrigem().equals(origensbox.getSelectedItem().toString())) {
                        origensAux.add(listaCentro.get(i));
                    }
                }
                for (int i = 0; i < listaSul.size(); i++) {
                    if (listaSul.get(i).getOrigem().equals(origensbox.getSelectedItem().toString())) {
                        origensAux.add(listaSul.get(i));
                    }
                }
                //adicionando bilhetes com o destino selecionada
                for (int i = 0; i < listaNorte.size(); i++) {
                    if (listaNorte.get(i).getDestino().equals(destinosbox.getSelectedItem().toString())) {
                        destinosAux.add(listaNorte.get(i));
                    }
                }
                for (int i = 0; i < listaCentro.size(); i++) {
                    if (listaCentro.get(i).getDestino().equals(destinosbox.getSelectedItem().toString())) {
                        destinosAux.add(listaCentro.get(i));
                    }
                }
                for (int i = 0; i < listaSul.size(); i++) {
                    if (listaSul.get(i).getDestino().equals(destinosbox.getSelectedItem().toString())) {
                        destinosAux.add(listaSul.get(i));
                    }
                }
                //adicionando bilhetes intermediário do trecho
                for (int i = 0; i < origensAux.size(); i++) {
                    for (int j = 0; j < listaNorte.size(); j++) {
                        if (origensAux.get(i).getDestino().equals(listaNorte.get(j).getOrigem())) {
                            for (int k = 0; k < destinosAux.size(); k++) {
                                if (destinosAux.get(k).getOrigem().equals(listaNorte.get(j).getDestino())) {
                                    interAux.add(listaNorte.get(j));
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < origensAux.size(); i++) {
                    for (int j = 0; j < listaCentro.size(); j++) {
                        if (origensAux.get(i).getDestino().equals(listaCentro.get(j).getOrigem())) {
                            for (int k = 0; k < destinosAux.size(); k++) {
                                if (destinosAux.get(k).getOrigem().equals(listaCentro.get(j).getDestino())) {
                                    interAux.add(listaCentro.get(j));
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < origensAux.size(); i++) {
                    for (int j = 0; j < listaSul.size(); j++) {
                        if (origensAux.get(i).getDestino().equals(listaSul.get(j).getOrigem())) {
                            for (int k = 0; k < destinosAux.size(); k++) {
                                if (destinosAux.get(k).getOrigem().equals(listaSul.get(j).getDestino())) {
                                    interAux.add(listaSul.get(j));
                                }
                            }
                        }
                    }
                }

                if (interAux.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não foram encontrados trechos intermediários. Caso queira"
                            + " escolha um trecho intermediário individualmente em alguma das companhias.");
                    for (int i = 0; i < origensAux.size(); i++) {
                        for (int j = 0; j < destinosAux.size(); j++) {
                            modelo2.addElement(origensAux.get(i).toString() + "-"
                                    + destinosAux.get(j).toString());
                        }
                    }
                    qtdTrechos = 2;
                } else {
                    for (int i = 0; i < origensAux.size(); i++) {
                        for (int j = 0; j < destinosAux.size(); j++) {
                            for (int k = 0; k < interAux.size(); k++) {
                                modelo2.addElement(origensAux.get(i).toString()
                                        + "-" + interAux.get(k).toString()
                                        + "-" + destinosAux.get(j).toString());
                            }

                        }
                    }
                    qtdTrechos = 3;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Foi necessário apenas um trecho para essa viagem.");
                qtdTrechos = 1;
            }
            //setando o modelo na interface
            listaTrechosCompletos.setModel(modelo2);
        }
    }//GEN-LAST:event_pesquisarActionPerformed

    private void comprarCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprarCompletoActionPerformed
        if (listaTrechosCompletos.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Escolha ao menos UM trecho.");
        } else {
            String textoLista;
            String textoBilhete1;
            String textoBilhete2;
            String textoBilhete3;
            Bilhete b = null;
            Bilhete b2 = null;
            Bilhete b3 = null;
            Cliente c = new Cliente();
            boolean resultado = false;
            int companhiaAux = 0;
            int companhiaAux2 = 0;
            int companhiaAux3 = 0;
            switch (qtdTrechos) {
                case 1:
                    textoLista = listaTrechosCompletos.getSelectedValue();
                    for (int i = 0; i < listaNorte.size(); i++) {
                        if (listaNorte.get(i).toString().equals(textoLista)) {
                            b = listaNorte.get(i);
                            companhiaAux = 1;
                            break;
                        }
                    }
                    for (int i = 0; i < listaCentro.size(); i++) {
                        if (listaCentro.get(i).toString().equals(textoLista)) {
                            b = listaCentro.get(i);
                            companhiaAux = 2;
                            break;
                        }
                    }
                    for (int i = 0; i < listaCentro.size(); i++) {
                        if (listaCentro.get(i).toString().equals(textoLista)) {
                            b = listaCentro.get(i);
                            companhiaAux = 3;
                            break;
                        }
                    }

                    System.out.println(b);
                    if (b != null) {
                        switch (companhiaAux) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    textoLista = listaTrechosCompletos.getSelectedValue();
                    textoBilhete1 = textoLista.split("-")[0];
                    textoBilhete2 = textoLista.split("-")[1];
                    for (int i = 0; i < listaNorte.size(); i++) {
                        if (listaNorte.get(i).toString().equals(textoBilhete1)) {
                            b = listaNorte.get(i);
                            companhiaAux = 1;
                            
                        }
                        if (listaNorte.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaNorte.get(i);
                            companhiaAux2 = 1;
                            
                        }
                    }
                    for (int i = 0; i < listaCentro.size(); i++) {
                        if (listaCentro.get(i).toString().equals(textoBilhete1)) {
                            b = listaCentro.get(i);
                            companhiaAux = 2;
                            
                        }
                        if (listaCentro.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaCentro.get(i);
                            companhiaAux2 = 2;
                            
                        }
                    }
                    for (int i = 0; i < listaSul.size(); i++) {
                        if (listaSul.get(i).toString().equals(textoBilhete1)) {
                            b = listaSul.get(i);
                            companhiaAux = 3;
                            
                        }
                        if (listaSul.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaSul.get(i);
                            companhiaAux2 = 3;
                            
                        }
                    }
                    resultado = false;
                    if (b != null) {
                        switch (companhiaAux) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }
                    resultado = false;
                    if (b2 != null) {
                        System.out.println("entrou b2");
                        switch (companhiaAux2) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }

                    break;
                case 3:
                    textoLista = listaTrechosCompletos.getSelectedValue();
                    textoBilhete1 = textoLista.split("-")[0];
                    textoBilhete2 = textoLista.split("-")[1];
                    textoBilhete3 = textoLista.split("-")[2];
                    System.out.println("ESSE É O B2: "+textoBilhete2);
                    for (int i = 0; i < listaNorte.size(); i++) {
                        if (listaNorte.get(i).toString().equals(textoBilhete1)) {
                            b = listaNorte.get(i);
                            companhiaAux = 1;
                            
                        }
                        if (listaNorte.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaNorte.get(i);
                            companhiaAux2 = 1;
                            
                        }
                        if (listaNorte.get(i).toString().equals(textoBilhete3)) {
                            b3 = listaNorte.get(i);
                            companhiaAux3 = 1;
                            
                        }
                    }
                    for (int i = 0; i < listaCentro.size(); i++) {
                        if (listaCentro.get(i).toString().equals(textoBilhete1)) {
                            b = listaCentro.get(i);
                            companhiaAux = 2;
                            
                        }
                        if (listaCentro.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaCentro.get(i);
                            companhiaAux2 = 2;
                            
                        }
                        if (listaCentro.get(i).toString().equals(textoBilhete3)) {
                            b3 = listaCentro.get(i);
                            companhiaAux3 = 2;
                            
                        }
                    }
                    for (int i = 0; i < listaSul.size(); i++) {
                        if (listaSul.get(i).toString().equals(textoBilhete1)) {
                            b = listaSul.get(i);
                            companhiaAux = 3;
                            
                        }
                        if (listaSul.get(i).toString().equals(textoBilhete2)) {
                            b2 = listaSul.get(i);
                            companhiaAux2 = 3;
                            
                        }
                        if (listaSul.get(i).toString().equals(textoBilhete3)) {
                            b3 = listaSul.get(i);
                            companhiaAux3 = 3;
                        }
                    }
                    resultado = false;
                    if (b != null) {
                        switch (companhiaAux) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b.getId() + " comprado por " + b.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }
                    resultado = false;
                    if (b2 != null) {
                        System.out.println("entrou b2");
                        switch (companhiaAux2) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b2.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b2.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b2.getId() + " comprado por " + b2.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }
                    resultado = false;
                    if (b3 != null) {
                        System.out.println("entrou b3");
                        switch (companhiaAux3) {
                            case 1: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b3.getId(), TelaInicial.ip_a, TelaInicial.porta_a,
                                            b3.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b3.getId() + " comprado por " + b3.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b3.getId(), TelaInicial.ip_b, TelaInicial.porta_b,
                                            b3.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b3.getId() + " comprado por " + b3.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                            case 3: {
                                try {
                                    resultado = c.comprarBilhete(TelaLogin.loginAux, b3.getId(), TelaInicial.ip_c, TelaInicial.porta_c,
                                            b3.getData());
                                } catch (RemoteException | NotBoundException ex) {
                                    Logger.getLogger(ListarBilhetes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (resultado) {
                                    //
                                    JOptionPane.showMessageDialog(null, "Bilhete " + b3.getId() + " comprado por " + b3.getPreco() + " reais.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao comprar bilhete.");
                                }
                            }
                            break;
                        }
                    }
                    break;
            }
            this.dispose();
            try {
                new MenuUsuario().setVisible(true);
            } catch (IOException | ClassNotFoundException | NotBoundException ex) {
                Logger.getLogger(MenuUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_comprarCompletoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton centro;
    private javax.swing.JButton comprarCompleto;
    private javax.swing.JComboBox<String> destinosbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaBilhetes;
    private javax.swing.JList<String> listaTrechosCompletos;
    private javax.swing.JButton norte;
    private javax.swing.JComboBox<String> origensbox;
    private javax.swing.JButton pesquisar;
    private javax.swing.JButton sul;
    // End of variables declaration//GEN-END:variables
}
