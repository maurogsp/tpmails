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


    public ServicioUsuarios() {

    }

    public UsuarioSesion login(String nombreUsuario, String password) {
        UsuarioSesion u = null;
        try {
            u = usuarioDao.get(nombreUsuario, password);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return u;
    }

    public List listar_usuarios() {
        List lista = null;
        try {
            lista = usuarioDao.listar_usuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List listar_usuarios_x_nombre(String nombre) {
        List lista = null;
        try {
            lista = usuarioDao.listar_usuarios_x_nombre(nombre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void crear_Usuario(String nombre_usuario, String contrasenia, String nombre, String apellido, String direccion, String telefono, String ciudad, String provincia, String pais, String mail, boolean borrado, boolean admin) {
        try {
            usuarioDao.crearUsuario(nombre_usuario, contrasenia, nombre, apellido, direccion, telefono, ciudad, provincia, pais, mail, borrado, admin);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminar_Usuario(int id) {
        try {
            usuarioDao.eliminar_usuario(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
