package com.journaldev.androidmvvmbasics.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.journaldev.androidmvvmbasics.R;
import com.journaldev.androidmvvmbasics.databinding.UserViewBinding;
import com.journaldev.androidmvvmbasics.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<User> mUserList = new ArrayList<>();
        Context mContext;

    public UserAdapter(Context context) {
        mContext = context;
    }

    public void setUserList(List<User> userList) {
            mUserList = userList;
            this.notifyDataSetChanged();

        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            UserViewBinding userViewBinding = DataBindingUtil
                    .inflate(inflater, R.layout.user_view,parent,false);
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
