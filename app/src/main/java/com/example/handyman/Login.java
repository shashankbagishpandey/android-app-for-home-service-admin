package com.example.handyman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {


    TextView signup;
    TextView login;
    TextInputLayout password_var,username_var;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        signup =findViewById(R.id.signup);
        login=findViewById(R.id.login);
        username_var=findViewById(R.id.username_field);
        password_var=findViewById(R.id.password_input_field);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String username_=username_var
                String username_ =username_var.getEditText().getText().toString();
                String password_ =password_var.getEditText().getText().toString();

                if (!username_.isEmpty()){

                    username_var.setError(null);
                    username_var.setErrorEnabled(false);

                    if (!password_.isEmpty()){
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final String username_data=username_var.getEditText().getText().toString();
                        final  String password_data=password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance("https://test-495ce-default-rtdb.asia-southeast1.firebasedatabase.app/");
                        DatabaseReference databaseReference=firebaseDatabase.getReference("Techusers");

                        Query check_username=databaseReference.orderByChild("username").equalTo(username_data);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()){

                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);

                                    String passwordcheck=snapshot.child(username_data).child("password").getValue(String.class);
                                    if (passwordcheck.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);

                                        Toast.makeText(getApplicationContext(),"login sucessfuly",Toast.LENGTH_SHORT).show();

                                        Intent i=new Intent(getApplicationContext(),userlist.class);
                                        startActivity(i);
                                        finish();

                                    }else{

                                        password_var.setError("Wrong password");

                                    }

                                }else{
                                    username_var.setError("user does not exist");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }else {
                        password_var.setError("please enter the password");
                    }

                }else {
                    username_var.setError("please enter the username");
                }

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
                finish();//once intent get to the signup   then user dont able to move back in login
            }
        });


    }
}