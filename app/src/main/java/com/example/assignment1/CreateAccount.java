package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    private EditText email, cpassword, rpassword;
    private Button next;
    private String valid_email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.emailAddress);
        cpassword = findViewById(R.id.createdPassword);
        rpassword = findViewById(R.id.repeatedPassword);

        next = findViewById(R.id.nextButton);
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
                } else {
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
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
                Pattern pattern;
                Matcher matcher;
                final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(pw);

                return matcher.matches();
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
    }

    private boolean validated(){
        return true;
    }
}