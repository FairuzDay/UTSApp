package com.unikom.ujiantengahsemester.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unikom.ujiantengahsemester.database.FriendRepo;
import com.unikom.ujiantengahsemester.models.Friends;

import java.util.List;

public class FriendViewModel extends AndroidViewModel {
    private FriendRepo mFriendRepo;
    private LiveData<List<Friends>> mAllFriends;

    public FriendViewModel(@NonNull Application application) {
        super(application);
        this.mFriendRepo = new FriendRepo(application);
        this.mAllFriends = mFriendRepo.getAllFriends();
    }

    public LiveData<List<Friends>> getAllFriends() {
        return mAllFriends;
    }

    public void insertFriend(Friends friends){mFriendRepo.insertFriend(friends);}

    public void updateFriend(Friends friends){mFriendRepo.updateFriend(friends);}

    public void removeFriend(Friends friends){mFriendRepo.deleteFriend(friends);}
}
