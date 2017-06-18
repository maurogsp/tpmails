package com.maven.tp2.dao;

import com.maven.tp2.modelo.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by mauro on 11/06/17.
 */
@Repository
public class DaoMensajes {
    @Autowired
    private Connection conex;


    public List todos_los_mensajes() {
        List<Mensaje> lista = new ArrayList<Mensaje>();

        try {
            String cmd = "select * from mensajes";
            //Statement st = DaoMensajes.getinstance().conex.createStatement();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Mensaje local = new Mensaje(rs.getInt("id"), rs.getInt("user_id_from"), rs.getInt("user_id_to"), rs.getString("remitente"), rs.getString("recipiente"), rs.getDate("fecha"), rs.getString("asunto"), rs.getString("cuerpo"), rs.getBoolean("trash_recibido"), rs.getBoolean("trash_enviado"));
                lista.add(local);
            }


        } catch (Exception e) {

        }
        return lista;
    }

    public List mensajes_recibidos_x_usuario(int id) {
        List<Mensaje> lista = new ArrayList<Mensaje>();

        try {
            String cmd = "select * from mensajes where user_id_to ="+id;
            //Statement st = DaoMensajes.getinstance().conex.createStatement();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Mensaje local = new Mensaje(rs.getInt("id"), rs.getInt("user_id_from"), rs.getInt("user_id_to"), rs.getString("remitente"), rs.getString("recipiente"), rs.getDate("fecha"), rs.getString("asunto"), rs.getString("cuerpo"), rs.getBoolean("trash_recibido"), rs.getBoolean("trash_enviado"));
                lista.add(local);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public List mensajes_enviados_x_usuario(int id) {
        List<Mensaje> lista = new ArrayList<Mensaje>();

        try {
            String cmd = "select * from mensajes where user_id_from ="+id;
            //Statement st = DaoMensajes.getinstance().conex.createStatement();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Mensaje local = new Mensaje(rs.getInt("id"), rs.getInt("user_id_from"), rs.getInt("user_id_to"), rs.getString("remitente"), rs.getString("recipiente"), rs.getDate("fecha"), rs.getString("asunto"), rs.getString("cuerpo"), rs.getBoolean("trash_recibido"), rs.getBoolean("trash_enviado"));
                lista.add(local);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public List mensajes_recibidos_borrados(int id) {
        List<Mensaje> lista = new ArrayList<Mensaje>();

        try {
            String cmd = "select * from mensajes where user_id_to ="+id+" and trash_recibido = 1";
            //Statement st = DaoMensajes.getinstance().conex.createStatement();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Mensaje local = new Mensaje(rs.getInt("id"), rs.getInt("user_id_from"), rs.getInt("user_id_to"), rs.getString("remitente"), rs.getString("recipiente"), rs.getDate("fecha"), rs.getString("asunto"), rs.getString("cuerpo"), rs.getBoolean("trash_recibido"), rs.getBoolean("trash_enviado"));
                lista.add(local);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public List mensajes_enviados_borrados(int id) {
        List<Mensaje> lista = new ArrayList<Mensaje>();

        try {
            String cmd = "select * from mensajes where user_id_from ="+id+" and trash_enviado = 1";
            //Statement st = DaoMensajes.getinstance().conex.createStatement();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Mensaje local = new Mensaje(rs.getInt("id"), rs.getInt("user_id_from"), rs.getInt("user_id_to"), rs.getString("remitente"), rs.getString("recipiente"), rs.getDate("fecha"), rs.getString("asunto"), rs.getString("cuerpo"), rs.getBoolean("trash_recibido"),rs.getBoolean("trash_enviado"));
                lista.add(local);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void enviarMail (int iduf, int idut, String rem, String rec, String asunto, String cuerpo, boolean trash_r, boolean trash_e)
    {
        try {
            conex.setAutoCommit(false);
            PreparedStatement ps = conex.prepareStatement("INSERT INTO mensajes (user_id_from, user_id_to, remitente, recipiente, fecha, asunto, cuerpo, trash_recibido, trash_enviado) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, iduf);
            ps.setInt(2, idut);
            ps.setString(3, rem);
            ps.setString(4, rec);
            Timestamp sqlDate = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(5, sqlDate);
            ps.setString(6, asunto);
            ps.setString(7, cuerpo);
            ps.setBoolean(8, trash_r);
            ps.setBoolean(9, trash_e);
            ps.execute();
            conex.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void eliminar_mensaje (int idu, int id)
    {
        try {
            conex.setAutoCommit(false);
            PreparedStatement ps = conex.prepareStatement("call eliminar_mensaje (?,?)");
            ps.setInt(1, idu);
            ps.setInt(2, id);
            ps.execute();
            conex.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
