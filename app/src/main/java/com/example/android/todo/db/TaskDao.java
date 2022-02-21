package com.example.android.todo.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Tasks")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM Tasks WHERE id=:Id Limit 1")
    LiveData<Task> getTask(int Id);

    @Query("SELECT * FROM Tasks WHERE Title LIKE :string OR Body LIKE :string LIMIT 1")
    LiveData<Task> searchForOne(String string);

    @Query("SELECT * FROM Tasks WHERE Title LIKE :string OR Body LIKE :string ")
    LiveData<List<Task>> search(String string);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Task... Tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Query("UPDATE Tasks SET Completed = :completed WHERE id = :taskId")
    void updateCompleted(int taskId, Boolean completed);

    @Delete
    int delete(Task Task);

    @Update
    int updateTask(Task task);
}
