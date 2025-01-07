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

    private LocalDateTime pauzaPanaLa;
    private int solicitariCount = 0;


    public Ghiseu(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
        this.stareGhiseu = true;
        Random random = new Random();
        this.numarRandom = random.nextInt(10) + 1;
        this.contor = 0;
    }

    public Ghiseu() {}

    public LocalDateTime getPauzaPanaLa() {
        return pauzaPanaLa;
    }

    public void setPauzaPanaLa(LocalDateTime pauzaPanaLa) {
        this.pauzaPanaLa = pauzaPanaLa;
    }

    public synchronized boolean getStareGhiseu() {
        return stareGhiseu;
    }

    public synchronized void setStareGhiseu(boolean stareGhiseu) {
        this.stareGhiseu = stareGhiseu;
    }

    public synchronized int getNumarRandom() {
        return numarRandom;
    }

    public synchronized int getContor() {
        return contor;
    }

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

    private synchronized void pauzaGhiseu() {
        stareGhiseu = false;
        pauzaPanaLa = LocalDateTime.now().plusMinutes(1);
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " este în pauză până la " + pauzaPanaLa);
        new Thread(() -> {
            try {
                Thread.sleep(60000);
                redeschideGhiseu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private synchronized void redeschideGhiseu() {
        stareGhiseu = true;
        pauzaPanaLa = null;
        contor = 0;
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " s-a redeschis.");
    }

    public synchronized String getTip_de_document_eliberat() {
        return tip_de_document_eliberat.getTip();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
