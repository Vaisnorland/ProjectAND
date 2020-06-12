package com.example.projectads.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.projectads.Model.Grocery;

import java.util.List;

@Dao
public interface GroceryDao {

    @Insert
    void insert(Grocery grocery);

    @Update
    void update(Grocery grocery);

    @Delete
    void delete(Grocery grocery);

    @Query("SELECT * FROM grocery_table ORDER BY quantity DESC")
    LiveData<List<Grocery>> getAllGoceries();

}
