package com.PCBE.Bureaucratic_System;

import jakarta.persistence.*;


@Entity
@Table(name="documente")
public class  Document {

    private String tip;
    @Id
    private int id;

    public Document() {

    }
    public Document (String tip, int id){
        this.id = id; this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

/*    public int getId() {
        return Integer.parseInt(tip);
    }*/

    public void setTip(String newTip) {
        tip = newTip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
