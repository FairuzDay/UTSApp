package com.unikom.ujiantengahsemester.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.unikom.ujiantengahsemester.models.Friends;
import com.unikom.ujiantengahsemester.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM table_user")
    void deleteAll();

    @Query("SELECT * FROM table_user ORDER BY uname ASC")
    LiveData<List<User>> getAllUser();
}
