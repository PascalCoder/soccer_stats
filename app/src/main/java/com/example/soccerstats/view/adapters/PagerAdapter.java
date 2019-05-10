package com.example.soccerstats.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.soccerstats.model.Leagues;
import com.example.soccerstats.view.activities.MainActivity;
import com.example.soccerstats.view.fragments.LigaFragment;
import com.example.soccerstats.view.fragments.PremierLeagueFragment;
import com.example.soccerstats.view.fragments.SerieAFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MainActivity.LEAGUE = Leagues.LIGA;
                LigaFragment tab1 = LigaFragment.newInstance();
                return tab1;
            case 1:
                MainActivity.LEAGUE = Leagues.PREMIER_LEAGUE;
                PremierLeagueFragment tab2 = PremierLeagueFragment.newInstance();
                return tab2;
            case 2:
                SerieAFragment tab3 = SerieAFragment.newInstance();
                MainActivity.LEAGUE = Leagues.SERIE_A;
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
