package com.maven.tp2.controladora;

import com.maven.tp2.response.LoginResponseWrapper;
import com.maven.tp2.servicio.ServicioUsuarios;
import com.maven.tp2.util.SessionData;
import com.maven.tp2.util.UsuarioSesion;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
public class ControladoraUsuariosTest extends TestCase {

    ControladoraUsuarios cu;
    ServicioUsuarios su;
    UsuarioSesion us;
    ResponseEntity<LoginResponseWrapper> lrw;
    ResponseEntity re;
    ResponseEntity rerr;
    ResponseEntity reac;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listar_usuarios() throws Exception {
    }

    @Test
    public void listar_usuarios1() throws Exception {
    }

    @Test
    public void nuevoMensaje() throws Exception {
    }

    @Test
    public void eliminarMensaje() throws Exception {
    }

}