package com.duvarax.tp1lab3android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duvarax.tp1lab3android.databinding.ActivityMainBinding;
import com.duvarax.tp1lab3android.model.Usuario;
import com.duvarax.tp1lab3android.request.ApiClient;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mv;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = getApplicationContext();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        mv.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent intent = new Intent(context, RegistrarseActivity.class);
                startActivity(intent);
            }
        });

        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.logear(binding.etCorreo.getText().toString(), binding.etContraseA.getText().toString());
            }
        });

        binding.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiClient.guardar(context, new Usuario("", -1l, "", "", ""));
                Intent intent = new Intent(context, RegistrarseActivity.class);
                startActivity(intent);
            }
        });


    }
}