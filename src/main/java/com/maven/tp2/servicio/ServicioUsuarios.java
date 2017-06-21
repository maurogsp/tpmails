package com.maven.tp2.servicio;

import com.maven.tp2.dao.DaoUsuarios;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mauro on 16/06/17.
 */
@Service
public class ServicioUsuarios {


    @Autowired
    DaoUsuarios usuarioDao;


    public void setUsuarioDao(DaoUsuarios usuarioDao) {
        this.usuarioDao = usuarioDao;
    }


    public ServicioUsuarios() {

    }

    public UsuarioSesion login(String nombreUsuario, String password) throws Exception{
        UsuarioSesion u = null;
        try {
            u = usuarioDao.get(nombreUsuario, password);
        } catch (Exception e) {
            throw e;

        }
        return u;
    }

    public List listar_usuarios() throws Exception {
        List lista = null;
        try {
            lista = usuarioDao.listar_usuarios();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List listar_usuarios_x_nombre(String nombre) throws Exception {
        List lista = null;
        try {
            lista = usuarioDao.listar_usuarios_x_nombre(nombre);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public void crear_Usuario(String nombre_usuario, String contrasenia, String nombre, String apellido, String direccion, String telefono, String ciudad, String provincia, String pais, String mail, boolean borrado, boolean admin) throws Exception{
        try {
            usuarioDao.crearUsuario(nombre_usuario, contrasenia, nombre, apellido, direccion, telefono, ciudad, provincia, pais, mail, borrado, admin);
        } catch (Exception e) {
            throw e;
        }

    }

    public void eliminar_Usuario(int id) throws Exception{
        try {
            usuarioDao.eliminar_usuario(id);
        } catch (Exception e) {
            throw e;
        }

    }

}
