
package com.stadiumplayers.stadium.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.adapters.AdapterNavDrawer;
import com.stadiumplayers.stadium.common.Constants;
import com.stadiumplayers.stadium.dialogs.DialogLogin.OnDialogLoginListener;
import com.stadiumplayers.stadium.fragments.FragmentEventDetailed;
import com.stadiumplayers.stadium.fragments.FragmentMain;
import com.stadiumplayers.stadium.fragments.FragmentMap;
import com.stadiumplayers.stadium.fragments.FragmentMapModule;
import com.stadiumplayers.stadium.fragments.FragmentSearch;
import com.stadiumplayers.stadium.fragments.FragmentSearch.OnSearchButtonClickedListener;
import com.stadiumplayers.stadium.fragments.FragmentSearchResults;
import com.stadiumplayers.stadium.fragments.FragmentSearchResults.OnSearchResultClickedListener;
import com.stadiumplayers.stadium.models.NavDrawer;
import com.stadiumplayers.stadium.models.Sport;
import com.stadiumplayers.stadium.models.SportGame;

public class ActivityMain extends FragmentActivity implements OnClickListener,
        OnDialogLoginListener, OnSearchButtonClickedListener, OnSearchResultClickedListener {

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

        case SEARCH_GAMES:
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, new FragmentSearch()).commit();
            break;

        case NEAR_ME:
            Fragment fragmentNearMe = FragmentMapModule.newInstance(Constants.POSITION_E5_WATERLOO);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragmentNearMe,
                            FragmentMap.class.getSimpleName()).commit();
            break;

        case SUGGESTED:
            Fragment fragmentEventDetailed = FragmentEventDetailed.newInstance(SportGame
                    .getGenerated().get(0));

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragmentEventDetailed,
                            FragmentEventDetailed.class.getSimpleName()).commit();
            break;

        case UPCOMING:
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, new FragmentSearchResults(),
                            FragmentSearchResults.class.getSimpleName()).commit();
            break;

        case CREATE_EVENT:
            // TODO: check for and prompt login if necessary
            break;
        default:
            break;
        }

        setTitle(currentNavDrawerItem.getTitle());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ActivityMain.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchButtonClicked() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new FragmentSearchResults(),
                        FragmentSearchResults.class.getSimpleName()).addToBackStack(null).commit();
    }

    @Override
    public void onSearchResultClicked(SportGame sportGame) {

        Fragment fragment = FragmentEventDetailed.newInstance(sportGame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment,
                        FragmentEventDetailed.class.getSimpleName()).addToBackStack(null).commit();

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
