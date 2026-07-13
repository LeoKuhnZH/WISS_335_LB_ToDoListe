package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }

        Task task = getItem(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewPriority = convertView.findViewById(R.id.textViewPriority);
        TextView textViewDueDate = convertView.findViewById(R.id.textViewDueDate);
        CheckBox checkBoxDone = convertView.findViewById(R.id.checkBoxDone);

        if (task != null) {
            textViewTitle.setText(task.getTitle());
            textViewPriority.setText("Priority: " + task.getPriority());
            textViewDueDate.setText("Due: " + task.getDueDate());
            checkBoxDone.setChecked(task.isDone());
        }

        return convertView;
    }
}
