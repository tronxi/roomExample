package com.example.roomexample.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM " + Result.TABLE_NAME)
    List<Result> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insert(Result... results);

    @Query("DELETE FROM " + Result.TABLE_NAME)
    void deleteAll();

}
