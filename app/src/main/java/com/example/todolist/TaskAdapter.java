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
    
    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewPriority;
        TextView textViewDueDate;
        CheckBox checkBoxDone;
    }

    public TaskAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
            viewHolder.textViewPriority = convertView.findViewById(R.id.textViewPriority);
            viewHolder.textViewDueDate = convertView.findViewById(R.id.textViewDueDate);
            viewHolder.checkBoxDone = convertView.findViewById(R.id.checkBoxDone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = getItem(position);

        if (task != null) {
            viewHolder.textViewTitle.setText(task.getTitle());
            viewHolder.textViewPriority.setText("Priority: " + task.getPriority());
            viewHolder.textViewDueDate.setText("Due: " + task.getDueDate());
            viewHolder.checkBoxDone.setChecked(task.isDone());
        }

        return convertView;
    }
}
