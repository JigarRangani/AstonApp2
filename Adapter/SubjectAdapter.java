package com.example.dell.astonapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.astonapp.Model_classis.Subject_Model;
import com.example.dell.astonapp.R;
import com.example.dell.astonapp.Subject;
import com.example.dell.astonapp.SubjectSource.SubjectHome;

import java.util.List;

/**
 * Created by DELL on 07-02-2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {

    private Context mContext;
    private List<Subject_Model> subject_modelList;





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, sem, subject_code, credit;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.tvSubjectName);
            sem = (TextView) itemView.findViewById(R.id.tvnofAssignment);
            subject_code = (TextView) itemView.findViewById(R.id.tvnofLecture);
            credit = (TextView) itemView.findViewById(R.id.tvnofPosts);
        }

        @Override
        public void onClick(View v) {
                Intent i = new Intent(mContext, SubjectHome.class);
                i.putExtra("subject",title.getText().toString());
            //for start activity in recycler view we have to define class with start activity mind it
                mContext.startActivity(i);

        }
    }

    public SubjectAdapter(Context mContext, List<Subject_Model> subject_modelList){
        super();
        this.mContext = mContext;
        this.subject_modelList = subject_modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View iView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card, parent, false);

        return new MyViewHolder(iView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject_Model subject_model1 = subject_modelList.get(position);
        holder.title.setText(subject_model1.getTitle());
        holder.sem.setText(subject_model1.getSem() + " Semester");
        holder.subject_code.setText(subject_model1.getSubject_code() + " Subject code");
        holder.credit.setText(subject_model1.getCredit() + " Credit");

    }

    @Override
    public int getItemCount() {
        return subject_modelList.size() ;
    }


}
