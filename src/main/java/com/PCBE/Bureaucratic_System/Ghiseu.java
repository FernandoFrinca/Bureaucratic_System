package com.PCBE.Bureaucratic_System;

import jakarta.persistence.*;

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

    public void setTip_de_document_eliberat(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
    }

    public Ghiseu(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
        this.stareGhiseu = true;

        Random random = new Random();
        this.numarRandom = random.nextInt(10) + 1;

        this.contor = 0;
    }

    public Ghiseu() {

    }

    public synchronized String getTip_de_document_eliberat() {
        return tip_de_document_eliberat.getTip();
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

    public synchronized boolean getStareGhiseu() {
        return stareGhiseu;
    }

    private synchronized void pauzaGhiseu() {
        stareGhiseu = false;
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " este în pauză.");
        redeschideGhiseu();
        new Thread(() -> {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private synchronized void redeschideGhiseu() {
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() +" s-a redeschis.");
        contor = 0;
        stareGhiseu = true;
        notifyAll();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
