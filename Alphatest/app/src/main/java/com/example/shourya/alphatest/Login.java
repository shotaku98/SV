package com.example.shourya.alphatest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    SharedPreferences sp;
    EditText mobile,Name,otp;
    SharedPreferences.Editor editor;
    ActionBar actionBar;
    Button B,r;
    String verfication_code;
    LinearLayout Sp,Op;
    FirebaseAuth auth;
    ScrollView scroll;
    boolean status;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        sp=getSharedPreferences("Login", Context.MODE_PRIVATE);
        editor=sp.edit();
        actionBar=getSupportActionBar();
        actionBar.hide();

        B=findViewById(R.id.Sendo);
        mobile=findViewById(R.id.MI);
        otp=findViewById(R.id.OTPTEXT);
        r=findViewById(R.id.OTP);
        scroll
                =findViewById(R.id.Send);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=otp.getText().toString();
                Verify(pass);
                            }
        });
        Name=findViewById(R.id.Name);
        Sp=findViewById(R.id.Sp);
        Op=findViewById(R.id.ottp);
        auth = FirebaseAuth.getInstance();
        mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verfication_code=s;
                Toast.makeText(getApplicationContext(),"code sent to the number",Toast.LENGTH_LONG).show();
            }
        };

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = mobile.getText().toString();
                String Namee=Name.getText().toString();
                editor.putString("UserName",Namee);
                editor.commit();
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+phone_number,60,TimeUnit.SECONDS,Login.this,mCallback);
                Sp.setVisibility(View.INVISIBLE);
                Op.setVisibility(View.VISIBLE);
                scroll.setVisibility(View.VISIBLE);
            }
        });


            }
    public void signin(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Signed In",Toast.LENGTH_SHORT).show();
                    status= true;
                    editor.putBoolean("LS",true);
                    editor.commit();


                    Intent intent= new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    status = false;
                    Toast.makeText(getApplicationContext(),"OTP incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public  void Verify(String Input_OTP){

        if (verfication_code!=null){
            verifyphone(verfication_code,Input_OTP);
        }
    }
    private void verifyphone(String verifyCode,String inputcode)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifyCode,inputcode);
        signin(credential);
    }
    }

