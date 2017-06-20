package com.maven.tp2.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mauro on 20/06/17.
 */
public class UsuarioRequest {


    @JsonProperty("id")
    int id;
    @JsonProperty("nombre_u")
    String nombre_usuario;
    @JsonProperty("password")
    String password;
    @JsonProperty("nombre")
    String nombre;
    @JsonProperty("apellido")
    String apellido;
    @JsonProperty("direccion")
    String direccion;
    @JsonProperty("telefono")
    String telefono;
    @JsonProperty("ciudad")
    String ciudad;
    @JsonProperty("provincia")
    String provincia;
    @JsonProperty("pais")
    String pais;
    @JsonProperty("mail")
    String mail;
    @JsonProperty("borrado")
    boolean borrado;
    @JsonProperty("admin")
    boolean admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
