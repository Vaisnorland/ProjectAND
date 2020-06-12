package com.example.projectads.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_table")

public class Grocery {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int quantity;

    public Grocery(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
