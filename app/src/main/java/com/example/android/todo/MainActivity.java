package com.example.android.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.todo.databinding.ActivityMainBinding;
import com.example.android.todo.viewmodels.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    public static TaskViewModel viewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }

}