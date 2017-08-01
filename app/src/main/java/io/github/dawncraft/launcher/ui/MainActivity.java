package io.github.dawncraft.launcher.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomNavigationView;

import io.github.dawncraft.launcher.R;
import io.github.dawncraft.launcher.utils.Util;

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
        this.setContentView(R.layout.activity_main);
        this.fragmentHome = new HomeFragment();
        this.fragmentGames = new GamesFragment();
        this.fragmentFriends = new FriendsFragment();
        // Set tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        this.setSupportActionBar(toolbar);
        // Set menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Add menu items
        ListView listview = (ListView) findViewById(R.id.menu_list);
        listview.setAdapter(new ArrayAdapter<>(this, R.layout.list_item_menu,
                getResources().getStringArray(R.array.menu_items)));
        listview.setOnItemClickListener(new MenuListener());
        // Set tab bar(Don't forget to add BadgeItem :)
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.main_tabbar);
        bottomNavigation.setOnNavigationItemSelectedListener(new TabListener());
        bottomNavigation.setSelectedItemId(R.id.menu_home);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
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
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
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

    private class TabListener implements BottomNavigationView.OnNavigationItemSelectedListener
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.main_tabbar);
            bottomNavigation.getMenu().getItem(0).setChecked(false);
            bottomNavigation.getMenu().getItem(1).setChecked(false);
            bottomNavigation.getMenu().getItem(2).setChecked(false);
            item.setChecked(true);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId())
            {
                case R.id.menu_home:
                    transaction.replace(R.id.main_content_layout, fragmentHome);
                    break;
                case R.id.menu_games:
                    transaction.replace(R.id.main_content_layout, fragmentGames);
                    break;
                case R.id.menu_friends:
                    transaction.replace(R.id.main_content_layout, fragmentFriends);
                    break;
            }
            transaction.commit();
            return false;
        }
    }
}
