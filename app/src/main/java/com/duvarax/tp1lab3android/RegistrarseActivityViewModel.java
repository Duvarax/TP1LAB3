package com.duvarax.tp1lab3android;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.tp1lab3android.model.Usuario;
import com.duvarax.tp1lab3android.request.ApiClient;

public class RegistrarseActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<Boolean> usuarioNuevo;

    private MutableLiveData<Bitmap> imagen;
    public RegistrarseActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Bitmap> getImagen(){
        if(imagen == null){
            imagen = new MutableLiveData<>();
        }
        return imagen;
    }
    public LiveData<Usuario> getUsuarioMutable(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public LiveData<Boolean> getUsuarioNuevo(){
        if(usuarioNuevo == null){
            usuarioNuevo = new MutableLiveData<>();
        }
        return usuarioNuevo;
    }

    public void leer(){
        Usuario user = ApiClient.leerUsuario(context);
        if(user == null){
            usuarioNuevo.setValue(true);
        }else{
            usuarioMutable.setValue(user);
        }
    }


    public void setImagen(){

    }

}
