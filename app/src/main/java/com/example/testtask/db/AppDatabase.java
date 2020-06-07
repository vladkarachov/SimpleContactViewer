package com.example.testtask.db;


import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "personlist";
    private static AppDatabase sInstance;

    public abstract ContactDao contactDao();

    public static AppDatabase getInstance(final Context context) {
        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).contactDao().insertAll(initSomeValues());
                    }
                });

        }
    };
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .addCallback(rdc)
                        .build();
                //trigerring callback
                sInstance.beginTransaction();
                sInstance.endTransaction();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }



    public static Contact[] initSomeValues() {
        Contact[] contacts = new Contact[20];
        for (int num_of_cont = 0; num_of_cont < 20; num_of_cont++) {
            Contact contact = new Contact(num_of_cont, "Name " + String.valueOf(num_of_cont), "surname", "email@a.com");
            contacts[num_of_cont] = contact;
        }
        return contacts;
    }
}