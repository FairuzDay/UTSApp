package com.unikom.ujiantengahsemester.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.unikom.ujiantengahsemester.dao.FriendDao;
import com.unikom.ujiantengahsemester.database.FriendDB;
import com.unikom.ujiantengahsemester.models.Friends;

import java.util.List;

public class FriendRepo {
    private FriendDao friendDao;
    private LiveData<List<Friends>> mAllFriends;

    public FriendRepo(Application application) {
        FriendDB db = (FriendDB) FriendDB.getDatabase(application);
        this.friendDao = db.friendDao();
        this.mAllFriends = friendDao.getAllWords();
    }

    public LiveData<List<Friends>> getAllFriends() {
        return mAllFriends;
    }

    public void insertFriend(Friends friends){
        new insertAsync(friendDao).execute(friends);
    }

    public void updateFriend(Friends friends){
        new updateAsync(friendDao).execute(friends);
    }

    public void deleteFriend(Friends friends){
        new removeAsync(friendDao).execute(friends);
    }

    private static class insertAsync extends AsyncTask<Friends, Void, Void>{
        private FriendDao mAsyncTakskDao;
        insertAsync(FriendDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(Friends... friends) {
            mAsyncTakskDao.insert(friends[0]);
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<Friends, Void, Void>{
        private FriendDao mAsyncTakskDao;
        updateAsync(FriendDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(Friends... friends) {
            mAsyncTakskDao.update(friends[0]);
            return null;
        }
    }

    private static class removeAsync extends AsyncTask<Friends, Void, Void>{
        private FriendDao mAsyncTakskDao;
        removeAsync(FriendDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(Friends... friends) {
            mAsyncTakskDao.delete(friends[0]);
            return null;
        }
    }
}
