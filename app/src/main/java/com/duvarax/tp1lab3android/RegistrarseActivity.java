package com.duvarax.tp1lab3android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.duvarax.tp1lab3android.databinding.ActivityMainBinding;
import com.duvarax.tp1lab3android.databinding.ActivityRegistrarseBinding;
import com.duvarax.tp1lab3android.model.Usuario;
import com.duvarax.tp1lab3android.request.ApiClient;

public class RegistrarseActivity extends AppCompatActivity {

    private ActivityRegistrarseBinding binding;
    private RegistrarseActivityViewModel mv;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarseBinding.inflate(getLayoutInflater());
        context = getApplicationContext();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistrarseActivityViewModel.class);

        mv.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni()+"");
                binding.etNombre.setText(usuario.getNombre()+"");
                binding.etApellido.setText(usuario.getApellido()+"");
                binding.etEmail.setText(usuario.getCorreo()+"");
                binding.etPass.setText(usuario.getContraseña()+"");
            }
        });
        mv.getUsuarioNuevo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDni.setText("");
                binding.etNombre.setText("");
                binding.etApellido.setText("");
                binding.etEmail.setText("");
                binding.etPass.setText("");
            }
        });

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long dni = Long.parseLong(binding.etDni.getText().toString());
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String correo = binding.etEmail.getText().toString();
                String contraseña = binding.etPass.getText().toString();

                Usuario usuario = new Usuario(nombre,dni, apellido, correo, contraseña);
                ApiClient.guardar(context, usuario);
            }
        });
        mv.leer();
        setContentView(binding.getRoot());
    }
}