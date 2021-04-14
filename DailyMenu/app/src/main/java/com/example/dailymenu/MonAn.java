package com.example.dailymenu;

public class MonAn {
    private int idMonAn;
    private String TenMonAn;
    private byte[] hinhAnh;
    private int idCongThuc;

    public MonAn(int idMonAn, String tenMonAn, byte[] hinhAnh, int idCongThuc) {
        this.idMonAn = idMonAn;
        TenMonAn = tenMonAn;
        this.hinhAnh = hinhAnh;
        this.idCongThuc = idCongThuc;
    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getIdCongThuc() {
        return idCongThuc;
    }

    public void setIdCongThuc(int idCongThuc) {
        this.idCongThuc = idCongThuc;
    }
}
