package com.dcentralized.studywallet.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.fragments.AccountFragment;
import com.dcentralized.studywallet.fragments.AllProjectsFragment;
import com.dcentralized.studywallet.fragments.DashboardFragment;
import com.dcentralized.studywallet.fragments.ProjectsFragment;
import com.dcentralized.studywallet.fragments.RankingFragment;
import com.dcentralized.studywallet.fragments.TransferFragment;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.models.User;

/**
 * This is the main activity for the application, it handles most of the GUI logic
 *
 * @author Tom de Wildt
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Initializes activity, sets toolbar, drawer & navigation view
     *
     * @param savedInstanceState
     * @author Tom de Wildt
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerStateChanged(int newState) {
                closeKeyboard();
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set UI
        setupUI();
    }

    /**
     * Binds the event of the back button to the navigationDrawer
     *
     * @author Tom de Wildt
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Handles the click event of a navbar item
     *
     * @param item
     * @return true if successful
     * @author Tom de Wildt
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changeFragment(id);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Changes the fragment in the content_frame
     *
     * @param id of the navigation item
     * @author Tom de Wildt
     */
    public void changeFragment(int id) {
        FragmentManager manager = getFragmentManager();
        Fragment fragment;

        // Do transaction
        switch (id) {
            case R.id.nav_rankings:
                fragment = new RankingFragment();
                break;
            case R.id.nav_all_projects:
                fragment = new AllProjectsFragment();
                break;
            case R.id.nav_transfer:
                fragment = new TransferFragment();
                break;
            case R.id.nav_projects:
                fragment = new ProjectsFragment();
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                break;
            default:
                fragment = new DashboardFragment();
                break;
        }
        manager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Set Checked Item
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(id);
    }

    /**
     * Closes the software keyboard if its open
     *
     * @author Tom de Wildt
     */
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Sets up the UI
     *
     * @author Tom de Wildt
     */
    public void setupUI() {
        User user = StudyWallet.getInstance(this).getCurrentUser();
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        TextView name = header.findViewById(R.id.textName);
        TextView email = header.findViewById(R.id.textEmail);

        name.setText(String.format("%s %s", user.getFirstname(), user.getLastname()));
        email.setText(user.getEmail());

        changeFragment(R.id.nav_dashboard);
    }
}
