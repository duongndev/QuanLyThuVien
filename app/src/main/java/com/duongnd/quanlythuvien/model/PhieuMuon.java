package com.duongnd.quanlythuvien.model;

public class PhieuMuon {
    private String id, idNV, idTV, idSP, date;
    private int price, status;

    public PhieuMuon() {
    }

    public PhieuMuon(String id, String idNV, String idTV, String idSP, String date, int price, int status) {
        this.id = id;
        this.idNV = idNV;
        this.idTV = idTV;
        this.idSP = idSP;
        this.date = date;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getIdTV() {
        return idTV;
    }

    public void setIdTV(String idTV) {
        this.idTV = idTV;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}