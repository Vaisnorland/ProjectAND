package com.example.projectads;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GroceryDao {

    @Insert
    void insert(Grocery grocery);

    @Update
    void update(Grocery grocery);

    @Delete
    void delete(Grocery grocery);

    /*  @Query("DELETE FROM note_table")
      void deleteAllNotes();
      */
    @Query("SELECT * FROM grocery_table ORDER BY quantity DESC")
    LiveData<List<Grocery>> getAllGoceries();

}
