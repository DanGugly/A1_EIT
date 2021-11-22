package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    private EditText email, cpassword, rpassword;
    private Button next;
    private String valid_email;
    private String password;
    private ArrayList<String> elist;
    private ImageButton back_butn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.emailAddress);
        cpassword = findViewById(R.id.createdPassword);
        rpassword = findViewById(R.id.repeatedPassword);

        next = findViewById(R.id.nextButton);

        back_butn = findViewById(R.id.back_butn);

        elist = new ArrayList<>();
        elist.add("test@123.com");
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

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else if (!isEmailValid(edt.getText().toString())) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else if (oldEmail(edt.getText().toString())) {
                    edt.setError("Email Address already exists");
                    valid_email = null;
                }
                else {
                    valid_email = edt.getText().toString();
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
                    pw.setError("Password must contain at least 8 characters, 1 upper & lowercase");
                }else {
                    password = pw.getText().toString();
                }
            }
            public boolean validpass(final String pw){
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

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(rpassword.getText().toString()==null||!(cpassword.getText().toString().equals(rpassword.getText().toString()))){
                    rpassword.setError("Passwords don't match!");
                }else {

                }
            }
        });

        if(validated()){
            //next.setHighlighted // Highlight button after validations passed
            next.setOnClickListener(v -> {
                //Save email and password locally

            });
        }

        back_butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private boolean validated(){
        return true;
    }
}