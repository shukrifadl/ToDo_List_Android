package com.example.android.todo.viewmodels;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.android.todo.R;
import com.example.android.todo.db.Task;
import com.example.android.todo.repo.TasksRepo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TasksRepo repo;


    private final LiveData<List<Task>> allTasks;
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> body = new MutableLiveData<>();

    public TaskViewModel(@NonNull @NotNull Application application) {
        super(application);
        repo = new TasksRepo(application);
        allTasks = repo.getTasks();
    }


    public void updateTask(int taskId) {
        repo.updateTask(new Task(taskId, title.getValue(), body.getValue(), false));
    }

    public void addTask() {
        if (title.getValue() != null) {

            repo.insertTask(new Task(0, title.getValue(), body.getValue(), false));

        }
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        repo.insertTasks(task);
    }

    public LiveData<Task> getTask(int taskId) {
        return repo.getTask(taskId);
    }

    public void completeTask(Task task, boolean completed) {

        if (completed) {
            repo.updateCompleted(task.id, completed);
            Toast.makeText(getApplication().getApplicationContext(), R.string.task_marked_complete, Toast.LENGTH_SHORT);
        } else {
            repo.updateCompleted(task.id, completed);
            Toast.makeText(getApplication().getApplicationContext(), R.string.task_marked_active, Toast.LENGTH_SHORT);
        }
    }
}
