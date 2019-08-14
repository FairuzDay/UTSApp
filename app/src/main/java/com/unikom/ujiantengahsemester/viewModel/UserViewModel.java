package com.unikom.ujiantengahsemester.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unikom.ujiantengahsemester.database.FriendRepo;
import com.unikom.ujiantengahsemester.database.UserRepo;
import com.unikom.ujiantengahsemester.models.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepo mUserRepo;
    private LiveData<List<User>> mAllUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.mUserRepo = new UserRepo(application);
        this.mAllUser = mUserRepo.getmAllUser();
    }

    public LiveData<List<User>> getAllUser() {
        return mAllUser;
    }

    public void insertUser(User user){mUserRepo.insertUser(user);}

    public void updateUser(User user){mUserRepo.updateUser(user);}

    public void removeUser(User user){mUserRepo.deleteUser(user);}
}
