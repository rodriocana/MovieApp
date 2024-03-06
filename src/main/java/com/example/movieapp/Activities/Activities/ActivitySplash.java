package com.example.movieapp.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.movieapp.R;

public class ActivitySplash extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i); // Utiliza el contexto para iniciar la actividad
                finish(); // Finaliza Activity_inicio
            }
        }, 6000); // Tiempo de espera en milisegundos (6 segundos)
    }
}
