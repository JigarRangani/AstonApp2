package com.example.dell.astonapp.Model_classis;

/**
 * Created by DELL on 07-02-2017.
 */

public class Subject_Model {
    private int courcesid;
    private int id2;
    private String title;
    private int sem;
    private int subject_code;
    private int dep_id;
    private int credit;
    private int enrollid;



    public Subject_Model(){

   }

   public Subject_Model(int id2,String title, int sem, int subject_code, int dep_id,int credit){
       //this.courcesid = courcesid;
       this.id2 = id2;
     this.title = title;
     this.sem = sem;
       this.subject_code = subject_code;
       this.dep_id = dep_id;
       this.credit = credit;
    }

    public Subject_Model(String title, int sem, int subject_code, int credit){
        //this.courcesid = courcesid;
       // this.id2 = id2;
        this.title = title;
        this.sem = sem;
        this.subject_code = subject_code;
      //  this.dep_id = dep_id;
        this.credit = credit;
    }
    public int getCourcesid() {
        return courcesid;
    }

    public void setCourcesid(int courcesid) {
        this.courcesid = courcesid;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(int subject_code) {
        this.subject_code = subject_code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getEnrollid() {
        return enrollid;
    }

    public void setEnrollid(int enrollid) {
        this.enrollid = enrollid;
    }
}
