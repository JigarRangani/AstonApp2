package com.example.dell.astonapp.SubjectSource.SubjectSubClasses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.astonapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lecture extends Fragment {


    public Lecture() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecture, container, false);
    }

}
