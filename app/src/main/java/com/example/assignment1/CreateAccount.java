package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    private EditText email, cpassword, rpassword;
    private TextView error_msg, error_email;
    private Button next;
    private String valid_email;
    private String password;
    private String password2;
    private String SHARED_PREFS = "MY_SHARED_PREFS";
    private int count = 0;
    private static String counts = "COUNT";
    private static String email_id = "EMAIL_ID";
    Drawable myIcon ;
    private ArrayList<String> elist;
    private ImageButton back_butn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.emailAddress);
        cpassword = findViewById(R.id.createdPassword);
        rpassword = findViewById(R.id.repeatedPassword);

        error_msg = findViewById(R.id.error_text);
        error_email = findViewById(R.id.error_text_email);

        // when we have this kind of warnings lets try to solve it
        // it also says it is deprecated on API 22
        // AppCompatResources.getDrawable(getBaseContext(), R.mipmap.tick_foreground);

        myIcon = getResources().getDrawable(R.mipmap.tick_foreground);
        myIcon.setBounds(0, 0, 170, 170);

        next = findViewById(R.id.nextButton);

        back_butn = findViewById(R.id.back_butn);
        elist = new ArrayList<>();

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        count = sharedPreferences.getInt(counts, 0);

        if (count == 0){
            sharedPreferences.edit().putInt(counts, 1).apply();
        } else if(count>1){
            for (int x=2; x<=count; x++){
                //Log.d("Shared_Pref",sharedPreferences.getString(email_id+x,null));
                elist.add(sharedPreferences.getString(email_id+x, null));
            }
        }

        // just a observation here and there... don't commit and push commented code
        // since that can be a caused of security leak.
        // it is good for comments but not for actual logic

        //elist.add("test@123.com");
    }

    @Override
    protected void onResume() {
        super.onResume();

        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Auto-generated method stub

                // Auto-generated method stub
                Is_Valid_Email(email); // pass your EditText Obj here.
            }

            @SuppressLint("ResourceAsColor")
            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    //email.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.cross_foreground, 0, 0, 0);
                    valid_email = null;

                } else if (!isEmailValid(edt.getText().toString())) {
                    //edt.setError("Invalid Email Address");
                    error_email.setText(R.string.email_format);
                    error_email.setVisibility(View.VISIBLE);
                    //email.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.cross_foreground, 0, 0, 0);
                    //valid_email = null;
                    email.setBackgroundResource(R.drawable.fronttext);
                    validatedU();
                } else if (oldEmail(edt.getText().toString())) {
                    //edt.setError("Email Address already exists");
                    error_email.setText(R.string.email_dup);
                    error_email.setVisibility(View.VISIBLE);
                    //valid_email = null;
                    email.setBackgroundResource(R.drawable.fronttext);
                    validatedU();
                }
                else {
                    valid_email = edt.getText().toString();
                    Log.d("VALID", "Email valid");
                    cpassword.setFocusable(true);
                    validatedU();
                    error_email.setVisibility(View.GONE);
                    error_email.setText("");

                    //email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                    //email.setError(null);
                    email.setError( "",myIcon);
                    email.setBackgroundResource(R.drawable.backtext);
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)

            boolean oldEmail(final String email){
                return elist.contains(email);
            }
        });

        cpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Check valid password (8chars, 1 num, 1 upper & lowercase, can have special chars
                is_pass_valid(cpassword);
            }
            public void is_pass_valid(EditText pw){
                if(String.valueOf(pw.getText())==null){
                    pw.setError("Invalid Password");
                }else if(!validpass(pw.getText().toString())){
                    //pw.setError("Password must contain at least 8 characters, 1 upper & lowercase");
                    error_msg.setVisibility(View.VISIBLE);
                    error_msg.setText(R.string.pass_invalid);
                    cpassword.setBackgroundResource(R.drawable.fronttext);
                    validatedU();
                }else {
                    password = pw.getText().toString();
                    //cpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    cpassword.setError( " ",myIcon);
                    cpassword.setBackgroundResource(R.drawable.backtext);
                    rpassword.setFocusable(true);
                    error_msg.setVisibility(View.GONE);
                    error_msg.setText("");
                    validatedU();
                    Log.d("VALID", "Password valid");
                }
            }
            public boolean validpass(final String pw){

                // good use of patterns to check the validation

                Pattern uppercase = Pattern.compile("[A-Z]");
                Pattern lowercase = Pattern.compile("[a-z]");
                Pattern digit = Pattern.compile("[0-9]");

                if (!lowercase.matcher(pw).find()) return false;
                else if (!uppercase.matcher(pw).find()) return false;
                else if (!digit.matcher(pw).find()) return false;
                else if(pw.length()<8) return false;
                else return true;
            }
        });

        rpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //validatedU();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(rpassword.getText().toString()==null||!(cpassword.getText().toString().equals(rpassword.getText().toString()))){
                    //rpassword.setError("Passwords don't match!");
                    error_msg.setVisibility(View.VISIBLE);
                    error_msg.setText(R.string.pass_mismatch);
                    rpassword.setBackgroundResource(R.drawable.fronttext);
                    validatedU();
                }else {
                    rpassword.setError( " ",myIcon);
                    rpassword.setBackgroundResource(R.drawable.backtext);
                    //rpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    password2 = rpassword.getText().toString();
                    error_msg.setVisibility(View.GONE);
                    error_msg.setText("");
                    Log.d("VALID", "Repeated Password valid");
                    validatedU();
                }
            }
        });

            //next.setHighlighted // Highlight button after validations passed
            next.setOnClickListener(v -> {
                if(validated()){
                    //Save email and password locally
                    SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    if(count<2) count=2; else count++;
                    sharedPreferences.edit().putInt(counts, count).apply();
                    Log.d("Shared_Pref","Count: "+count+" Val = "+email.getText().toString());

                    // nice implementation for the counter

                    sharedPreferences.edit().putString(email_id+count, valid_email).apply();
                    elist.add(valid_email);

                    //Reset fields after storing
                    email.setText("");
                    email.setError(null);
                    cpassword.setText("");
                    cpassword.setError(null);
                    rpassword.setText("");
                    rpassword.setError(null);
                    error_email.setVisibility(View.GONE);
                    error_msg.setVisibility(View.GONE);
                    email.setBackgroundResource(0);
                    cpassword.setBackgroundResource(0);
                    rpassword.setBackgroundResource(0);
                    email.setBackgroundColor(getResources().getColor(R.color.white));
                    cpassword.setBackgroundColor(getResources().getColor(R.color.white));
                    rpassword.setBackgroundColor(getResources().getColor(R.color.white));
                }
            });


        back_butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);

                // This way to start the activity is used when you need to perform any kind of question
                // lets say you want to execute certain logic when this request code is received by the 'MainActivity'
                // a normal call should be 'startActivity(myIntent)'

                startActivityForResult(myIntent, 0);
            }
        });
    }

    private boolean validated(){
        // all commented code should be removed

        //try {
            if(!valid_email.equals("") && email.getText().toString().equals(valid_email)
                    && !password.equals("") && cpassword.getText().toString().equals(password)
                    && !password2.equals("") && rpassword.getText().toString().equals(password2)){
                //next.setAlpha(1);
                //next.setEnabled(true);
                return true;
            }else {
                //next.setAlpha(0.5F);
                //next.setEnabled(false);
                return false;
            }/*
        }catch (Exception e){
            Log.d("Exception",e.getStackTrace().toString());
        }*/
    }
    private void validatedU(){
        try {
            if(!valid_email.equals("") && email.getText().toString().equals(valid_email)
                    && !password.equals("") && cpassword.getText().toString().equals(password)
                    && !password2.equals("") && rpassword.getText().toString().equals(password2))
            {
                next.setAlpha(1);
            }else {
                next.setAlpha(0.5F);
            }
        }catch (Exception e){

        }
    }
}