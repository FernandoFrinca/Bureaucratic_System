package com.PCBE.Bureaucratic_System;

import java.util.ArrayList;

public class Client implements Runnable {
    private String nume;
    private Document document_necesar;
    private ArrayList<Birou> lista_de_birouri;
    private final int threadNumber;
    private Birou birou_asignat;
    ArrayList<String> documenteNecesare = new ArrayList<String>();
    private boolean obtinereListaDocumente = false;

    Client(String nume, Document document_necesar, ArrayList<Birou> lista_de_birouri, int threadNumber) {
        this.nume = nume;
        this.document_necesar = document_necesar;
        this.lista_de_birouri = lista_de_birouri;
        this.threadNumber = threadNumber;
    }

    public void cautaBirou() throws InterruptedException {
        synchronized (lista_de_birouri) {
            boolean clientAsignat = false;

            while (!clientAsignat) {
                for (Birou birou : lista_de_birouri) {
                    synchronized (birou) {
                        if (birou.allowClient(this)) {
                            if (birou.obtinereDocumentDeLaGhiseu(this)) {
                                birou_asignat = birou;
                                clientAsignat = true;
                                break;
                            }
                        }
                    }

                    if (documenteObtained()) {
                        clientAsignat = true;
                        break;
                    }
                }

                if (!clientAsignat) {
                    System.out.println("Client " + nume + " așteaptă să se elibereze un loc.");
                    lista_de_birouri.wait();
                }
            }
        }
    }

    private boolean documenteObtained() {
        return getDocumenteNecesare().isEmpty();
    }

    public void setBirou_asignat(Birou birou_asignat) {
        this.birou_asignat = birou_asignat;
    }

    public ArrayList<String> getDocumenteNecesare() {
        if (!obtinereListaDocumente) {
            String documentNecesar = document_necesar.getTip();

            switch (documentNecesar) {
                case "Adeverinta":
                    documenteNecesare.add("Buletin");
                    documenteNecesare.add("Adeverinta");
                    break;
                case "Buletin":
                    documenteNecesare.add("Adeverinta");
                    documenteNecesare.add("Buletin");
                    break;
                case "Pasaport":
                    documenteNecesare.add("Adeverinta");
                    documenteNecesare.add("Buletin");
                    documenteNecesare.add("Pasaport");
                    break;
                default:
                    break;
            }
            obtinereListaDocumente = true;
        }
        return documenteNecesare;
    }

    @Override
    public String toString() {
        return nume;
    }

    @Override
    public void run() {
        try {
            cautaBirou();
            Thread.sleep(1000);
//            birou_asignat.leaveOffice(this);
            synchronized (lista_de_birouri) {
                lista_de_birouri.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + threadNumber + " was interrupted.");
        }
    }

    public String getDocument_necesar() {
        return document_necesar.getTip();
    }

    public void setDocumenteNecesare(ArrayList<String> documenteNecesare) {
        this.documenteNecesare = documenteNecesare;
    }
}
