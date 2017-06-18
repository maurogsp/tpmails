package com.maven.tp2.modelo;

import java.util.Date;

/**
 * Created by mauro on 11/06/17.
 */
public class Mensaje {
        private int id;
        private int user_id_from;
        private int user_id_to;
        private String remitente;
        private String recipiente;
        private Date fecha;
        private String asunto;
        private String cuerpo;// adentro puedo poner html
        private boolean trash_r;
        private boolean trash_e;

        public Mensaje(int id, int user_id_from, int user_id_to, String remitente, String recipiente, Date fecha, String asunto, String cuerpo, boolean trash_r, boolean trash_e) {
            this.setId(id);
            this.setUser_id_from(user_id_from);
            this.setUser_id_to(user_id_to);
            this.setRemitente(remitente);
            this.setRecipiente(recipiente);
            this.setFecha(fecha);
            this.setAsunto(asunto);
            this.setCuerpo(cuerpo);
            this.setTrash_r(trash_r);
            this.setTrash_e(trash_e);
        }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", user_id_from=" + user_id_from +
                ", user_id_to=" + user_id_to +
                ", remitente='" + remitente + '\'' +
                ", recipiente='" + recipiente + '\'' +
                ", fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", trash_r=" + trash_r +
                ", trash_e=" + trash_e +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(int user_id_from) {
        this.user_id_from = user_id_from;
    }

    public int getUser_id_to() {
        return user_id_to;
    }

    public void setUser_id_to(int user_id_to) {
        this.user_id_to = user_id_to;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(String recipiente) {
        this.recipiente = recipiente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public boolean isTrash_r() {
        return trash_r;
    }

    public void setTrash_r(boolean trash_r) {
        this.trash_r = trash_r;
    }

    public boolean isTrash_e() {
        return trash_e;
    }

    public void setTrash_e(boolean trash_e) {
        this.trash_e = trash_e;
    }
}
