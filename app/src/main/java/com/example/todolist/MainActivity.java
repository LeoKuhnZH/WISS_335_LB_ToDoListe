package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskActionListener {
    private ListView listViewTasks;
    private TaskAdapter adapter;
    private List<Task> taskList;
    private String currentDbHash = "";
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTasks = findViewById(R.id.listViewTasks);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(this, taskList, this);
        listViewTasks.setAdapter(adapter);

        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });

        // Periodically check for database updates every 3 seconds using a lightweight SQL checksum
        scheduler.scheduleWithFixedDelay(this::checkSync, 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public void onTaskStatusChanged(Task task) {
        final android.content.Context appContext = getApplicationContext();
        scheduler.execute(() -> {
            AppDatabase.getInstance(appContext).taskDao().update(task);
            // Manually trigger a sync check immediately after update
            checkSync();
        });
    }

    @Override
    public void onTaskDelete(Task task) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    final android.content.Context appContext = getApplicationContext();
                    scheduler.execute(() -> {
                        AppDatabase.getInstance(appContext).taskDao().delete(task);
                        checkSync();
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        forceRefresh();
    }

    private void checkSync() {
        if (isFinishing() || isDestroyed()) return;
        
        final android.content.Context appContext = getApplicationContext();
        String newHash = AppDatabase.getInstance(appContext).taskDao().getDatabaseHash();
        if (!newHash.equals(currentDbHash)) {
            forceRefresh();
            currentDbHash = newHash;
        }
    }

    private void forceRefresh() {
        if (isFinishing() || isDestroyed()) return;

        final android.content.Context appContext = getApplicationContext();
        scheduler.execute(() -> {
            List<Task> newTasks = AppDatabase.getInstance(appContext).taskDao().getAll();
            runOnUiThread(() -> {
                if (isFinishing() || isDestroyed()) return;
                taskList.clear();
                taskList.addAll(newTasks);
                adapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduler.shutdown();
    }
}
