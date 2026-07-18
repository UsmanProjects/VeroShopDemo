package com.veroshop.demo.models;

public class Category {
    private String name;
    private String emoji;
    private int productCount;

    public Category(String name, String emoji, int productCount) {
        this.name = name;
        this.emoji = emoji;
        this.productCount = productCount;
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public int getProductCount() { return productCount; }
}
