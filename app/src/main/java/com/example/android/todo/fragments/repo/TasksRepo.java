package com.example.android.todo.fragments.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.android.todo.db.Task;
import com.example.android.todo.db.TaskDao;
import com.example.android.todo.db.ToDoDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TasksRepo {
    private final TaskDao tasksDao;
    private final LiveData<List<Task>> allTasks;

    public TasksRepo(@NotNull Application app) {
        ToDoDatabase db = ToDoDatabase.getDatabase(app);
        tasksDao = db.taskDao();
        allTasks = tasksDao.getAll();
    }

    public LiveData<List<Task>> getTasks() {
        return allTasks;
    }

    public void insertTasks(Task... tasks) {
        ToDoDatabase.databaseWriteExecutor.execute(() -> {
            tasksDao.insertAll(tasks);
        });
    }
    public void insertTask(Task task)
    {
        ToDoDatabase.databaseWriteExecutor.execute(() ->{
            tasksDao.insertTask(task);
        });
    }

    public LiveData<Task> getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public void updateTask(Task task) {
        ToDoDatabase.databaseWriteExecutor.execute(() -> {
            tasksDao.updateTask(task);
        });
    }

    public void delete(Task task) {
        ToDoDatabase.databaseWriteExecutor.execute(() -> {
            tasksDao.delete(task);
        });
    }

    public void updateCompleted(int id, boolean completed) {
        ToDoDatabase.databaseWriteExecutor.execute(() -> {
            tasksDao.updateCompleted(id, completed);
        });
    }

    public LiveData<Task> searchForOne(String string) {
        return tasksDao.searchForOne(string);
    }

    public LiveData<List<Task>> search(String string) {
        return tasksDao.search(string);
    }
}
