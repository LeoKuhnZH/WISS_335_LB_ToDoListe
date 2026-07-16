package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT COALESCE(SUM(id + done), 0) || '-' || COUNT(*) FROM tasks")
    String getDatabaseHash();
}
