package com.duvarax.tp1lab3android.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.duvarax.tp1lab3android.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static SharedPreferences sp;

    public static void registrarUsuario(Context context,Usuario usuario){
        File userDat = new File(context.getFilesDir(), "usuario.dat");
        try {
            FileOutputStream fo = new FileOutputStream(userDat);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream data = new ObjectOutputStream(bo);
            data.writeObject(usuario);
            bo.flush();
            data.close();

        } catch (FileNotFoundException e) {
            Log.d("salida", e.toString());
        } catch (IOException e) {
            Log.d("salida", e.toString());
        }

    }

    public static Usuario leerUsuario(Context context){
        File userDat = new File(context.getFilesDir(), "usuario.dat");
        try {
            FileInputStream fo = new FileInputStream(userDat);
            BufferedInputStream bo = new BufferedInputStream(fo);
            ObjectInputStream data = new ObjectInputStream(bo);
            Usuario user = (Usuario)data.readObject();
            return user;

        } catch (FileNotFoundException e) {
            Log.d("salida", e.toString());
        } catch (IOException e) {
            Log.d("salida", e.toString());
        } catch (ClassNotFoundException e) {
            Log.d("salida", e.toString());
        }
        return null;
    }

    public static Usuario logearUsuario(Context context, String email, String pass){
        Usuario user = leerUsuario(context);
        if(user == null){
            return null;
        }
        if(user.getCorreo().equals(email) && user.getContraseña().equals(pass)){
            return user;
        }else{
            user = null;
            return user;
        }
    }


}
