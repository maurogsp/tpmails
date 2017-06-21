package com.maven.tp2.dao;

import com.maven.tp2.modelo.Mensaje;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.*;
import java.util.List;


import static org.junit.Assert.*;

/**
 * Created by mauro on 20/06/17.
 */
@RunWith(PowerMockRunner.class)
public class DaoMensajesTest extends TestCase {
    DaoMensajes dm;
    Date fecha;

    @Before
    public void setUp() throws Exception {
        try
        {
            Connection conn = Mockito.mock(Connection.class);
            Statement statement = Mockito.mock(Statement.class);
            PreparedStatement pstatement = Mockito.mock(PreparedStatement.class);
            Mockito.when(conn.createStatement()).thenReturn(statement);

            //MOCKEO PARA CONSULTA DE TODOS LOS MENSAJES//
            String cmd1 = "select * from mensajes";
            fecha = Date.valueOf("2017-06-20");
            ResultSet resultSet1 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet1.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet1.getInt("id")).thenReturn(1);
            Mockito.when(resultSet1.getInt("user_id_from")).thenReturn(1);
            Mockito.when(resultSet1.getInt("user_id_to")).thenReturn(2);
            Mockito.when(resultSet1.getString("remitente")).thenReturn("prueba1");
            Mockito.when(resultSet1.getString("recipiente")).thenReturn("prueba2");
            Mockito.when(resultSet1.getDate("fecha")).thenReturn(fecha);
            Mockito.when(resultSet1.getString("asunto")).thenReturn("asunto prueba");
            Mockito.when(resultSet1.getString("cuerpo")).thenReturn("cuerpo prueba");
            Mockito.when(resultSet1.getBoolean("trash_recibido")).thenReturn(false);
            Mockito.when(resultSet1.getBoolean("trash_enviado")).thenReturn(false);
            Mockito.when(statement.executeQuery(cmd1)).thenReturn(resultSet1);


            //MOCKEO PARA CONSULTA MENSAJES RECIBIDOS X USUARIO//
            String cmd2 = "select * from mensajes where user_id_to =1";
            fecha = Date.valueOf("2017-06-20");
            ResultSet resultSet2 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet2.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet2.getInt("id")).thenReturn(1);
            Mockito.when(resultSet2.getInt("user_id_from")).thenReturn(1);
            Mockito.when(resultSet2.getInt("user_id_to")).thenReturn(2);
            Mockito.when(resultSet2.getString("remitente")).thenReturn("prueba1");
            Mockito.when(resultSet2.getString("recipiente")).thenReturn("prueba2");
            Mockito.when(resultSet2.getDate("fecha")).thenReturn(fecha);
            Mockito.when(resultSet2.getString("asunto")).thenReturn("asunto prueba1");
            Mockito.when(resultSet2.getString("cuerpo")).thenReturn("cuerpo prueba1");
            Mockito.when(resultSet2.getBoolean("trash_recibido")).thenReturn(false);
            Mockito.when(resultSet2.getBoolean("trash_enviado")).thenReturn(false);
            Mockito.when(statement.executeQuery(cmd2)).thenReturn(resultSet2);

