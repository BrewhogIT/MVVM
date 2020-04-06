package com.journaldev.androidmvvmbasics;

import android.app.Application;

import com.journaldev.androidmvvmbasics.db.database.UserDatabase;

import androidx.room.Room;

public class App extends Application {
    private static UserDatabase mUserDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mUserDatabase = Room.databaseBuilder(this,UserDatabase.class,"userDatabase").build();
    }

    public static UserDatabase getUserDatabase() {
        return mUserDatabase;
    }
}
