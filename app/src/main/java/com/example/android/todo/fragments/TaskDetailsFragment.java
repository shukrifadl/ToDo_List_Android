package com.example.android.todo.fragments;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import com.example.android.todo.MainActivity;
import com.example.android.todo.R;
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
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TaskDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private int taskId;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = MainActivity.viewModel;
        taskId = TaskDetailsFragmentArgs.fromBundle(getArguments()).getTaskId();
        LiveData<Task> task = mViewModel.getTask(taskId);
        task.observe(this, it -> {
            binding.setTask(it);
        });
        binding.fabEdit.setOnClickListener(v -> {
            Navigation.findNavController(v).
                    navigate(TaskDetailsFragmentDirections.actionTaskDetailsFragmentToUpdateFragment(taskId));
        });
        binding.fabDelete.setOnClickListener(v -> {
            mViewModel.deleteTask(binding.getTask());
            Navigation.findNavController(v).
                    navigate(TaskDetailsFragmentDirections.actionTaskDetailsFragmentToHomeFragment());
        });
    }

}