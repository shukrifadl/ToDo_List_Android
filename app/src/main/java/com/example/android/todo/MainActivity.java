package com.example.android.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.todo.viewmodels.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    public static TaskViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }
}