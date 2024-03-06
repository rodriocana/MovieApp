package com.example.movieapp.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userEdt, passEdt;

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();




    }

    private void initView() {

        userEdt=findViewById(R.id.editTextTextuser);
        passEdt = findViewById(R.id.editTextTextpassword);
        loginBtn = findViewById(R.id.buttonLogin);

        vaciarHiint();


    }

    public void vaciarHiint(){


        userEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Cuando el EditText obtiene el foco, elimina el hint
                    userEdt.setHint(null);
                } else {
                    // Cuando el EditText pierde el foco, muestra el hint si no hay texto ingresado
                    if (userEdt.getText().toString().isEmpty()) {
                        userEdt.setHint("Usuario");
                    }
                }
            }
        });

        passEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Cuando el EditText obtiene el foco, elimina el hint
                    passEdt.setHint(null);
                } else {
                    // Cuando el EditText pierde el foco, muestra el hint si no hay texto ingresado
                    if (passEdt.getText().toString().isEmpty()) {
                        passEdt.setHint("Usuario");
                    }
                }
            }
        });

        loginBtn.setOnClickListener(v -> {
            if(userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Por favor, introduzca un usuario y contraseña",Toast.LENGTH_SHORT).show();
            }else if(userEdt.getText().toString().equals("test") && passEdt.getText().toString().equals("admin")){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this,"Tu usuario y contraseña no es correcta",Toast.LENGTH_SHORT).show();
            }
        });


    }
}