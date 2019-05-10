package com.example.soccerstats.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Leagues;
import com.example.soccerstats.view.adapters.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static String LEAGUE;
    public static String POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LEAGUE = Leagues.LIGA;

        setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText(Leagues.LIGA_TITLE));
        tabLayout.addTab(tabLayout.newTab().setText(Leagues.PREMIER_LEAGUE_TITLE));
        tabLayout.addTab(tabLayout.newTab().setText(Leagues.SERIE_A_TITLE));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter mAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0){
                    LEAGUE = Leagues.LIGA;
                    POSITION = "1";
                }else if (tab.getPosition() == 1){
                    LEAGUE = Leagues.PREMIER_LEAGUE;
                    POSITION = "2";
                }else{
                    LEAGUE = Leagues.SERIE_A;
                    POSITION = "3";
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setOnClickListener(new TabLayout.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    Log.d(TAG, "onClick: " + tabLayout.getSelectedTabPosition());
                }
            }
        });
        /*tabLayout.setOnTouchListener(new TabLayout.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //if()
        return super.onOptionsItemSelected(item);
    }
}
