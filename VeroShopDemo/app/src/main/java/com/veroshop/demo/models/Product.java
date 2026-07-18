package com.veroshop.demo.models;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private float rating;
    private int reviewCount;
    private String category;
    private String emoji;

    public Product(int id, String name, String description, double price,
                   float rating, int reviewCount, String category, String emoji) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.category = category;
        this.emoji = emoji;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }
    public String getCategory() { return category; }
    public String getEmoji() { return emoji; }
}
