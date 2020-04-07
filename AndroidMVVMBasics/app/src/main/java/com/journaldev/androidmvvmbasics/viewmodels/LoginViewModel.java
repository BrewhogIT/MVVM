package com.journaldev.androidmvvmbasics.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.journaldev.androidmvvmbasics.BR;
import com.journaldev.androidmvvmbasics.db.repository.UserRepository;
import com.journaldev.androidmvvmbasics.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginViewModel extends ViewModel {
    private User user;
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";
    private LiveData<List<User>> userLiveData;

    //@Bindable
    public ObservableField<String> toastMessage = new ObservableField<>();

//    public String getToastMessage() {
//        return toastMessage;
//    }

    public LiveData<List<User>> getUserLiveData() {
        if(userLiveData == null){
            try {
                userLiveData = UserRepository.getAllUser();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return userLiveData;
    }

    public void deleteUser(User user){
        UserRepository.deleteUser(user);
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage.set(toastMessage);
        //notifyPropertyChanged(BR.toastMessage);
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
        if (user.isInputDataValid()) {
            setToastMessage(successMessage);
            UserRepository.insertUser(user);
        }
        else
            setToastMessage(errorMessage);
    }

}