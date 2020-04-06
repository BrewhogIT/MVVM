package com.journaldev.androidmvvmbasics.views;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAdapter = new UserAdapter();
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new LoginViewModel());
        activityMainBinding.userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.userRecyclerView.setAdapter(userAdapter);
        activityMainBinding.executePendingBindings();

        try {
            mLiveData = activityMainBinding.getViewModel().getUserLiveData();
            mLiveData.observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    userAdapter.setUserList(users);
                    userAdapter.notifyDataSetChanged();
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"toastMessageAdapter"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<User> mUserList = new ArrayList<>();

        public void setUserList(List<User> userList) {
            mUserList = userList;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getBaseContext());
            UserViewBinding userViewBinding = DataBindingUtil
                    .inflate(inflater,R.layout.user_view,parent,false);
            return new UserHolder(userViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            holder.bind(mUserList.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        UserViewBinding mViewBinding;

        public UserHolder(@NonNull UserViewBinding itemView) {
            super(itemView.getRoot());
            mViewBinding = itemView;
        }

        public void bind(User user){
            mViewBinding.setUser(user);
        }
    }
}
