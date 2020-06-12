package com.example.projectads.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectads.Database.GroceryRepository;

import com.example.projectads.Model.Grocery;

import java.util.List;

public class GroceryViewModel extends AndroidViewModel {

    private GroceryRepository repository;
    private LiveData<List<Grocery>> allGroceries;

    public GroceryViewModel(@NonNull Application application) {
        super(application);
        repository = new GroceryRepository(application);
        allGroceries = repository.getAllGroceries();
    }

    public void insert(Grocery grocery) {
        repository.insert(grocery);
    }

    public void delete(Grocery grocery) {
        repository.delete(grocery);
    }

    public LiveData<List<Grocery>> getAllGroceries() {
        return allGroceries;
    }

}
