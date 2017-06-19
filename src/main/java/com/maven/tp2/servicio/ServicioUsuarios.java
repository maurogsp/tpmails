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

}
