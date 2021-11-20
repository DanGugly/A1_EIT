package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    private EditText email, cpassword, rpassword;
    private Button next;
    private String valid_email;

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
                } else if (isEmailValid(edt.getText().toString()) == false) {
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

        if(validated()){
            //next.setHighlighted // Highlight button after validations passed
            next.setOnClickListener(v -> {

            });
        }
    }

    private boolean validated(){
        return true;
    }
}