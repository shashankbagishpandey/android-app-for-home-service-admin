package com.example.handyman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    TextInputLayout username_var,password_var,cpassword_var,mobile_var;
    FirebaseDatabase firebaseDatabase;  //instance like table name
    DatabaseReference reference;         //refrence (data in table )

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        username_var=findViewById(R.id.username_field);
        password_var=findViewById(R.id.password_input_field);
        cpassword_var=findViewById(R.id.cpassword_input_field);
        mobile_var=findViewById(R.id.mobile_field);

    }

    public void loginbutton(View view) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void registerbutton(View view) {

        String username_ =username_var.getEditText().getText().toString();
        String mobile_ =mobile_var.getEditText().getText().toString();
        String password_ =password_var.getEditText().getText().toString();
        String cpassword_ =cpassword_var.getEditText().getText().toString();


        if(!username_.isEmpty()){

            username_var.setError(null);
            username_var.setErrorEnabled(false);

            if (!mobile_.isEmpty()){

                mobile_var.setError(null);
                mobile_var.setErrorEnabled(false);

                if (!password_.isEmpty()){

                    password_var.setError(null);
                    password_var.setErrorEnabled(false);

                    if (!cpassword_.isEmpty()){

                        cpassword_var.setError(null);
                        cpassword_var.setErrorEnabled(false);

                        firebaseDatabase=FirebaseDatabase.getInstance();
                        reference=firebaseDatabase.getReference("Techusers");

                        String username_s =username_var.getEditText().getText().toString();
                        String mobile_s =mobile_var.getEditText().getText().toString();
                        String password_s =password_var.getEditText().getText().toString();
                        String cpassword_s =cpassword_var.getEditText().getText().toString();

                        storingdata sd=new storingdata(username_s,mobile_s,password_s,cpassword_s);

                        //to store in firebase
                        reference.child(username_s).setValue(sd);

                        Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        finish();

                    }else{
                        cpassword_var.setError("please confirm your password");
                    }


                }else{
                    password_var.setError("please enter your password");
                }


            }else{
                mobile_var.setError("please enter your mobile number");
            }


        }else{
            username_var.setError("please enter the username");
        }

    }
}