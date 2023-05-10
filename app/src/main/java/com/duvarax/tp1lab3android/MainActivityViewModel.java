package com.duvarax.tp1lab3android;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.tp1lab3android.model.Usuario;
import com.duvarax.tp1lab3android.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutable(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }


    public void logear(String correo, String contraseña){
        Usuario usuario = ApiClient.logearUsuario(context, correo, contraseña);
        if(usuario != null){
            usuarioMutable.setValue(usuario);
        }else{
            Toast.makeText(context, "Usuario inexistente", Toast.LENGTH_SHORT).show();
        }
    }

}
