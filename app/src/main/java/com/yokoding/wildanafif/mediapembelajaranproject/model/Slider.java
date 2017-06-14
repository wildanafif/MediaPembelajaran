package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 5/31/2017.
 */

public class Slider {
    private String judul,gambar;

    public Slider(String judul, String gambar) {
        this.judul = judul;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}

