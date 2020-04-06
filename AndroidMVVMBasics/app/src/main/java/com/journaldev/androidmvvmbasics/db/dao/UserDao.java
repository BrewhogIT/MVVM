package com.journaldev.androidmvvmbasics.db.dao;

import com.journaldev.androidmvvmbasics.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    public LiveData<List<User>> getAll();

    @Insert
    public void insert(User user);
}
