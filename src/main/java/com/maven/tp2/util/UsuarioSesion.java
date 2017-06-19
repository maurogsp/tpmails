package com.maven.tp2.util;

/**
 * Created by mauro on 16/06/17.
 */
public class UsuarioSesion {

    private int id;
    private String nombreUsuario;
    private String password;
    private boolean admin;


    public UsuarioSesion(int id, String nombreUsuario, String password, boolean admin) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
