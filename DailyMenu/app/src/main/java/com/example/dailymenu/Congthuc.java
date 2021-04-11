package com.example.dailymenu;

public class Congthuc {
    private String TenCT;
    private int imgAnh;
    public Congthuc(String tenCT, int imgAnh) {
        TenCT = tenCT;
        this.imgAnh = imgAnh;
    }

    public String getTenCT() {
        return TenCT;
    }

    public void setTenCT(String tenCT) {
        TenCT = tenCT;
    }

    public int getImgAnh() {
        return imgAnh;
    }

    public void setImgAnh(int imgAnh) {
        this.imgAnh = imgAnh;
    }
}