            //MOCKEO PARA CONSULTA MENSAJES ENVIADOS X USUARIO//
            String cmd3 = "select * from mensajes where user_id_from =1";
            fecha = Date.valueOf("2017-06-20");
            ResultSet resultSet3 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet3.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet3.getInt("id")).thenReturn(1);
            Mockito.when(resultSet3.getInt("user_id_from")).thenReturn(1);
            Mockito.when(resultSet3.getInt("user_id_to")).thenReturn(2);
            Mockito.when(resultSet3.getString("remitente")).thenReturn("pruebaenviado2");
            Mockito.when(resultSet3.getString("recipiente")).thenReturn("pruebaenviado2");
            Mockito.when(resultSet3.getDate("fecha")).thenReturn(fecha);
            Mockito.when(resultSet3.getString("asunto")).thenReturn("asunto prueba1");
            Mockito.when(resultSet3.getString("cuerpo")).thenReturn("cuerpo prueba1");
            Mockito.when(resultSet3.getBoolean("trash_recibido")).thenReturn(false);
            Mockito.when(resultSet3.getBoolean("trash_enviado")).thenReturn(false);
            Mockito.when(statement.executeQuery(cmd3)).thenReturn(resultSet3);

            //MOCKEO PARA CONSULTA MENSAJES RECIBIDOS ELIMINADOS X USUARIO//
            String cmd4 = "select * from mensajes where user_id_to =1 and trash_recibido = 1";
            fecha = Date.valueOf("2017-06-20");
            ResultSet resultSet4 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet4.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet4.getInt("id")).thenReturn(1);
            Mockito.when(resultSet4.getInt("user_id_from")).thenReturn(1);
            Mockito.when(resultSet4.getInt("user_id_to")).thenReturn(2);
            Mockito.when(resultSet4.getString("remitente")).thenReturn("pruebaeliminadorecibido");
            Mockito.when(resultSet4.getString("recipiente")).thenReturn("pruebaeliminadorecibido");
            Mockito.when(resultSet4.getDate("fecha")).thenReturn(fecha);
            Mockito.when(resultSet4.getString("asunto")).thenReturn("asunto prueba1");
            Mockito.when(resultSet4.getString("cuerpo")).thenReturn("cuerpo prueba1");
            Mockito.when(resultSet4.getBoolean("trash_recibido")).thenReturn(true);
            Mockito.when(resultSet4.getBoolean("trash_enviado")).thenReturn(false);
            Mockito.when(statement.executeQuery(cmd4)).thenReturn(resultSet4);

            //MOCKEO PARA CONSULTA MENSAJES ENVIADOS ELIMINADOS X USUARIO//
            String cmd5 = "select * from mensajes where user_id_from =1 and trash_enviado = 1";
            fecha = Date.valueOf("2017-06-20");
            ResultSet resultSet5 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet5.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet5.getInt("id")).thenReturn(1);
            Mockito.when(resultSet5.getInt("user_id_from")).thenReturn(1);
            Mockito.when(resultSet5.getInt("user_id_to")).thenReturn(2);
            Mockito.when(resultSet5.getString("remitente")).thenReturn("pruebaeliminadoenviado");
            Mockito.when(resultSet5.getString("recipiente")).thenReturn("pruebaeliminadoenviado");
            Mockito.when(resultSet5.getDate("fecha")).thenReturn(fecha);
            Mockito.when(resultSet5.getString("asunto")).thenReturn("asunto prueba1");
            Mockito.when(resultSet5.getString("cuerpo")).thenReturn("cuerpo prueba1");
            Mockito.when(resultSet5.getBoolean("trash_recibido")).thenReturn(false);
            Mockito.when(resultSet5.getBoolean("trash_enviado")).thenReturn(true);
            Mockito.when(statement.executeQuery(cmd5)).thenReturn(resultSet5);

            //MOCKEO PARA AGREGAR MENSAJE//
            String cmd6 = "INSERT INTO mensajes (user_id_from, user_id_to, remitente, recipiente, fecha, asunto, cuerpo, trash_recibido, trash_enviado) VALUES (?,?,?,?,?,?,?,?,?)";
            Mockito.doThrow(new SQLException()).when(pstatement).setInt(1,2);
            Mockito.when(conn.prepareStatement(cmd6)).thenReturn(pstatement);

            //MOCKEO PARA ELIMINAR MENSAJE//
            String cmd7 = "call eliminar_mensaje (?,?)";
            Mockito.doThrow(new SQLException()).when(pstatement).setInt(2,3);
            Mockito.when(conn.prepareStatement(cmd7)).thenReturn(pstatement);

            dm = new DaoMensajes();
            dm.setConex(conn);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void todos_los_mensajes()  {
        List<Mensaje> lista = null;
        try
        {
            lista = dm.todos_los_mensajes();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals(1,lista.get(0).getUser_id_from());
        assertEquals(2,lista.get(0).getUser_id_to());
        assertEquals("prueba1",lista.get(0).getRemitente());
        assertEquals("prueba2",lista.get(0).getRecipiente());
        assertEquals(fecha,lista.get(0).getFecha());
        assertEquals("asunto prueba",lista.get(0).getAsunto());
        assertEquals("cuerpo prueba",lista.get(0).getCuerpo());
        assertEquals(false,lista.get(0).isTrash_r());
        assertEquals(false,lista.get(0).isTrash_e());
    }



    @Test
    public void mensajes_recibidos_x_usuario() {
        List<Mensaje> lista = null;
        try
        {
            lista = dm.mensajes_recibidos_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals(1,lista.get(0).getUser_id_from());
        assertEquals(2,lista.get(0).getUser_id_to());
        assertEquals("prueba1",lista.get(0).getRemitente());
        assertEquals("prueba2",lista.get(0).getRecipiente());
        assertEquals(fecha,lista.get(0).getFecha());
        assertEquals("asunto prueba1",lista.get(0).getAsunto());
        assertEquals("cuerpo prueba1",lista.get(0).getCuerpo());
        assertEquals(false,lista.get(0).isTrash_r());
        assertEquals(false,lista.get(0).isTrash_e());
    }

    @Test
    public void mensajes_recibidos_x_usuario_error() {
        boolean error = false;
        try
        {
            List<Mensaje>lista = dm.mensajes_recibidos_x_usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);

    }



    @Test
    public void mensajes_enviados_x_usuario()  {
        List<Mensaje> lista = null;
        try
        {
            lista = dm.mensajes_enviados_x_usuario(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals(1,lista.get(0).getUser_id_from());
        assertEquals(2,lista.get(0).getUser_id_to());
        assertEquals("pruebaenviado2",lista.get(0).getRemitente());
        assertEquals("pruebaenviado2",lista.get(0).getRecipiente());
        assertEquals(fecha,lista.get(0).getFecha());
        assertEquals("asunto prueba1",lista.get(0).getAsunto());
        assertEquals("cuerpo prueba1",lista.get(0).getCuerpo());
        assertEquals(false,lista.get(0).isTrash_r());
        assertEquals(false,lista.get(0).isTrash_e());

    }

    @Test
    public void mensajes_enviados_x_usuario_error()
    {
        boolean error = false;
        try
        {
            List<Mensaje>lista = dm.mensajes_enviados_x_usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void mensajes_recibidos_borrados() {
        List<Mensaje> lista = null;
        try
        {
            lista = dm.mensajes_recibidos_borrados(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals(1,lista.get(0).getUser_id_from());
        assertEquals(2,lista.get(0).getUser_id_to());
        assertEquals("pruebaeliminadorecibido",lista.get(0).getRemitente());
        assertEquals("pruebaeliminadorecibido",lista.get(0).getRecipiente());
        assertEquals(fecha,lista.get(0).getFecha());
        assertEquals("asunto prueba1",lista.get(0).getAsunto());
        assertEquals("cuerpo prueba1",lista.get(0).getCuerpo());
        assertEquals(true,lista.get(0).isTrash_r());
        assertEquals(false,lista.get(0).isTrash_e());

    }

    @Test
    public void mensajes_recibidos_borrados_error()
    {
        boolean error = false;
        try
        {
            List<Mensaje>lista = dm.mensajes_recibidos_borrados(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);

    }

    @Test
    public void mensajes_enviados_borrados()  {
        List<Mensaje> lista = null;
        try
        {
            lista = dm.mensajes_enviados_borrados(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals(1,lista.get(0).getUser_id_from());
        assertEquals(2,lista.get(0).getUser_id_to());
        assertEquals("pruebaeliminadoenviado",lista.get(0).getRemitente());
        assertEquals("pruebaeliminadoenviado",lista.get(0).getRecipiente());
        assertEquals(fecha,lista.get(0).getFecha());
        assertEquals("asunto prueba1",lista.get(0).getAsunto());
        assertEquals("cuerpo prueba1",lista.get(0).getCuerpo());
        assertEquals(false,lista.get(0).isTrash_r());
        assertEquals(true,lista.get(0).isTrash_e());

    }

    @Test
    public void mensajes_enviados_borrados_error()
    {
        boolean error = false;
        try
        {
            List<Mensaje>lista = dm.mensajes_enviados_borrados(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void enviarMail() {
        boolean ok = false;
        try
        {
            dm.enviarMail(1, 2, "rte", "receptor", "asunto", "cuerpo", false, false);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);
    }

    @Test
    public void enviarMail_error()
    {
        boolean error = false;
        try
        {
            dm.enviarMail(2, 2, "rte", "receptor", "asunto", "cuerpo", false, false);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);

    }

    @Test
    public void eliminar_mensaje()  {
        boolean ok = false;
        try
        {
            dm.eliminar_mensaje(1,1);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);

    }

    @Test
    public void eliminar_mensaje_error()
    {
        boolean error = false;
        try
        {
            dm.eliminar_mensaje(1,3);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

}