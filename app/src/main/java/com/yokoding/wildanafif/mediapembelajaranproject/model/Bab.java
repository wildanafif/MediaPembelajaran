package com.yokoding.wildanafif.mediapembelajaranproject.model;

import java.io.Serializable;

/**
 * Created by wildan afif on 5/23/2017.
 */

public class Bab implements Serializable{
    private int id_bab;
    private String judul_bab;
    private String indikator;
    private int kkm;


    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public Bab(int id_bab, String judul_bab, String indikator, int kkm) {
        this.id_bab = id_bab;
        this.judul_bab = judul_bab;
        this.indikator = indikator;
        this.kkm=kkm;
    }

    public int getKkm() {
        return kkm;
    }

    public void setKkm(int kkm) {
        this.kkm = kkm;
    }

    public int getId_bab() {
        return id_bab;
    }

    public void setId_bab(int id_bab) {
        this.id_bab = id_bab;
    }

    public String getJudul_bab() {
        return judul_bab;
    }

    public void setJudul_bab(String judul_bab) {
        this.judul_bab = judul_bab;
    }
}
