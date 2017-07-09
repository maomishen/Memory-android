package com.maomishen.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class CreateMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_memory_activity);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_create_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        } else if (item.getItemId() == R.id.create_new_done) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
