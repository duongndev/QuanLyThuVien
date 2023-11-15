package com.duongnd.quanlythuvien.model;

import java.io.Serializable;

public class LoaiSach implements Serializable {
    private String id, name, nxb, image;

    public LoaiSach(String id, String name, String nxb, String image) {
        this.id = id;
        this.name = name;
        this.nxb = nxb;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}