package com.maven.tp2.Wrappers;

import com.maven.tp2.modelo.Mensaje;

import java.util.Date;

/**
 * Created by mauro on 18/06/17.
 */
public class MensajeWrapper {

    private String remitente;
    private String recipiente;
    private Date fecha;
    private String asunto;
    private String cuerpo;



    public void setRemitente(String remitente) {
        this.remitente = remitente;
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



    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public MensajeWrapper(Mensaje msje) {
        this.setRemitente(msje.getRemitente());
        this.setRecipiente(msje.getRecipiente());
        this.setFecha(msje.getFecha());
        this.setAsunto(msje.getAsunto());
        this.setCuerpo(msje.getCuerpo());
    }
}
