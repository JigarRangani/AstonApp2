package com.example.dell.astonapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.astonapp.helper.SQLiteHandler;
import com.example.dell.astonapp.loginPerpose.AppConfig;
import com.example.dell.astonapp.loginPerpose.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.emailet_login);
        inputPassword = (EditText) findViewById(R.id.passwordet_login);
        login_btn = (Button) findViewById(R.id.loginbt_login);

        //progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        //sqlite databse halper
        db = new SQLiteHandler(getApplicationContext());

        //session manager
        session = new SessionManager(getApplicationContext());

        //check is user is already logged in or not
        if(session.isLoggedIn()){
            //user is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        //login button click event
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                //check for empty data in the form
                if(!email.isEmpty() && !password.isEmpty()){
                    //login user
                    checkLogin(email, password);
                }else {
                    //promt user to enter credentials
                    Toast.makeText(getApplicationContext(),"please enter the credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void checkLogin(final String email, final String password) {
        //Tag used to cancel the request
        //String tag_string_req = "req_login";

        pDialog.setMessage("Logging in...............");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    // boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (jObj.names().get(0).equals("token")) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("token");



                        // Inserting row in users table
                        db.addUer(uid);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        //  String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                "Somthing went Wrong", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "it is looking strenge", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };
        //Adding the string request to t
//        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
    }

    private void showDialog() {
        if(!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
