package com.example.dell.astonapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.astonapp.Model_classis.Subject_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by DELL on 14-02-2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    //All static variables
    //Database Version
    private static final int DATAABASE_VERSION = 1;

    private static final String DATABASE_NAME = "android_api";

    private static final String TABLE_USER = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";

    private static final String TABLE_COURSES = "courses";

    private static final String KEY_COURSES_ID = "courseid";
    private static final String KEY_ID2 = "id2";
    private static final String KEY_COURSES_TITLE = "courses_title";
    private static final String KEY_COURSES_CODE = "courses_code";
    private static final String KEY_COURSES_SEM = "courses_sem";
    private static final String KEY_COURSES_DEP_ID = "courses_dep_id";
    private static final String KEY_COURSES_CREDIT = "courses_credit";
    private static final String KEY_ENROLLED_ID = "enroll_id";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATAABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table for storin token
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UID + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        //table for enrolled courses
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + " ("
                + KEY_COURSES_ID + " INTEGER PRIMARY KEY, " + KEY_ID2 + " TEXT,"
                + KEY_COURSES_TITLE + " TEXT," + KEY_COURSES_CODE + " TEXT,"
                + KEY_COURSES_SEM + " TEXT," + KEY_COURSES_DEP_ID + " TEXT,"
                + KEY_COURSES_CREDIT + " TEXT," + KEY_ENROLLED_ID + " Text" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
        Log.d(TAG, "Database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop table  if already existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_COURSES);

        //Create table again
        onCreate(db);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addUer(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid);

        //Insertind Row
        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("uid", cursor.getString(1));

        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching user from Sqlite; " + user.toString());

        return user;
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CRUD operation for cources
    public void addCourses(Subject_Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values2 = new ContentValues();
        // values2.put(KEY_COURSES_ID, model.getCourcesid());
        values2.put(KEY_ID2, model.getId2());
        values2.put(KEY_COURSES_TITLE, model.getTitle());
        values2.put(KEY_COURSES_SEM, model.getSem());
        values2.put(KEY_COURSES_CODE, model.getSubject_code());
        values2.put(KEY_COURSES_DEP_ID, model.getDep_id());
        values2.put(KEY_COURSES_CREDIT, model.getCredit());
        values2.put(KEY_ENROLLED_ID, model.getEnrollid());

        //Insertind Row

        db.insert(TABLE_COURSES, null, values2);
        db.close();

        Log.d(TAG, "New user inserted into sqlite: " + KEY_COURSES_TITLE);
    }

    public List<Subject_Model> getAllCourses() {
        List<Subject_Model> courseslist = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_COURSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int index = cursor.getColumnIndex(SQLiteHandler.KEY_COURSES_TITLE);
                int index2 = cursor.getColumnIndex(SQLiteHandler.KEY_COURSES_SEM);
                int index3 = cursor.getColumnIndex(SQLiteHandler.KEY_COURSES_CODE);
                int index4 = cursor.getColumnIndex(SQLiteHandler.KEY_COURSES_CREDIT);
                Subject_Model model = new Subject_Model();
                model.setTitle(cursor.getString(index));
                model.setSem(cursor.getInt(index2));
                model.setSubject_code(cursor.getInt(index3));
                model.setCredit(cursor.getInt(index4));
                courseslist.add(model);
            } while (cursor.moveToNext());

        }

        return courseslist;

    }

    public void deleteCources() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getCourseid(String title) {
        // long corid = 0;
        String reid = "Error";
        // String query = "SELECT  * FROM courses WHERE courses_title=" + title;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSES, new String[]{KEY_COURSES_ID,
                        KEY_ID2, KEY_COURSES_TITLE, KEY_COURSES_CODE,
                        KEY_COURSES_SEM, KEY_COURSES_DEP_ID,
                        KEY_COURSES_CREDIT}, KEY_COURSES_TITLE + "=?",
                new String[]{String.valueOf(title)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            reid = cursor.getString(cursor.getColumnIndex(SQLiteHandler.KEY_ID2));
        }
        //  if (cursor != null)
        //  cursor.moveToFirst();

        Log.d(TAG, "getting the id value");
        return reid;
    }
}
