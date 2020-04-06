package com.journaldev.androidmvvmbasics.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.text.TextUtils;
import android.util.Patterns;
@Entity
public class User extends BaseObservable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @Bindable
    @NonNull
    private String mEmail;

    @Bindable
    @NonNull
    private String mPassword;

    public User() {

    }

    public User(@NonNull final String email, @NonNull final String password) {
        mEmail = email;
        mPassword = password;
    }

    public User(int id, @NonNull String email, @NonNull String password) {
        this.id = id;
        mEmail = email;
        mPassword = password;
    }

    @NonNull
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(@NonNull final String email) {
        mEmail = email;
        notifyChange();
    }

    @NonNull
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(@NonNull final String password) {
        mPassword = password;
        notifyChange();
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() && getPassword().length() > 5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}