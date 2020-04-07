package com.journaldev.androidmvvmbasics.views;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.journaldev.androidmvvmbasics.R;
import com.journaldev.androidmvvmbasics.databinding.ActivityMainBinding;
import com.journaldev.androidmvvmbasics.databinding.UserViewBinding;
import com.journaldev.androidmvvmbasics.db.repository.UserRepository;
import com.journaldev.androidmvvmbasics.model.User;
import com.journaldev.androidmvvmbasics.viewmodels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private LiveData <List<User>> mLiveData;
    private UserAdapter userAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAdapter = new UserAdapter(this);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(viewModel);
        //activityMainBinding.userRecyclerView.setAdapter(userAdapter);
        activityMainBinding.setAdapter(userAdapter);
        activityMainBinding.executePendingBindings();

        mLiveData = viewModel.getUserLiveData();
        mLiveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setUserList(users);
                System.out.println("!!!" + users);
            }
        });

    }

    @BindingAdapter({"toastMessageAdapter"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }


}
