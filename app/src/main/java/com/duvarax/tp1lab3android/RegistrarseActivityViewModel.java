package com.duvarax.tp1lab3android;

import android.app.Application;
import android.content.Context;

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
    public RegistrarseActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
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
        Usuario user = ApiClient.leer(context);
        if(user.getDni() == -1){
            usuarioNuevo.setValue(true);
        }else{
            usuarioMutable.setValue(user);
        }
    }

}
