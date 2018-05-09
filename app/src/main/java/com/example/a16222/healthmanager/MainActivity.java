package com.example.a16222.healthmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a16222.healthmanager.fragment.BaseFragment;
import com.example.a16222.healthmanager.ui.BottomNavigationViewHelper;
import com.example.a16222.healthmanager.ui.ViewPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_more:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewpager);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = navigation.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BaseFragment.newInstance("Home"));
        adapter.addFragment(BaseFragment.newInstance("Dashboard"));
        adapter.addFragment(BaseFragment.newInstance("Notifications"));
        adapter.addFragment(BaseFragment.newInstance("More"));
        viewPager.setAdapter(adapter);
    }
}
