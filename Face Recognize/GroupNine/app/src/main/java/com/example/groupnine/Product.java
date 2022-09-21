package com.example.groupnine;

public class Product {
    private int id;
    private String name2;
    private float wage;

    public Product() {
    }

    public Product(int id, String name2, float wage) {
        this.id = id;
        this.name2 = name2;
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name2='" + name2 + '\'' +
                ", wage=" + wage +
                '}';
    }
}
