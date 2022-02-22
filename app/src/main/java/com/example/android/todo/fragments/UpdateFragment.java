package com.example.android.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.android.todo.databinding.UpdateFragmentBinding;
import com.example.android.todo.viewmodels.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class UpdateFragment extends Fragment {

    private TaskViewModel mViewModel;
    private UpdateFragmentBinding binding;

    public static UpdateFragment newInstance() {
        return new UpdateFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UpdateFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        int taskId = UpdateFragmentArgs.fromBundle(getArguments()).getTaskId();
        mViewModel.getTask(taskId).observe(this, task -> {
            binding.etTaskTitle.setText(task.title);
            binding.edtTaskBody.setText(task.body);
        });
        binding.setViewModel(mViewModel);

        binding.fabSave.setOnClickListener(v -> {
            mViewModel.updateTask(taskId);
            Navigation.findNavController(v).navigate(UpdateFragmentDirections.actionUpdateFragmentToHomeFragment());
        });
    }
}