package com.example.android.todo.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Tasks")
public class Task {
    public Task(int id,String title, String body, boolean completed) {
        this.id=id;
        this.title = title;
        this.body = body;
        this.setCompleted(completed);
        getStatus = isCompleted() ? "Completed Task" : "Task Not Completed Yet";
    }

    public String getStatus;


    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Title")
    public String title;
    @ColumnInfo(name = "Body")
    public String body;
    @ColumnInfo(name = "Completed")
    private boolean isCompleted;

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        getStatus =
                isCompleted() ? "Completed Task" : "Task Not Completed Yet";
    }
}