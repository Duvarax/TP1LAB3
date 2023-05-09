package com.duvarax.tp1lab3android.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.duvarax.tp1lab3android.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("datos.xml", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("correo", usuario.getCorreo());
        editor.putString("contraseña", usuario.getContraseña());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String nombre = sp.getString("nombre", "nn");
        String apellido = sp.getString("apellido", "nn");
        String correo = sp.getString("correo", "nn");
        String contraseña = sp.getString("contraseña", "nn");

        Usuario usuario = new Usuario(nombre,dni, apellido, correo, contraseña);
        return usuario;
    }
    public static Usuario login(Context context, String email, String pass){
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String nombre = sp.getString("nombre", "nn");
        String apellido = sp.getString("apellido", "nn");
        String correo = sp.getString("correo", "nn");
        String contraseña = sp.getString("contraseña", "nn");

        if(correo.equals(email) && contraseña.equals(pass)){
            usuario = new Usuario(nombre,dni, apellido, correo, contraseña);
        }
        return usuario;
    }


}
