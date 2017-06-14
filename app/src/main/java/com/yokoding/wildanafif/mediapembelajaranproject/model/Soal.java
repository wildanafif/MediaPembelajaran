package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 6/9/2017.
 */

public class Soal{
    private String id, id_bab,soal,a,b,c,d,e,benar;

    public Soal(String id, String id_bab, String soal, String a, String b, String c, String d, String e, String benar) {
        this.id = id;
        this.id_bab = id_bab;
        this.soal = soal;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.benar = benar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_bab() {
        return id_bab;
    }

    public void setId_bab(String id_bab) {
        this.id_bab = id_bab;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getBenar() {
        return benar;
    }

    public void setBenar(String benar) {
        this.benar = benar;
    }
}
