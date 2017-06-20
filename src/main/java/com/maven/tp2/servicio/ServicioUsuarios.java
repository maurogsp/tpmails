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

    DaoUsuarios usuarioDao;

    @Autowired
    public ServicioUsuarios(DaoUsuarios dao) {
        this.usuarioDao = dao;
    }

    public UsuarioSesion login(String nombreUsuario, String password) {
        return usuarioDao.get(nombreUsuario,password);
    }

    public List listar_usuarios()
    {
        return usuarioDao.listar_usuarios();
    }

    public List listar_usuarios_x_nombre(String nombre)
    {
        return usuarioDao.listar_usuarios_x_nombre(nombre);
    }

    public void crear_Usuario(String nombre_usuario, String contrasenia, String nombre, String apellido, String direccion, String telefono, String ciudad, String provincia, String pais, String mail, boolean borrado, boolean admin)
    {
        usuarioDao.crearUsuario(nombre_usuario, contrasenia,nombre,apellido,direccion,telefono,ciudad,provincia,pais,mail,borrado,admin);
    }

    public void eliminar_Usuario (int id)
    {
        usuarioDao.eliminar_usuario(id);
    }

}
