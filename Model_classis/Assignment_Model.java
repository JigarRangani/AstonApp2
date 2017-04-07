package com.example.dell.astonapp.Model_classis;

/**
 * Created by DELL on 08-02-2017.
 */

public class Assignment_Model {
    private String assignmentName;
    private int questions;
    public Assignment_Model(){

    }
    public Assignment_Model(String assignmentName, int questions ){
        this.assignmentName = assignmentName;
        this.questions = questions;
    }

    public String getAssignmentName() {

        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }



}
