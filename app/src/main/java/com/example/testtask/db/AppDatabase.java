package com.example.testtask.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "personlist";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
                try {
                    initSomeValues();
                }
                catch (Exception e){//debagging initializate db multiple times
                     }
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract ContactDao contactDao();

    public static void initSomeValues() {
        for (int num_of_cont = 0; num_of_cont < 20; num_of_cont++) {
            Contact contact = new Contact(num_of_cont, "Name " + String.valueOf(num_of_cont), "surname", "email@a.com");
            ContactDao contactDao = sInstance.contactDao();
            contactDao.insert(contact);
        }
    }
}