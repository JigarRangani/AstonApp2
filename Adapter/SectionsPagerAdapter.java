package com.example.dell.astonapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.astonapp.Result;
import com.example.dell.astonapp.Subject;

/**
 * Created by DELL on 07-02-2017.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public SectionsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Subject tab1 = new Subject();
                return tab1;
            case 1:
                Result tab2 = new Result();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
