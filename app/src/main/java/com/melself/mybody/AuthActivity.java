package com.melself.mybody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends AppCompatActivity {

    TextView signInBtn, signUpBtn, hello_text, newMan_text, info_text;
    EditText emailReg, passReg, confPassReg, emailLog, passLog;
    Button regBtn, logBtn;
    ImageView imageBack;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        signInBtn = findViewById(R.id.signIn);
        signUpBtn = findViewById(R.id.signUp);
        emailReg = findViewById(R.id.emailReg);
        passReg = findViewById(R.id.passReg);
        confPassReg = findViewById(R.id.confPassReg);
        regBtn = findViewById(R.id.regBtn);
        imageBack = findViewById(R.id.imageBack);
        hello_text = findViewById(R.id.hello_text);
        newMan_text = findViewById(R.id.newMen_text);
        info_text = findViewById(R.id.info_text);
        emailLog = findViewById(R.id.emailLog);
        passLog = findViewById(R.id.passLog);
        logBtn = findViewById(R.id.logBtn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignIn();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUp();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUser();
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUser();
            }
        });
    }

    //?????????? ?????????????????? ?????? ??????????
    public void openSignIn(){
        //???????????? ???????????????? ??????????????????????
        emailReg.setVisibility(View.GONE);
        passReg.setVisibility(View.GONE);
        confPassReg.setVisibility(View.GONE);
        regBtn.setVisibility(View.GONE);
        //???????????? ????????????????
        imageBack.setImageResource(R.drawable.logback);

        hello_text.setText("?? ????????????????????????!");
        newMan_text.setText("");
        info_text.setText("?????????????? ?? ???????? ?????????????? ?????????? \n???????????????????? ????????????????????");

        emailLog.setVisibility(View.VISIBLE);
        passLog.setVisibility(View.VISIBLE);
        logBtn.setVisibility(View.VISIBLE);
    }

    //?????????? ?????????????????? ?????? ??????????????????????
    public void openSignUp(){
        //???????????? ???????????????? ??????????
        emailLog.setVisibility(View.GONE);
        passLog.setVisibility(View.GONE);
        logBtn.setVisibility(View.GONE);

        hello_text.setText("????????????");
        newMan_text.setText("??????????????,");
        info_text.setText("?????????????? ???????? ???????????? ???????? ?????? ?????????????? ?? ?????????????? ?? ???????????? ?????????????? ??????????????");

        //???????????? ???????????????? ??????????????????????
        emailReg.setVisibility(View.VISIBLE);
        passReg.setVisibility(View.VISIBLE);
        confPassReg.setVisibility(View.VISIBLE);
        regBtn.setVisibility(View.VISIBLE);
        imageBack.setImageResource(R.drawable.regback);
    }

    public void regUser(){
        if(!TextUtils.isEmpty(emailReg.getText().toString()) && !TextUtils.isEmpty(passReg.getText().toString()) && !TextUtils.isEmpty(confPassReg.getText().toString())){
            mAuth.createUserWithEmailAndPassword(emailReg.getText().toString(), passReg.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User();
                        user.setEmail(emailReg.getText().toString());
                        user.setPassword(passReg.getText().toString());
                        user.setName("???? ????????????????");
                        user.setSex("???? ????????????????");
                        user.setWeight(0);
                        user.setGrowth(0);
                        user.setImage_id("");

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                        Toast.makeText(AuthActivity.this, "?????????????????????? ????????????????", Toast.LENGTH_SHORT).show();
                        Intent openTake = new Intent(AuthActivity.this, TakeInfoActivity.class);
                        startActivity(openTake);
                    }
                    else {
                        Toast.makeText(AuthActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(AuthActivity.this, "?????????????????? ?????? ????????", Toast.LENGTH_SHORT).show();
        }
    }

    public void logUser(){
        if (!TextUtils.isEmpty(emailLog.getText().toString()) && !TextUtils.isEmpty(passLog.getText().toString())) {
            mAuth.signInWithEmailAndPassword(emailLog.getText().toString(), passLog.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intentLog = new Intent(AuthActivity.this, MainActivity.class);
                        startActivity(intentLog);
                    } else {
                        Toast.makeText(AuthActivity.this, "?????????? ?????? ???????????? ?????????????? ??????????????", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(AuthActivity.this, "?????????????????? ?????? ????????", Toast.LENGTH_SHORT).show();
        }
    }
}