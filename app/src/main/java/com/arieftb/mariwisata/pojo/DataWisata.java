package com.arieftb.mariwisata.pojo;

import android.graphics.Bitmap;

/**
 * Created by root on 11/08/17.
 */

public class DataWisata {
    private String nama;
    private String alamat;
    private String detail;
    private Bitmap gambar;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Bitmap getGambar() {
        return gambar;
    }

    public void setGambar(Bitmap gambar) {
        this.gambar = gambar;
    }
}


