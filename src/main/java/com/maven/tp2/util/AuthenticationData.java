package com.maven.tp2.util;

import com.maven.tp2.modelo.Usuario;
import org.joda.time.DateTime;

/**
 * Created by mauro on 13/06/17.
 */


public class AuthenticationData {

    private UsuarioSesion usuario;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public UsuarioSesion getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSesion usuario) {
        this.usuario = usuario;
    }
}

