package com.journaldev.androidmvvmbasics.db.repository;

import android.os.AsyncTask;

import com.journaldev.androidmvvmbasics.App;
import com.journaldev.androidmvvmbasics.db.dao.UserDao;
import com.journaldev.androidmvvmbasics.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class UserRepository {

    public static LiveData<List<User>> getAllUser() throws ExecutionException, InterruptedException {
       return new GetAllUserAsync().execute().get();
    }

    public static void insertUser(User user){
        new InsertUserAsync().execute(user);
    }

    private static class GetAllUserAsync extends AsyncTask<Void,Void,LiveData<List<User>>>{
        private UserDao mUserDao;

        GetAllUserAsync(){
            mUserDao = App.getUserDatabase().getUserDao();
        }

        @Override
        protected LiveData<List<User>> doInBackground(Void... voids) {
            return  mUserDao.getAll();
        }
    }

    private static class InsertUserAsync extends AsyncTask<User,Void,Void>{
        private UserDao mUserDao;

        InsertUserAsync() {
            mUserDao = App.getUserDatabase().getUserDao();
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }
}
