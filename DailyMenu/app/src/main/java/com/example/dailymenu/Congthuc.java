package com.example.dailymenu;

public class CongThuc {
    private int Id;
    private String tenMonan;
    private byte[] anhMonAn;

    public CongThuc(int id, String tenMonan, byte[] anhMonAn) {
        Id = id;
        this.tenMonan = tenMonan;
        this.anhMonAn = anhMonAn;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenMonan() {
        return tenMonan;
    }

    public void setTenMonan(String tenMonan) {
        this.tenMonan = tenMonan;
    }

    public byte[] getAnhMonAn() {
        return anhMonAn;
    }

    public void setAnhMonAn(byte[] anhMonAn) {
        this.anhMonAn = anhMonAn;
    }
}
