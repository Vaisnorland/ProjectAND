package com.example.projectads;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GroceryRepository {

    private GroceryDao groceryDao;
    private LiveData<List<Grocery>> allGroceries;

    public GroceryRepository(Application application) {
        GroceryDatabase database = GroceryDatabase.getInstance(application);
        groceryDao = database.groceryDao();
        allGroceries = groceryDao.getAllGoceries();
    }

    public void insert(Grocery grocery) {
        new InsertNoteAsyncTask(groceryDao).execute(grocery);
    }

    public void update(Grocery grocery) {
        new UpdateNoteAsyncTask(groceryDao).execute(grocery);
    }

    public void delete(Grocery grocery) {
        new DeleteNoteAsyncTask(groceryDao).execute(grocery);
    }

    public LiveData<List<Grocery>> getAllGroceries() {
        return allGroceries;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Grocery, Void, Void> {

        private GroceryDao groceryDao;

        private InsertNoteAsyncTask(GroceryDao groceryDao) {
            this.groceryDao = groceryDao;
        }

        @Override
        protected Void doInBackground(Grocery... groceries) {
            groceryDao.insert(groceries[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyncTask extends AsyncTask<Grocery, Void, Void> {

        private GroceryDao groceryDao;

        private UpdateNoteAsyncTask(GroceryDao groceryDao) {
            this.groceryDao = groceryDao;
        }

        @Override
        protected Void doInBackground(Grocery... groceries) {
            groceryDao.update(groceries[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Grocery, Void, Void> {

        private GroceryDao groceryDao;

        private DeleteNoteAsyncTask(GroceryDao groceryDao) {
            this.groceryDao = groceryDao;
        }

        @Override
        protected Void doInBackground(Grocery... groceries) {
            groceryDao.delete(groceries[0]);
            return null;
        }
    }

}
