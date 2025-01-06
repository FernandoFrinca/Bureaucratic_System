package com.PCBE.Bureaucratic_System;

import jakarta.persistence.*;

import java.util.Random;
import java.util.concurrent.Semaphore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Entity
@Table(name = "ghisee")
public class Ghiseu {

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document tip_de_document_eliberat;

    private boolean stareGhiseu;
    private int numarRandom;
    private int contor;

    @Transient
    private Semaphore semafor = new Semaphore(1);

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "birou_id")
    private Birou birou;

    private LocalDateTime pauzaPanaLa; // Nou câmp pentru gestionarea pauzei
    private int solicitariCount = 0;



    // Constructori
    public Ghiseu(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
        this.stareGhiseu = true;
        Random random = new Random();
        this.numarRandom = random.nextInt(10) + 1;
        this.contor = 0;
    }

    public Ghiseu() {}

    // Getter și setter pentru pauzaPanaLa
    public LocalDateTime getPauzaPanaLa() {
        return pauzaPanaLa;
    }

    public void setPauzaPanaLa(LocalDateTime pauzaPanaLa) {
        this.pauzaPanaLa = pauzaPanaLa;
    }

    // Getter și setter pentru stareGhiseu
    public synchronized boolean getStareGhiseu() {
        return stareGhiseu;
    }

    public synchronized void setStareGhiseu(boolean stareGhiseu) {
        this.stareGhiseu = stareGhiseu;
    }

    // Getter pentru numarRandom și contor
    public synchronized int getNumarRandom() {
        return numarRandom;
    }

    public synchronized int getContor() {
        return contor;
    }

    // Incrementare contor și pauză automată
    public synchronized void incrementeazaContor() {
        try {
            semafor.acquire();
            contor++;
            if (contor >= numarRandom) {
                pauzaGhiseu();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semafor.release();
        }
    }

    // Logica pentru pauză
    private synchronized void pauzaGhiseu() {
        stareGhiseu = false;
        pauzaPanaLa = LocalDateTime.now().plusMinutes(1); // Pauză timp de 1 minut
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " este în pauză până la " + pauzaPanaLa);
        new Thread(() -> {
            try {
                Thread.sleep(60000); // Pauză de 1 minut
                redeschideGhiseu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Logica pentru redeschidere
    private synchronized void redeschideGhiseu() {
        stareGhiseu = true;
        pauzaPanaLa = null;
        contor = 0;
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " s-a redeschis.");
    }

    // Getter pentru tip document eliberat
    public synchronized String getTip_de_document_eliberat() {
        return tip_de_document_eliberat.getTip();
    }

    // Getter și setter pentru ID
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Getter și setter pentru Birou
    public Birou getBirou() {
        return birou;
    }

    public void setBirou(Birou birou) {
        this.birou = birou;
    }
    public int getSolicitariCount() {
        return solicitariCount;
    }

    public void setSolicitariCount(int solicitariCount) {
        this.solicitariCount = solicitariCount;
    }
}
