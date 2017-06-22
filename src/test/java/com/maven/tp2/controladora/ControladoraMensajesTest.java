package com.maven.tp2.controladora;

import com.maven.tp2.Wrappers.MensajeWrapper;
import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.request.MensajeRequest;
import com.maven.tp2.request.UsuarioRequest;
import com.maven.tp2.servicio.ServicioMensajes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
@RunWith(PowerMockRunner.class)
public class ControladoraMensajesTest {
    ControladoraMensajes cm;
    ServicioMensajes sm;
    ServicioMensajes smvacio;
    ServicioMensajes smerror;
    Mensaje m;
    MensajeWrapper mw;
    MensajeRequest mr;
    List<Mensaje> lista;
    List<MensajeWrapper> listaw;
    ResponseEntity<List<MensajeWrapper>> remwok;
    ResponseEntity<List<MensajeWrapper>> remwnc;
    ResponseEntity err;
    ResponseEntity cr;
    ResponseEntity ok;
    Date fecha;

    @Before
    public void setUp() throws Exception {
        try
        {
            fecha = new Date();
            m = new Mensaje();
            m.setId(1);
            m.setUser_id_from(1);
            m.setUser_id_to(2);
            m.setRemitente("mauro");
            m.setRecipiente("pepe");
            m.setFecha(fecha);
            m.setAsunto("asunto prueba");
            m.setCuerpo("cuerpo prueba");
            m.setTrash_r(false);
            m.setTrash_e(false);
            lista = new ArrayList<Mensaje>();
            lista.add(m);
            listaw = new ArrayList<MensajeWrapper>();
            mw = new MensajeWrapper(m);
            listaw.add(mw);
            mr = new MensajeRequest();
            mr.setIduf(1);
            mr.setIdut(2);
            mr.setFecha(fecha);
            mr.setRemitente("ida");
            mr.setRecipiente("vuelta");
            mr.setAsunto("asunto");
            mr.setCuerpo("cuerpo");
            mr.setTrash_r(false);
            mr.setTrash_e(false);
            sm = Mockito.mock(ServicioMensajes.class);
            smvacio = Mockito.mock(ServicioMensajes.class);
            smerror = Mockito.mock(ServicioMensajes.class);
            Mockito.when(sm.mensajes_recibidos_x_usuario(1)).thenReturn(lista);
            Mockito.when(sm.mensajes_recibidos_x_usuario(2)).thenReturn(new ArrayList<Mensaje>());
            Mockito.when(sm.mensajes_recibidos_x_usuario(3)).thenThrow(new Exception());
            Mockito.when(sm.mensajes_enviados_x_usuario(1)).thenReturn(lista);
            Mockito.when(sm.mensajes_enviados_x_usuario(2)).thenReturn(new ArrayList<Mensaje>());
            Mockito.when(sm.mensajes_enviados_x_usuario(3)).thenThrow(new Exception());
            Mockito.when(sm.mensajes_recibidos_borrados(1)).thenReturn(lista);
            Mockito.when(sm.mensajes_recibidos_borrados(2)).thenReturn(new ArrayList<Mensaje>());
            Mockito.when(sm.mensajes_recibidos_borrados(3)).thenThrow(new Exception());
            Mockito.when(sm.mensajes_enviados_borrados(1)).thenReturn(lista);
            Mockito.when(sm.mensajes_enviados_borrados(2)).thenReturn(new ArrayList<Mensaje>());
            Mockito.when(sm.mensajes_enviados_borrados(3)).thenThrow(new Exception());
            Mockito.doThrow(new Exception()).when(smerror).enviarMensaje(mr.getIduf(),mr.getIdut(),mr.getRemitente(),mr.getRecipiente(),mr.getAsunto(),mr.getCuerpo(),mr.isTrash_r(),mr.isTrash_e());
            Mockito.doThrow(new Exception()).when(sm).eliminarMensaje(2,2);
            remwok = new ResponseEntity<List<MensajeWrapper>>(listaw, HttpStatus.OK);
            remwnc = new ResponseEntity<List<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            err = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            cr = new ResponseEntity(HttpStatus.CREATED);
            ok = new ResponseEntity(HttpStatus.OK);

            cm = new ControladoraMensajes();
            cm.setService(sm);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




    }

    @Test
    public void recibidos_x_usuario_ok()  {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.recibidos_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwok.getBody().get(0).getFecha(),relmw.getBody().get(0).getFecha());
        assertEquals(remwok.getBody().get(0).getAsunto(),relmw.getBody().get(0).getAsunto());

    }

