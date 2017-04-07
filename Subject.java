package com.example.dell.astonapp;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.astonapp.Adapter.SubjectAdapter;
import com.example.dell.astonapp.Model_classis.Subject_Model;
import com.example.dell.astonapp.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Subject extends Fragment {

    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    static boolean isDataLoaded = false;

    RecyclerView.Adapter recyclerViewadapter;
   // private SubjectAdapter adapter;
    private List<Subject_Model> subject_modelList1;
    private SQLiteHandler db;


    String JSON_ID2 = "id";
    String JSON_TITLE = "title";
    String JSON_CODE = "code";
    String JSON_SEM = "sem";
    String JSON_DEP_ID = "department_id";
    String JSON_CREDIT = "credit";

    Button button;

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    ProgressBar progressBar;

    public Subject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.subject, container, false);

     //   progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        db = new SQLiteHandler(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        subject_modelList1 = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(db == null){
            db = new SQLiteHandler(getContext());
        }


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDataLoaded){
                    recyclerViewadapter = new SubjectAdapter(getContext(),db.getAllCourses());


                    recyclerView.setAdapter(recyclerViewadapter);


                }
                else {

                    new LoadCources().execute();
                }
            }
        });


       return view;
    }

    private class LoadCources extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading Cources ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            db = new SQLiteHandler(getContext());
            // Fetching user details from sqlite
            HashMap<String, String> user = db.getUserDetails();
            final String token = user.get("uid");

            jsonArrayRequest = new JsonArrayRequest("http://192.168.0.110/astonpro/public/api/user/courses/enrolled?token=" + token,

                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {


                         //   progressBar.setVisibility(View.GONE);

                            JSON_PARSE_DATA_AFTER_WEBCALL(response);
                        }

                        private void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {
                            if(isDataLoaded == false) {
                                for (int i = 0; i < array.length(); i++) {

                                    Subject_Model subject_model2 = new Subject_Model();
                                    JSONObject json = null;

                                    try {
                                        json = array.getJSONObject(i);
                                        subject_model2.setId2(json.getInt(JSON_ID2));
                                        subject_model2.setTitle(json.getString(JSON_TITLE));
                                        subject_model2.setSem(json.getInt(JSON_SEM));
                                        subject_model2.setSubject_code(json.getInt(JSON_CODE));
                                        subject_model2.setDep_id(json.getInt(JSON_DEP_ID));
                                        subject_model2.setCredit(json.getInt(JSON_CREDIT));

                                        JSONObject pivot = json.getJSONObject("pivot");
                                        subject_model2.setEnrollid(pivot.getInt("course_id"));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // subject_modelList1.add(subject_model2);
                                    db.addCourses(subject_model2);

                                }
                                recyclerViewadapter = new SubjectAdapter(getContext(), db.getAllCourses());
                                recyclerView.setAdapter(recyclerViewadapter);
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueue = Volley.newRequestQueue(getContext());

            requestQueue.add(jsonArrayRequest);
            return null;
        }
        protected void onPostExecute(String args) {
            // dismiss the dialog after getting all products
            isDataLoaded = true;
            recyclerViewadapter = new SubjectAdapter(getContext(), db.getAllCourses());
            recyclerView.setAdapter(recyclerViewadapter);
            pDialog.dismiss();


        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if ( db != null )
        {
            db.close();
        }
    }
}
