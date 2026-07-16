package com.example.todolist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
    
    public interface OnTaskActionListener {
        void onTaskStatusChanged(Task task);
        void onTaskDelete(Task task);
    }

    private final OnTaskActionListener listener;
    private final int colorGray = Color.parseColor("#888888");
    private final int colorBlack = Color.parseColor("#000000");
    private final int colorSecondary = Color.parseColor("#666666");

    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewPriority;
        TextView textViewDueDate;
        CheckBox checkBoxDone;
        View buttonDelete;
    }

    public TaskAdapter(@NonNull Context context, @NonNull List<Task> objects, OnTaskActionListener listener) {
        super(context, 0, objects);
        this.listener = listener;
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
            viewHolder.buttonDelete = convertView.findViewById(R.id.buttonDelete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = getItem(position);

        if (task != null) {
            viewHolder.textViewTitle.setText(task.getTitle());
            viewHolder.textViewPriority.setText("Priority: " + task.getPriority());
            viewHolder.textViewDueDate.setText("Due: " + task.getDueDate());
            
            // Apply visual indicators based on "done" status
            if (task.isDone()) {
                viewHolder.textViewTitle.setPaintFlags(viewHolder.textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.textViewTitle.setTextColor(colorGray);
                viewHolder.textViewPriority.setTextColor(colorGray);
                viewHolder.textViewDueDate.setTextColor(colorGray);
            } else {
                viewHolder.textViewTitle.setPaintFlags(viewHolder.textViewTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                viewHolder.textViewTitle.setTextColor(colorBlack);
                viewHolder.textViewPriority.setTextColor(colorSecondary);
                viewHolder.textViewDueDate.setTextColor(colorSecondary);
            }
            
            // Remove listener before setting state to avoid recursion during recycling
            viewHolder.checkBoxDone.setOnCheckedChangeListener(null);
            viewHolder.checkBoxDone.setChecked(task.isDone());
            
            viewHolder.checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
                task.setDone(isChecked);
                if (listener != null) {
                    listener.onTaskStatusChanged(task);
                }
            });

            viewHolder.buttonDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTaskDelete(task);
                }
            });
        }

        return convertView;
    }
}
