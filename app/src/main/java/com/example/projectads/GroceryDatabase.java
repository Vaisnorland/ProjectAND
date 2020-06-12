package com.example.projectads;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Grocery.class} , version = 1)
public abstract class GroceryDatabase extends RoomDatabase {

    private static GroceryDatabase instance;

    public abstract GroceryDao groceryDao();

    public static synchronized GroceryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GroceryDatabase.class, "grocery_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
            return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private GroceryDao groceryDao;

        private PopulateDbAsyncTask(GroceryDatabase db) {
            groceryDao = db.groceryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groceryDao.insert((new Grocery("Grocery 1",  1)));
            groceryDao.insert((new Grocery("Grocery 2",  2)));
            groceryDao.insert((new Grocery("Grocery 3",  3)));

            return null;
        }
    }

}
