package com.example.learnenglishapp.model;

public class TracNghiem {
    private int id;
    private String cauTA;
    private String da_A;
    private String da_B;
    private String da_C;
    private String da_D;
    private String da_True;

    public TracNghiem(int id, String cauTA, String da_A, String da_B, String da_C, String da_D, String da_True) {
        this.id = id;
        this.cauTA = cauTA;
        this.da_A = da_A;
        this.da_B = da_B;
        this.da_C = da_C;
        this.da_D = da_D;
        this.da_True = da_True;
    }

    public int getId() {
        return id;
    }

    public String getCauTA() {
        return cauTA;
    }

    public String getDa_A() {
        return da_A;
    }

    public String getDa_B() {
        return da_B;
    }

    public String getDa_C() {
        return da_C;
    }

    public String getDa_D() {
        return da_D;
    }

    public String getDa_True() {
        return da_True;
    }
}
