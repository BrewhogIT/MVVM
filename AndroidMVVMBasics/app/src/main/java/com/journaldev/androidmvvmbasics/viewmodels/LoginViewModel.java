package com.journaldev.androidmvvmbasics.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;

import com.journaldev.androidmvvmbasics.BR;
import com.journaldev.androidmvvmbasics.db.repository.UserRepository;
import com.journaldev.androidmvvmbasics.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginViewModel extends BaseObservable {
    private User user;
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";
    private LiveData<List<User>> userLiveData;

    @Bindable
    public String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    public LiveData<List<User>> getUserLiveData() throws ExecutionException, InterruptedException {
        if(userLiveData == null){
            userLiveData = UserRepository.getAllUser();
        }
        return userLiveData;
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel() {
        user = new User("", "");

        for (int i = 0; i < 3; i++){
            String email = "user" + i + "@mail.ru";
            String password = "password" + i;

            UserRepository.insertUser(new User(email,password));
        }
    }

    public void afterEmailTextChanged(CharSequence s) {
        user.setEmail(s.toString());
    }

    public void afterPasswordTextChanged(CharSequence s) {
        user.setPassword(s.toString());
    }

    public void onLoginClicked() {
        if (user.isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }
}