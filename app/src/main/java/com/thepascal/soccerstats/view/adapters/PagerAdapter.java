package com.thepascal.soccerstats.view.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thepascal.soccerstats.util.LeaguesConstants;
import com.thepascal.soccerstats.view.activities.MainActivity_2;
import com.thepascal.soccerstats.view.fragments.LigaFragment;
import com.thepascal.soccerstats.view.fragments.PremierLeagueFragment;
import com.thepascal.soccerstats.view.fragments.SerieAFragment;

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
                MainActivity_2.LEAGUE = LeaguesConstants.LIGA;
                LigaFragment tab1 = LigaFragment.newInstance();
                return tab1;
            case 1:
                MainActivity_2.LEAGUE = LeaguesConstants.PREMIER_LEAGUE;
                PremierLeagueFragment tab2 = PremierLeagueFragment.newInstance();
                return tab2;
            case 2:
                SerieAFragment tab3 = SerieAFragment.newInstance();
                MainActivity_2.LEAGUE = LeaguesConstants.SERIE_A;
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
