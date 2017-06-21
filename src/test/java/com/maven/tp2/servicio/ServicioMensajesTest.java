package com.maven.tp2.servicio;

import com.maven.tp2.dao.DaoMensajes;
import com.maven.tp2.dao.DaoUsuarios;
import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
@RunWith(PowerMockRunner.class)
public class ServicioMensajesTest extends TestCase {

    ServicioMensajes sm;
    Mensaje m;
    List lista;
    DaoMensajes dmerror = Mockito.mock(DaoMensajes.class);
    Date fecha;


    @Before
    public void setUp() throws Exception {

        try
        {
            fecha = new Date();
            m = new Mensaje(1, 1, 2, "rteprueba", "rpteprueba", fecha, "asuntoprueba", "cuerpo_prueba", false, false);
            lista = new ArrayList<Mensaje>();
            lista.add(m);
            DaoMensajes dm = Mockito.mock(DaoMensajes.class);
            Mockito.when(dm.todos_los_mensajes()).thenReturn(lista);
            Mockito.when(dmerror.todos_los_mensajes()).thenThrow(new Exception());
            Mockito.when(dm.mensajes_recibidos_x_usuario(1)).thenReturn(lista);
            Mockito.when(dm.mensajes_recibidos_x_usuario(2)).thenThrow(new Exception());
            Mockito.when(dm.mensajes_enviados_x_usuario(1)).thenReturn(lista);
            Mockito.when(dm.mensajes_enviados_x_usuario(2)).thenThrow(new Exception());
            Mockito.when(dm.mensajes_recibidos_borrados(1)).thenReturn(lista);
            Mockito.when(dm.mensajes_recibidos_borrados(2)).thenThrow(new Exception());
            Mockito.when(dm.mensajes_enviados_borrados(1)).thenReturn(lista);
            Mockito.when(dm.mensajes_enviados_borrados(2)).thenThrow(new Exception());
            Mockito.doThrow(new Exception()).when(dm).enviarMail(1, 2, "rteprueba_error", "rpteprueba", "asuntoprueba", "cuerpo_prueba", false, false);
            Mockito.doThrow(new Exception()).when(dm).eliminar_mensaje(1,3);
            sm = new ServicioMensajes();
            sm.setBd(dm);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void todos_los_mensajes()  {
        List<Mensaje> l = null;
        try
        {
            l = sm.todos_los_mensajes();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void todos_los_mensajes_error()
    {
        boolean error = false;
        try
        {
            sm.setBd(dmerror);
            List<Usuario> l = sm.todos_los_mensajes();
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void mensajes_recibidos_x_usuario() {
        List<Mensaje> l = null;
        try
        {
            l = sm.mensajes_recibidos_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void mensajes_recibidos_x_usuario_error()
    {
        boolean error = false;
        try
        {
            List<Usuario> l = sm.mensajes_recibidos_x_usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void mensajes_enviados_x_usuario() {

        List<Mensaje> l = null;
        try
        {
            l = sm.mensajes_enviados_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void mensajes_enviados_x_usuario_error()
    {
        boolean error = false;
        try
        {
            List<Usuario> l = sm.mensajes_enviados_x_usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void mensajes_recibidos_borrados() {
        List<Mensaje> l = null;
        try
        {
            l = sm.mensajes_recibidos_borrados(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void mensajes_recibidos_borrados_error()
    {
        boolean error = false;
        try
        {
            List<Usuario> l = sm.mensajes_recibidos_borrados(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void mensajes_enviados_borrados() {
        List<Mensaje> l = null;
        try
        {
            l = sm.mensajes_enviados_borrados(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);

    }

    @Test
    public void mensajes_enviados_borrados_error()
    {
        boolean error = false;
        try
        {
            List<Usuario> l = sm.mensajes_enviados_borrados(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void enviarMensaje() {
        boolean ok = false;
        try
        {
            sm.enviarMensaje( 1, 2, "rteprueba", "rpteprueba", "asuntoprueba", "cuerpo_prueba", false, false);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);

    }

    @Test
    public void enviarMensaje_error()
    {
        boolean error = false;
        try
        {
            sm.enviarMensaje( 1, 2, "rteprueba_error", "rpteprueba", "asuntoprueba", "cuerpo_prueba", false, false);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void eliminarMensaje() {
        boolean ok = false;
        try
        {
            sm.eliminarMensaje(1,2);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);
    }

    @Test
    public void eliminarMensaje_error()
    {
        boolean error = false;
        try
        {
            sm.eliminarMensaje(1,3);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

}