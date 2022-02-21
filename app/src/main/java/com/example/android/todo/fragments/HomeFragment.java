package com.example.android.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android.todo.MainActivity;
import com.example.android.todo.adapter.TaskListAdapter;
import com.example.android.todo.databinding.HomeFragmentBinding;
import com.example.android.todo.viewmodels.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeFragmentBinding binding;
    private TaskListAdapter adapter;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = MainActivity.viewModel;
        setupFab();
        setUpRecyclerView();
    }

    private void setupFab() {
        binding.fab.setOnClickListener(
                view -> {
                    Navigation.findNavController(view).
                            navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment());
                });
    }

    private void setUpRecyclerView() {
        adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), list -> {
            adapter.submitList(list);
        });
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);

    }


}