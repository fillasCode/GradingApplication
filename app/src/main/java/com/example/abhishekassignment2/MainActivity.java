package com.example.abhishekassignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiated A Drawer
        drawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);

        //Initiated A Navigation View
        navigationView = (NavigationView) findViewById (R.id.navigationView);

        //Implemented setNavigationItemSelectedListener Event
        navigationView.setNavigationItemSelectedListener(this);

        //Constructs A New ActionBarDrawerToggle.
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //Adds The Specified Listener To The List Of Listeners That Will Be Notified Of Drawer Events
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //Synchronize The State Of The Drawer
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Check For savedInstanceState == null When Adding A Fragment
        if (savedInstanceState == null)
        {
            // During Initial Setup, Plug In The Details Fragment.
            // Replace A Fragment With Frame Layout
            // Commit The Changes
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GradeEntryFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_gradeEntry);
        }
    }

    //Is Called Whenever An Item In Options Menu Is Selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Check Selected Menu Item's ID And Replace A Fragment Accordingly
        switch (item.getItemId())
        {
            case R.id.nav_gradeEntry:
                // Replace A Fragment With Frame Layout
                // Commit The Changes
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GradeEntryFragment()).commit();
                break;

            case R.id.nav_viewGrades:
                // Replace A Fragment With Frame Layout
                // Commit The Changes
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ViewGradesFragment()).commit();
                break;

            case R.id.nav_search:
                // Replace A Fragment With Frame Layout
                // Commit The Changes
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                break;
        }
        //Close The All Open Drawer Views
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}