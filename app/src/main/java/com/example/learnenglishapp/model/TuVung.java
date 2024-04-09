package com.example.learnenglishapp.model;

public class TuVung {
    private  int id;
    private String tuTA;
    private String phienAm;
    private String nghiaTV;

    private String tuLoai;
    private byte[] hinhAnh;

    public TuVung(int id, String tuTA, String phienAm, String nghiaTV, String tuLoai, byte[] hinhAnh) {
        this.id = id;
        this.tuTA = tuTA;
        this.phienAm = phienAm;
        this.nghiaTV = nghiaTV;
        this.tuLoai = tuLoai;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTuTA() {
        return tuTA;
    }

    public void setTuTA(String tuTA) {
        this.tuTA = tuTA;
    }

    public String getNghiaTV() {
        return nghiaTV;
    }

    public void setNghiaTV(String nghiaTV) {
        this.nghiaTV = nghiaTV;
    }

    public String getPhienAm() {
        return phienAm;
    }

    public void setPhienAm(String phienAm) {
        this.phienAm = phienAm;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTuLoai() {
        return tuLoai;
    }

    public void setTuLoai(String tuLoai) {
        this.tuLoai = tuLoai;
    }
}
