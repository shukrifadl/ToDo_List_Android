package com.example.android.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.todo.MainActivity;
import com.example.android.todo.databinding.TaskDetailsFragmentBinding;
import com.example.android.todo.db.Task;
import com.example.android.todo.viewmodels.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class TaskDetailsFragment extends Fragment {

    private TaskViewModel mViewModel;
    private TaskDetailsFragmentBinding binding;

    public static TaskDetailsFragment newInstance() {
        return new TaskDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TaskDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = MainActivity.viewModel;
        int taskId=  TaskDetailsFragmentArgs.fromBundle(getArguments()).getTaskId();
        LiveData<Task> task = mViewModel.getTask(taskId);
        task.observe(this, it -> {
            binding.setTask(it);
        });
    }

}