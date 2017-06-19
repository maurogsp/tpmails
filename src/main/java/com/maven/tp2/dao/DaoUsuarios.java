package com.maven.tp2.dao;

import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauro on 13/06/17.
 */
@Repository
public class DaoUsuarios {
    @Autowired
    private Connection conex;

    public UsuarioSesion get(String nombreUsuario, String password) {
        List<UsuarioSesion> lista = new ArrayList<UsuarioSesion>();
        try {
            String cmd = "select id, nombre_usuario, pass, admin from usuarios where nombre_usuario = '"+nombreUsuario+"' and pass ="+password;
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                UsuarioSesion user = new UsuarioSesion(rs.getInt("id"), rs.getString("nombre_usuario"), rs.getString("pass"), rs.getBoolean("admin"));
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lista.size() == 1) {
            return lista.get(0);
        } else {
            return null;
        }

    }

    public List listar_usuarios ()
    {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String cmd = "select * from usuarios";
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nombre_usuario"), rs.getString("pass"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("provincia"), rs.getString("pais"), rs.getString("mail"), rs.getBoolean("borrado"), rs.getBoolean("admin"));
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return lista;
    }


}
