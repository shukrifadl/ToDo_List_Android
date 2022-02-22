package com.example.android.todo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.todo.MainActivity;
import com.example.android.todo.databinding.TaskItemBinding;
import com.example.android.todo.db.Task;
import com.example.android.todo.fragments.HomeFragmentDirections;
import com.example.android.todo.viewmodels.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class TaskListAdapter extends ListAdapter<Task, ItemViewHolder> {

    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        Task current = getItem(position);
        holder.bind(current);
    }

    public static class TaskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.id==newItem.id;
        }
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    private final TaskItemBinding Item;

    private ItemViewHolder(TaskItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.Item = itemBinding;
    }

    public void bind(Task model) {
        Item.setTask(model);
        Item.setViewModel(MainActivity.viewModel);
        itemView.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(
                    HomeFragmentDirections.actionHomeFragmentToTaskDetailsFragment(model.id));
        });
    }

    static ItemViewHolder create(ViewGroup parent) {

        return new ItemViewHolder(
                TaskItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                )
        );

    }
}