package com.maven.tp2.servicio;

import com.maven.tp2.dao.DaoMensajes;
import com.maven.tp2.modelo.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauro on 11/06/17.
 */
@Service
public class ServicioMensajes {
    @Autowired
    DaoMensajes bd;

    public void setBd(DaoMensajes bd) {
        this.bd = bd;
    }

    public ServicioMensajes() {

    }

    public List todos_los_mensajes() throws Exception {
        List lista = null;
        try {
            lista = bd.todos_los_mensajes();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List mensajes_recibidos_x_usuario(int id) throws Exception {
        List lista = null;
        try {
            lista = bd.mensajes_recibidos_x_usuario(id);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List mensajes_enviados_x_usuario(int id) throws Exception{
        List lista = null;
        try {
            lista = bd.mensajes_enviados_x_usuario(id);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List mensajes_recibidos_borrados(int id) throws Exception{
        List lista = null;
        try {
            lista = bd.mensajes_recibidos_borrados(id);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List mensajes_enviados_borrados(int id) throws Exception {
        List lista = null;
        try {
            lista = bd.mensajes_enviados_borrados(id);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public void enviarMensaje(int user_id_from, int user_id_to, String remitente, String recipiente, String asunto, String cuerpo, boolean trash_r, boolean trash_e) throws Exception{
        try {
            bd.enviarMail(user_id_from, user_id_to, remitente, recipiente, asunto, cuerpo, trash_r, trash_e);
        } catch (Exception e) {
            throw e;
        }

    }

    public void eliminarMensaje(int idu, int idm) throws Exception {
        try {
            bd.eliminar_mensaje(idu, idm);
        } catch (Exception e) {
            throw e;
        }
    }


}
