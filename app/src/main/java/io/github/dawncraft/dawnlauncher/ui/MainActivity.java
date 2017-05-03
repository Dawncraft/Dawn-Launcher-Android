package io.github.dawncraft.dawnlauncher.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import io.github.dawncraft.dawnlauncher.R;
import io.github.dawncraft.dawnlauncher.utils.Util;

public class MainActivity extends AppCompatActivity
{
    private Fragment fragmentHome;
    private Fragment fragmentGames;
    private Fragment fragmentFriends;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        // Set tab bar
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(true)
                .setText("new")
                .setBackgroundColorResource(R.color.colorAccent)
                .setBorderWidth(0);
        BottomNavigationBar bottomBar = (BottomNavigationBar) findViewById(R.id.main_tabbar);
        TabListener tabListener = new TabListener();
        bottomBar.setBarBackgroundColor(R.color.white)
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, R.string.tab_home).setActiveColorResource(R.color.colorLight))
                .addItem(new BottomNavigationItem(R.mipmap.ic_games, R.string.tab_games).setActiveColorResource(R.color.colorLight).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_friends, R.string.tab_friends).setActiveColorResource(R.color.colorLight))
                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setTabSelectedListener(tabListener)
                .initialise();
        // Set menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Add menu items
        ListView listview = (ListView) findViewById(R.id.menu_list);
        listview.setAdapter(new ArrayAdapter<>(this, R.layout.list_item_menu,
                getResources().getStringArray(R.array.menu_items)));
        listview.setOnItemClickListener(new MenuListener());
        // Set main content
        tabListener.onTabSelected(0);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Util.toast(this, R.string.exit);
                exitTime = System.currentTimeMillis();
            }
            else
            {
                super.onBackPressed();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    private class MenuListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            switch(position)
            {
                default: Util.toast(MainActivity.this, "错误,无此项目:" + position); break;
                case 0: Util.toast(MainActivity.this, "您选择了下载管理:" + position); break;
                case 1: startActivity(new Intent(MainActivity.this, SettingsActivity.class)); break;
                case 2: android.os.Process.killProcess(android.os.Process.myPid()); break;
            }
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private class TabListener implements BottomNavigationBar.OnTabSelectedListener
    {
        @Override
        public void onTabSelected(int position)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (position)
            {
                case 0:
                    if (fragmentHome == null) fragmentHome = new HomeFragment();
                    transaction.replace(R.id.main_content, fragmentHome);
                    break;
                case 1:
                    if (fragmentGames == null) fragmentGames = new GamesFragment();
                    transaction.replace(R.id.main_content, fragmentGames);
                    break;
                case 2:
                    if (fragmentFriends == null) fragmentFriends = new FriendsFragment();
                    transaction.replace(R.id.main_content, fragmentFriends);
                    break;
            }
            transaction.commit();
        }

        @Override
        public void onTabUnselected(int position) {}

        @Override
        public void onTabReselected(int position) {}
    }
}
