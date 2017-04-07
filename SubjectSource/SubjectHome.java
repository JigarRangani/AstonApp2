package com.example.dell.astonapp.SubjectSource;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.astonapp.Adapter.SectionPagerAdapterForSubject;
import com.example.dell.astonapp.R;
import com.example.dell.astonapp.Adapter.SectionsPagerAdapter;
import com.example.dell.astonapp.helper.SQLiteHandler;

public class SubjectHome extends AppCompatActivity implements TabLayout.OnTabSelectedListener{


    private SectionPagerAdapterForSubject mSectionsPagerAdapter;
    TextView set_Title_Subject;
    TextView hello;
    private ViewPager mViewPager;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_home);

        set_Title_Subject = (TextView) findViewById(R.id.headingofsubject);
        hello = (TextView) findViewById(R.id.just);

        db = new SQLiteHandler(getApplicationContext());

        Intent i = getIntent();
        String s1 = i.getStringExtra("subject");
        set_Title_Subject.setText(s1.toString());

        String s2 = null;
        s2 = db.getCourseid(s1.toString());
        hello.setText(s2.toString());
       // Toast.makeText(getApplicationContext(),s2.,Toast.LENGTH_LONG).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabforsubject);
        tabLayout.addTab(tabLayout.newTab().setText("Assignment"));
        tabLayout.addTab(tabLayout.newTab().setText("Lecture"));
        tabLayout.addTab(tabLayout.newTab().setText("Post"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) findViewById(R.id.pagerforsubject);
        mSectionsPagerAdapter = new SectionPagerAdapterForSubject(getSupportFragmentManager(),tabLayout.getTabCount());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subject_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
