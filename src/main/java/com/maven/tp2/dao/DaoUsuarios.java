package com.maven.tp2.dao;

import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    public List listar_usuarios_x_nombre (String nombre)
    {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String cmd = "select * from usuarios where nombre like '%"+nombre+"%'";
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

    public void crearUsuario (String nombre_usuario, String contrasenia, String nombre, String apellido, String direccion, String telefono, String ciudad, String provincia, String pais, String mail, boolean borrado, boolean admin) {
        try {
            conex.setAutoCommit(false);
            PreparedStatement ps = conex.prepareStatement("INSERT INTO usuarios (nombre_usuario, pass, nombre, apellido, direccion, telefono, ciudad, provincia, pais, mail, borrado, admin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, nombre_usuario);
            ps.setString(2, contrasenia);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, ciudad);
            ps.setString(8, provincia);
            ps.setString(9, pais);
            ps.setString(10, mail);
            ps.setBoolean(11, borrado);
            ps.setBoolean(12, admin);
            ps.execute();
            conex.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminar_usuario (int idu) {
        try {
            conex.setAutoCommit(false);
            PreparedStatement ps = conex.prepareStatement("delete from usuarios where id = ?");
            ps.setInt(1, idu);
            ps.execute();
            conex.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
