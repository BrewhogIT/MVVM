package com.journaldev.androidmvvmbasics.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "id",
                                  childColumns = "user_id",
                                  onDelete = CASCADE))
public class Car {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String model;

    public int year;

    @ColumnInfo(name = "user_id")
    public long userId;
}
