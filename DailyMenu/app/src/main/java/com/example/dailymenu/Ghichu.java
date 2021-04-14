package com.example.dailymenu;

public class Ghichu {
    private int id;
    private String tenGhichu;
    private String ndGhichu;

    public Ghichu(int id, String tenGhichu, String ndGhichu) {
        this.id = id;
        this.tenGhichu = tenGhichu;
        this.ndGhichu = ndGhichu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGhichu() {
        return tenGhichu;
    }

    public void setTenGhichu(String tenGhichu) {
        this.tenGhichu = tenGhichu;
    }

    public String getNdGhichu() {
        return ndGhichu;
    }

    public void setNdGhichu(String ndGhichu) {
        this.ndGhichu = ndGhichu;
    }
}