    @Test
    public void recibidos_x_usuario_vacio()
    {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.recibidos_x_usuario(2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwnc.getBody(),relmw.getBody());
    }

    @Test
    public void recibidos_x_usuario_error()
    {
        ResponseEntity res = null;
        try
        {

            res = cm.recibidos_x_usuario(3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(err.getBody(),res.getBody());
    }

    @Test
    public void enviados_x_usuario_ok()  {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.enviados_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwok.getBody().get(0).getFecha(),relmw.getBody().get(0).getFecha());
        assertEquals(remwok.getBody().get(0).getAsunto(),relmw.getBody().get(0).getAsunto());
    }

    @Test
    public void enviados_x_usuario_vacio()
    {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.enviados_x_usuario(2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwnc.getBody(),relmw.getBody());
    }

    @Test
    public void enviados_x_usuario_error()
    {
        ResponseEntity res = null;
        try
        {
            res = cm.enviados_x_usuario(3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(err.getBody(),res.getBody());
    }

    @Test
    public void rec_eliminados_x_usuario_ok()  {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.rec_eliminados_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwok.getBody().get(0).getFecha(),relmw.getBody().get(0).getFecha());
        assertEquals(remwok.getBody().get(0).getAsunto(),relmw.getBody().get(0).getAsunto());
    }

    @Test
    public void rec_eliminados_x_usuario_vacio()
    {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.rec_eliminados_x_usuario(2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwnc.getBody(),relmw.getBody());
    }

    @Test
    public void rec_eliminados_x_usuario_error()
    {
        ResponseEntity res = null;
        try
        {
            res = cm.rec_eliminados_x_usuario(3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(err.getBody(),res.getBody());
    }

    @Test
    public void env_eliminados_x_usuario_ok()  {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.env_eliminados_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwok.getBody().get(0).getFecha(),relmw.getBody().get(0).getFecha());
        assertEquals(remwok.getBody().get(0).getAsunto(),relmw.getBody().get(0).getAsunto());
    }

    @Test
    public void env_eliminados_x_usuario_vacio()
    {
        ResponseEntity<List<MensajeWrapper>> relmw = null;
        try
        {
            relmw = cm.env_eliminados_x_usuario(2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(remwnc.getBody(),relmw.getBody());
    }

    @Test
    public void env_eliminados_x_usuario_error()
    {
        ResponseEntity res = null;
        try
        {
            res = cm.env_eliminados_x_usuario(3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(err.getBody(),res.getBody());
    }

    @Test
    public void nuevoMensajeok()  {
        ResponseEntity re = null;
        try
        {
            re = cm.nuevoMensaje(mr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),cr.getBody());
    }

    @Test
    public void nuevoMensaje_error()
    {
        ResponseEntity re = null;
        try
        {
            cm.setService(smerror);
            re = cm.nuevoMensaje(mr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),err.getBody());
    }

    @Test
    public void eliminarMensaje_ok()  {
        ResponseEntity re = null;
        try
        {
            re = cm.eliminarMensaje(1,1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),ok.getBody());

    }

    @Test
    public void eliminarMensaje_error()
    {
        ResponseEntity re = null;
        try
        {
            re = cm.eliminarMensaje(2,2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),err.getBody());
    }

}