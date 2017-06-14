package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 6/2/2017.
 */

public class User {
    private int id;
    private String nama;
    private int kode_absen;

    public User(String nama, int kode_absen) {
        this.nama = nama;
        this.kode_absen = kode_absen;
    }

    public User(int id, String nama, int kode_absen) {
        this.id = id;
        this.nama = nama;
        this.kode_absen = kode_absen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKode_absen() {
        return kode_absen;
    }

    public void setKode_absen(int kode_absen) {
        this.kode_absen = kode_absen;
    }
}
