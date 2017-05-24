package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 5/24/2017.
 */

public class Materi {
    private int id_bab;
    private String nama_materi;

    public Materi(int id_bab, String nama_materi) {
        this.id_bab = id_bab;
        this.nama_materi = nama_materi;
    }

    public int getId_bab() {
        return id_bab;
    }

    public void setId_bab(int id_bab) {
        this.id_bab = id_bab;
    }

    public String getNama_materi() {
        return nama_materi;
    }

    public void setNama_materi(String nama_materi) {
        this.nama_materi = nama_materi;
    }
}
