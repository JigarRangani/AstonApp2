package com.example.dell.astonapp.SubjectSource.SubjectSubClasses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.astonapp.Adapter.AssingmentAdapter;
import com.example.dell.astonapp.Adapter.SubjectAdapter;
import com.example.dell.astonapp.Model_classis.Assignment_Model;
import com.example.dell.astonapp.Model_classis.Subject_Model;
import com.example.dell.astonapp.R;
import com.example.dell.astonapp.helper.SQLiteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Assignment extends Fragment {

    private RecyclerView recyclerView;
    private AssingmentAdapter adapter;
    private List<Assignment_Model> assignment_modelList;
    private SQLiteHandler db;


    public Assignment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_assignment);

        assignment_modelList = new ArrayList<>();
        adapter = new AssingmentAdapter(getContext(), assignment_modelList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAssingment();

        return view;
    }

    private void prepareAssingment() {

        Assignment_Model a = new Assignment_Model("DBMS first Assingment",10);
        assignment_modelList.add(a);

        a = new Assignment_Model("youtube first Assingment",11);
        assignment_modelList.add(a);

        adapter.notifyDataSetChanged();

    }

}
