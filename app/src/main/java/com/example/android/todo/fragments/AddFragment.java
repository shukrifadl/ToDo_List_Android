package com.example.android.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.android.todo.R;
import com.example.android.todo.databinding.AddFragmentBinding;
import com.example.android.todo.viewmodels.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class AddFragment extends Fragment {


    private TaskViewModel mViewModel;
    private AddFragmentBinding binding;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        binding.setViewModel(mViewModel);
        binding.fabSave.setOnClickListener(v -> {
            if (binding.etTaskTitle.getText().length() > 0) {
                mViewModel.addTask();
                Navigation.findNavController(v).navigate(AddFragmentDirections.actionAddFragmentToHomeFragment());
                Toast.makeText(getContext(), R.string.task_added, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), R.string.cant_add_task, Toast.LENGTH_SHORT).show();
            }

        });
    }


}