package com.example.dell.astonapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.astonapp.Model_classis.Assignment_Model;
import com.example.dell.astonapp.Model_classis.Subject_Model;
import com.example.dell.astonapp.R;
import com.example.dell.astonapp.SubjectSource.SubjectHome;
import com.example.dell.astonapp.SubjectSource.SubjectSubClasses.QuestionActivity;

import java.util.List;

/**
 * Created by DELL on 09-02-2017.
 */

public class AssingmentAdapter extends RecyclerView.Adapter<AssingmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Assignment_Model> assignment_modelList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleofassingment, nofquestion,pdf,submit,update;


        public MyViewHolder(View itemView) {
            super(itemView);
            titleofassingment = (TextView) itemView.findViewById(R.id.tvAssignmentName);
            nofquestion = (TextView) itemView.findViewById(R.id.tvnofquestions);
            update = (TextView) itemView.findViewById(R.id.tvupdate);
            submit = (TextView) itemView.findViewById(R.id.tvsubmit);
            pdf = (TextView) itemView.findViewById(R.id.tvpdf);

        }


    }

    public AssingmentAdapter(Context mContext, List<Assignment_Model> assignment_modelList){
        this.mContext = mContext;
        this.assignment_modelList = assignment_modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card, parent, false);

        return new MyViewHolder(iView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Assignment_Model assignment_model = assignment_modelList.get(position);
        holder.titleofassingment.setText(assignment_model.getAssignmentName());
        holder.nofquestion.setText(assignment_model.getQuestions() + " Questions");
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(mContext, QuestionActivity.class);
                    mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return assignment_modelList.size() ;
    }
}
