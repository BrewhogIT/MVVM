package com.journaldev.androidmvvmbasics.db.database;

import android.app.Application;

import com.journaldev.androidmvvmbasics.db.dao.UserDao;
import com.journaldev.androidmvvmbasics.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
