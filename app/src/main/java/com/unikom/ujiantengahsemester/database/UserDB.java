package com.unikom.ujiantengahsemester.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.unikom.ujiantengahsemester.R;
import com.unikom.ujiantengahsemester.dao.UserDao;
import com.unikom.ujiantengahsemester.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDB extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDB INSTANCE;

    static UserDB getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (UserDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDB.class, "user_database")
                            .addCallback(sRoomDbCallbak)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDbCallbak = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        PopulateDbAsync(UserDB db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            User usr = new User(
                    "10116568",
                    "Muh. Fairuz Hadi Day",
                    "IF-13",
                    "The Quiter you become, the more you are able to hear",
                    String.valueOf(R.drawable.fotoku),
                    "fairuz@icloud.com ",
                    "008124116898",
                    "FB / IG : FairuzDay",
                    "fairuzday",
                    "fairuzday"
                    );
            mDao.insert(usr);
            return null;
        }
    }
}
