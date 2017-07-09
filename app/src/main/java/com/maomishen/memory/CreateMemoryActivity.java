package com.maomishen.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_memory_activity);
        setSupportActionBar(toolbar);
    }
}
