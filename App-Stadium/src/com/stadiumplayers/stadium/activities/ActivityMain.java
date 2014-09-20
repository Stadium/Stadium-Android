
package com.stadiumplayers.stadium.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.adapters.AdapterNavDrawer;
import com.stadiumplayers.stadium.dialogs.DialogLogin.OnDialogLoginListener;
import com.stadiumplayers.stadium.fragments.FragmentMain;
import com.stadiumplayers.stadium.fragments.FragmentMap;
import com.stadiumplayers.stadium.models.NavDrawer;

public class ActivityMain extends FragmentActivity implements OnClickListener,
        OnDialogLoginListener, Session.StatusCallback {

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    private NavDrawer[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private DrawerListener mDrawerListener;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getTitle();
        mDrawerTitle = getTitle();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, new FragmentMain(),
                            FragmentMain.class.getSimpleName()).commit();
        }

        // Drawer intialization
        mDrawerItems = NavDrawer.values();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_nav_drawer);
        mDrawerList = (ListView) findViewById(R.id.main_list_drawer);

        // Bind data to listview
        AdapterNavDrawer adapter = new AdapterNavDrawer(this);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerClickListener());

        mDrawerListener = new DrawerListener();
        mDrawerLayout.setDrawerListener(mDrawerListener);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerListener.syncState();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerListener extends ActionBarDrawerToggle {

        public DrawerListener() {
            super(ActivityMain.this, mDrawerLayout, R.drawable.ic_drawer, 0, 0);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            getActionBar().setTitle(mDrawerTitle);
            invalidateOptionsMenu();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            getActionBar().setTitle(mTitle);
            invalidateOptionsMenu();
        }

    }

    private class DrawerClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectDrawerItem(position);
        }
    }

    private void selectDrawerItem(int position) {
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawers();
        NavDrawer currentNavDrawerItem = mDrawerItems[position];

        switch (currentNavDrawerItem) {

        case PROFILE:
            // Do nothing
            break;

        case FIND_EVENT:
            Fragment fragment = new FragmentMap();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment,
                            FragmentMap.class.getSimpleName()).commit();
            break;

        case CREATE_EVENT:
            // TODO: check for and prompt login if necessary
            break;
        }

        setTitle(currentNavDrawerItem.getTitle());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ActivityMain.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void call(Session session, SessionState state, Exception exception) {
        if (session.isOpened()) {

        }
    }

    @Override
    public void onLoginSuccessful() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onLoginFailure() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onKeepBrowsing() {
        // TODO Auto-generated method stub
    }

}
