package com.duongnd.quanlythuvien.model;

public class Sach {
    private String id, name, author, idCategory;
    private int price;

    public Sach() {
    }

    public Sach(String id, String name, String author, String idCategory, int price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.idCategory = idCategory;
        this.price = price;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}