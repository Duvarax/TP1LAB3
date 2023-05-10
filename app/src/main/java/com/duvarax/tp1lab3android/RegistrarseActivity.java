package com.duvarax.tp1lab3android;

import static android.Manifest.permission_group.CAMERA;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.duvarax.tp1lab3android.databinding.ActivityMainBinding;
import com.duvarax.tp1lab3android.databinding.ActivityRegistrarseBinding;
import com.duvarax.tp1lab3android.model.Usuario;
import com.duvarax.tp1lab3android.request.ApiClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistrarseActivity extends AppCompatActivity {

    private ActivityRegistrarseBinding binding;
    private RegistrarseActivityViewModel mv;
    private Context context;
    private String ruta;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri uri;

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
                ApiClient.registrarUsuario(context,usuario);
                Toast.makeText(context, "Usuario Creado", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermisos();
                Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (tomarFoto.resolveActivity(getPackageManager()) != null) {
                    File fotoArchivo = null;
                    try {
                        fotoArchivo = createImageFile();
                    }catch (IOException e){
                        Log.d("salida", e.toString());
                    }
                    if(fotoArchivo != null){
                        uri = FileProvider.getUriForFile(context, getPackageName() + ".fileprovider", fotoArchivo);
                        tomarFoto.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(tomarFoto, 1);
                    }

                }
            }
        });
        mv.leer();
        setContentView(binding.getRoot());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // La foto ha sido capturada exitosamente, muestra la imagen en el ImageView
            binding.ivFoto.setImageURI(uri);
        }
    }

    private boolean solicitarPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA))){
            Toast.makeText(context, "Debe aceptar los permisos", Toast.LENGTH_SHORT).show();
        }else{
            requestPermissions(new String[]{CAMERA},100);
        }

        return false;
    }
    private File createImageFile() throws IOException {
        // Crea un nombre único para el archivo de imagen basado en la fecha y hora actual
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        File imageFile = new File(storageDir, "my_images/" + fileName);
        return imageFile;
    }
}