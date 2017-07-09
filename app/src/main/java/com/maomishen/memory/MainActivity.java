package com.maomishen.memory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.maomishen.memory.fragment.ArchiveFragment;
import com.maomishen.memory.fragment.BaseFragment;
import com.maomishen.memory.fragment.MemoryFragment;
import com.maomishen.memory.fragment.RecycleFragment;

import static com.maomishen.memory.R.id.fab;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentInteractionListener {

    FloatingActionButton addFab;
    Toolbar toolbar;
    MemoryFragment memoryFragment;
    ArchiveFragment archiveFragment;
    RecycleFragment recycleFragment;
    int selected_page_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addFab = (FloatingActionButton) findViewById(fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMemoryActivity(view);
            }
        });

        initDrawerLayout();
        initFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        changeFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAddMemoryActivity(View view) {
        Intent intent = new Intent(this, CreateMemoryActivity.class);
        startActivity(intent);
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }

    private void initDrawerLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        selected_page_id = R.id.nav_doing;
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        memoryFragment = MemoryFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.id_fragment_container,memoryFragment).commit();
    }

    private void changeFragment(int itemId) {
        if (itemId == selected_page_id) {
            return;
        }
        selected_page_id = itemId;
        if (itemId == R.id.nav_user_center) {
            addFab.setVisibility(View.INVISIBLE);
            return;
        } else if (itemId == R.id.nav_about_us) {
            addFab.setVisibility(View.INVISIBLE);
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (itemId == R.id.nav_doing) {
            memoryFragment = (MemoryFragment) checkFragment(fragmentTransaction, memoryFragment, MemoryFragment.class);
            fragmentTransaction.show(memoryFragment);
            addFab.setVisibility(View.VISIBLE);
            toolbar.setTitle(R.string.title_memory_doing);
        } else if (itemId == R.id.nav_archive) {
            archiveFragment = (ArchiveFragment) checkFragment(fragmentTransaction, archiveFragment, ArchiveFragment.class);
            fragmentTransaction.show(archiveFragment);
            addFab.setVisibility(View.INVISIBLE);
            toolbar.setTitle(R.string.title_memory_archive);
        } else if (itemId == R.id.nav_recycle) {
            recycleFragment = (RecycleFragment) checkFragment(fragmentTransaction, recycleFragment, RecycleFragment.class);
            fragmentTransaction.show(recycleFragment);
            addFab.setVisibility(View.INVISIBLE);
            toolbar.setTitle(R.string.title_memory_recycle);
        }
        fragmentTransaction.commit();
    }

    private BaseFragment checkFragment(FragmentTransaction fragmentTransaction, BaseFragment fragment, Class<?> cls) {
        if (fragment == null) {
            try {
                fragment = (BaseFragment)cls.getMethod("newInstance", new Class[]{}).invoke(null, new Object[]{});
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
            fragmentTransaction.add(R.id.id_fragment_container,fragment);
        }

        if (memoryFragment != null) fragmentTransaction.hide(memoryFragment);
        if (archiveFragment != null) fragmentTransaction.hide(archiveFragment);
        if (recycleFragment != null) fragmentTransaction.hide(recycleFragment);

        return fragment;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
