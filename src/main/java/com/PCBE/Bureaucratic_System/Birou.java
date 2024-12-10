package com.PCBE.Bureaucratic_System;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "birouri")
public class Birou {
    private String nume;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;
    private int contorListaGhiseuri = 0;

    @OneToMany
    @JoinColumn(name = "birou_  id")
    private ArrayList<Ghiseu> lista_ghiseuri_din_birou;
    @jakarta.persistence.Id
    private int id;

    public Birou(int id, String nume, ArrayList<Ghiseu> lista_ghiseuri_din_birou) {
        this.id = id;
        this.nume = nume;
        this.lista_ghiseuri_din_birou = lista_ghiseuri_din_birou;
    }

    public Birou() {

    }

    public synchronized boolean allowClient(Client client) {
        if (clientCount >= MAX_CLIENTS) {
            return false;
        }

        clientCount++;
        client.setBirou_asignat(this);
        System.out.println("------------------------------------------------");
        System.out.println("-> Client " + client + " a fost asignat la biroul " + nume);
        return true;
    }

    public synchronized void leaveOffice(Client client) {
        clientCount--;
        System.out.println("<- " + client + " a eliberat un loc la biroul " + nume);
        notifyAll();
    }

    public ArrayList<Ghiseu> getLista_ghiseuri_din_birou() {
        return lista_ghiseuri_din_birou;
    }

    public boolean obtinereDocumentDeLaGhiseu(Client client) {
        ArrayList<String> documente = client.getDocumenteNecesare();

        boolean toateDocumenteleObtinute = true;

        for (Ghiseu ghiseu : lista_ghiseuri_din_birou) {
            String tipDocument = ghiseu.getTip_de_document_eliberat();

            contorListaGhiseuri++;
            if(lista_ghiseuri_din_birou.size() < contorListaGhiseuri)
            {
                contorListaGhiseuri = 0;
                leaveOffice(client);
                return false;
            }

            System.out.println("--> " + documente.contains(tipDocument));
            if(!documente.contains(tipDocument)) {
                continue;
            }

            System.out.println("=  Clientul " + client + " a intrat în ghișeul pentru " + tipDocument);

            synchronized (ghiseu) {
                while (!ghiseu.getStareGhiseu()) {
                    try {
                        System.out.println("Clientul " + client + " așteaptă redeschiderea ghișeului pentru " + tipDocument);
                        ghiseu.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ghiseu.incrementeazaContor();

                if (documente.contains(tipDocument) && ghiseu.getStareGhiseu()) {
                    documente.remove(tipDocument);
                    client.setDocumenteNecesare(documente);
                    System.out.println("- "+ client +"-  A fost obținut documentul: " + tipDocument);
                    System.out.println("+ "+ client +"+  Documente rămase: " + documente);
                }

                if (!documente.isEmpty()) {
                    toateDocumenteleObtinute = false;
                }
            }
        }
        leaveOffice(client);

        return toateDocumenteleObtinute;
    }




    @Override
    public String toString() {
        return nume;
    }


    public int getGhisee() {
        return contorListaGhiseuri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
