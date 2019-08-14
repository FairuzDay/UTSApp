package com.unikom.ujiantengahsemester.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.unikom.ujiantengahsemester.dao.UserDao;
import com.unikom.ujiantengahsemester.models.User;

import java.util.List;

public class UserRepo {
    private UserDao userDao;
    private LiveData<List<User>> mAllUser;

    public UserRepo(Application application) {
        UserDB db = (UserDB) UserDB.getDatabase(application);
        this.userDao = db.userDao();
        this.mAllUser = userDao.getAllUser();
    }

    public LiveData<List<User>> getmAllUser() {
        return mAllUser;
    }

    public void insertUser(User user){
        new insertAsync(userDao).execute(user);
    }

    public void updateUser(User user){
        new updateAsync(userDao).execute(user);
    }

    public void deleteUser(User user){
        new removeAsync(userDao).execute(user);
    }

    private static class insertAsync extends AsyncTask<User, Void, Void>{
        private UserDao mAsyncTakskDao;
        insertAsync(UserDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTakskDao.insert(users[0]);
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<User, Void, Void>{
        private UserDao mAsyncTakskDao;
        updateAsync(UserDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTakskDao.update(users[0]);
            return null;
        }
    }

    private static class removeAsync extends AsyncTask<User, Void, Void>{
        private UserDao mAsyncTakskDao;
        removeAsync(UserDao dao){
            mAsyncTakskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTakskDao.delete(users[0]);
            return null;
        }
    }
}
