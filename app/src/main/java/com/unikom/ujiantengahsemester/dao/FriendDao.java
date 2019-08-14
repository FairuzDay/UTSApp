package com.unikom.ujiantengahsemester.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.unikom.ujiantengahsemester.models.Friends;

import java.util.List;
@Dao
public interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Friends friends);

    @Update
    void update(Friends friends);

    @Delete
    void delete(Friends friends);

    @Query("DELETE FROM table_friend")
    void deleteAll();

    @Query("SELECT * FROM table_friend ORDER BY fnim ASC")
    LiveData<List<Friends>> getAllWords();
}
