package com.unikom.ujiantengahsemester.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.unikom.ujiantengahsemester.dao.FriendDao;
import com.unikom.ujiantengahsemester.models.Friends;

@Database(entities = {Friends.class}, version = 1)
public abstract class FriendDB extends RoomDatabase {

    public abstract FriendDao friendDao();

    private static volatile FriendDB INSTANCE;

    static FriendDB getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (FriendDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FriendDB.class, "friend_database")
                            .addCallback(sRoomDbCallbak)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDbCallbak = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final FriendDao mDao;

        PopulateDbAsync(FriendDB db) {
            mDao = db.friendDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Friends friends = new Friends(
                    "10116567",
                    "Frangky Michael",
                    "IF-13",
                    "089680517559",
                    "frangky97@gmail.com",
                    "@frangkymichael");
            mDao.insert(friends);
            friends = new Friends(
                    "100516238",
                    "Awan Gustiawan",
                    "IS-06",
                    "089922716231",
                    "awan@gmail.com",
                    "@awangustiawan");
            mDao.insert(friends);
            friends = new Friends(
                    "10116561",
                    "Egi Widianto",
                    "IF-13",
                    "08218261628",
                    "egiwidianto@gmail.com",
                    "@egiwidianto");
            mDao.insert(friends);
            return null;
        }
    }
}
