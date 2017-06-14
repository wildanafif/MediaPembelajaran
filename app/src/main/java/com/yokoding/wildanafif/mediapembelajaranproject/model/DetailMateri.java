package com.yokoding.wildanafif.mediapembelajaranproject.model;

import java.io.Serializable;

/**
 * Created by wildan afif on 6/4/2017.
 */

public class DetailMateri implements Serializable {
    private int id, bab_id;
    private String title,image,isi,youtube,rangkuman;

    public DetailMateri(int id, int bab_id, String title, String image, String isi, String youtube, String rangkuman) {
        this.id = id;
        this.bab_id = bab_id;
        this.title = title;
        this.image = image;
        this.isi = isi;
        this.youtube = youtube;
        this.rangkuman = rangkuman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBab_id() {
        return bab_id;
    }

    public void setBab_id(int bab_id) {
        this.bab_id = bab_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getRangkuman() {
        return rangkuman;
    }

    public void setRangkuman(String rangkuman) {
        this.rangkuman = rangkuman;
    }
}
