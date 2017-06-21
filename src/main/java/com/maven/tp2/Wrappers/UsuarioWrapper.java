package com.maven.tp2.Wrappers;

import com.maven.tp2.modelo.Usuario;

/**
 * Created by mauro on 18/06/17.
 */
public class UsuarioWrapper {

    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String provincia;
    private String pais;
    private String mail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UsuarioWrapper(Usuario user) {
        this.setId(user.getId());
        this.setNombre(user.getNombre());
        this.setApellido(user.getApellido());
        this.setDireccion(user.getDireccion());
        this.setTelefono(user.getTelefono());
        this.setCiudad(user.getCiudad());
        this.setProvincia(user.getProvincia());
        this.setPais(user.getPais());
        this.setMail(user.getMail());
    }
}
