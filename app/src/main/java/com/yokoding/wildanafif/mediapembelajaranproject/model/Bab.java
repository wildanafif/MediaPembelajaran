package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 5/23/2017.
 */

public class Bab {
    private int id_bab;
    private String judul_bab;

    public Bab(int id_bab, String judul_bab) {
        this.id_bab = id_bab;
        this.judul_bab = judul_bab;
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
