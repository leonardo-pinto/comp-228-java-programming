package com.example.leonardopintocomp228lab5;

public class Product {
    private int id;
    private String name;
    private String company;
    private double price;

    public Product(int id, String name, String company, double price) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public double getPrice() {
        return price;
    }
}
