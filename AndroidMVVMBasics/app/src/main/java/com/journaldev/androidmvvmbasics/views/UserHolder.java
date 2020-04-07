package com.journaldev.androidmvvmbasics.views;

import android.view.View;

import com.journaldev.androidmvvmbasics.databinding.UserViewBinding;
import com.journaldev.androidmvvmbasics.db.repository.UserRepository;
import com.journaldev.androidmvvmbasics.model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class UserHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        UserViewBinding mViewBinding;


        public UserHolder(@NonNull UserViewBinding itemView) {
            super(itemView.getRoot());
            mViewBinding = itemView;
            mViewBinding.getRoot().setOnLongClickListener(this);
        }

        public void bind(User user){
            mViewBinding.setUser(user);
        }

        @Override
        public boolean onLongClick(View v) {
            User user = mViewBinding.getUser();
            UserRepository.deleteUser(user);
            //activityMainBinding.getViewModel().deleteUser(user);
            return false;
        }
    }
