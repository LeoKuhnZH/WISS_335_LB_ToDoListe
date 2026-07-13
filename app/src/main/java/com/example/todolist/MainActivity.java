package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
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
        adapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(adapter);

        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });

        // Periodically check for database updates every 3 seconds using a lightweight SQL checksum
        scheduler.scheduleWithFixedDelay(this::checkSync, 0, 3, TimeUnit.SECONDS);
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
