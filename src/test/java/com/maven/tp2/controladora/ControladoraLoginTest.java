package com.maven.tp2.controladora;

import com.maven.tp2.response.LoginResponseWrapper;
import com.maven.tp2.servicio.ServicioUsuarios;
import com.maven.tp2.util.SessionData;
import com.maven.tp2.util.UsuarioSesion;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
@RunWith(PowerMockRunner.class)
public class ControladoraLoginTest extends TestCase{

    ControladoraLogin cl;
    ServicioUsuarios su;
    SessionData sd;
    UsuarioSesion us;
    ResponseEntity<LoginResponseWrapper> lrw;
    ResponseEntity re;
    ResponseEntity rerr;
    ResponseEntity reac;

    @Before
    public void setUp()  {
        try
        {
            us = new UsuarioSesion();
            us.setId(1);
            us.setNombreUsuario("prueba");
            us.setPassword("123456");
            us.setAdmin(true);
            su = Mockito.mock(ServicioUsuarios.class);
            sd = Mockito.mock(SessionData.class);
            Mockito.when(su.login("prueba","123456")).thenReturn(us);
            Mockito.when(su.login("prueba2","123456")).thenReturn(null);
            Mockito.doThrow(new Exception()).when(su).login("prueba3","123456");
            Mockito.when(sd.addSession(us)).thenReturn("112233");
            Mockito.doThrow(new IllegalArgumentException()).when(sd).removeSession("112244");
            lrw = new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper("112233"), HttpStatus.OK);
            re = new ResponseEntity(HttpStatus.FORBIDDEN);
            rerr = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            reac = new ResponseEntity(HttpStatus.ACCEPTED);

            cl = new ControladoraLogin();
            cl.setServiusuario(su);
            cl.setSessionData(sd);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }




    }

    @Test
    public void getById1_ok()  {
        ResponseEntity<LoginResponseWrapper>lg = null;
        try
        {
            lg = cl.getById1("prueba","123456");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lg.getBody().getSessionId(),lrw.getBody().getSessionId());

    }

    @Test
    public void getById1_no_log()  {
        ResponseEntity ret = null;
        try
        {
            ret = cl.getById1("prueba2","123456");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(ret.getBody(),re.getBody());

    }

    @Test
    public void getById1_error()
    {
        ResponseEntity ret = null;
        try
        {
            ret = cl.getById1("prueba3","123456");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(rerr.getBody(),ret.getBody());

    }

    @Test
    public void getById2_ok() {
        ResponseEntity ret = null;
        try
        {
            ret = cl.getById2("112233");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(ret.getBody(),reac.getBody());
    }

    @Test
    public void getById2_error()
    {
        ResponseEntity ret = null;
        try
        {
            ret = cl.getById2("112244");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(rerr.getBody(),ret.getBody());
    }

}