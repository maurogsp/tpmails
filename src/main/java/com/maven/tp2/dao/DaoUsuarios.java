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
            String cmd = "select id, nombre_usuario, pass from usuarios where nombre_usuario = '"+nombreUsuario+"' and pass ="+password;
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                UsuarioSesion user = new UsuarioSesion(rs.getInt("id"), rs.getString("nombre_usuario"), rs.getString("pass"));
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


}
