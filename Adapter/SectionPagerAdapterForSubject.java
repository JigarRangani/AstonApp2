package com.example.dell.astonapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.astonapp.Result;
import com.example.dell.astonapp.Subject;
import com.example.dell.astonapp.SubjectSource.SubjectSubClasses.Assignment;
import com.example.dell.astonapp.SubjectSource.SubjectSubClasses.Lecture;
import com.example.dell.astonapp.SubjectSource.SubjectSubClasses.Post;

/**
 * Created by DELL on 08-02-2017.
 */

public class SectionPagerAdapterForSubject extends FragmentStatePagerAdapter {
    int tabCount;
    public SectionPagerAdapterForSubject(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Assignment tab3 = new Assignment();
                return tab3;
            case 1:
                Lecture tab4 = new Lecture();
                return tab4;
            case 2:
                Post tab5 = new Post();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
