package com.maven.tp2.dao;

import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by mauro on 20/06/17.
 */
@RunWith(PowerMockRunner.class)
public class DaoUsuariosTest extends TestCase {
    DaoUsuarios du;

    @Before
    public void setUp() throws Exception {
        try
        {
            Connection conn = Mockito.mock(Connection.class);
            Statement statement = Mockito.mock(Statement.class);
            PreparedStatement pstatement = Mockito.mock(PreparedStatement.class);
            Mockito.when(conn.createStatement()).thenReturn(statement);

            //MOCKEO PARA CONSULTA DE GET//
            String cmd1 = "select id, nombre_usuario, pass, admin from usuarios where nombre_usuario = 'prueba' and pass ='123456'";
            ResultSet resultSet1 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet1.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet1.getInt("id")).thenReturn(1);
            Mockito.when(resultSet1.getString("nombre_usuario")).thenReturn("prueba");
            Mockito.when(resultSet1.getString("pass")).thenReturn("123456");
            Mockito.when(resultSet1.getBoolean("admin")).thenReturn(false);
            Mockito.when(statement.executeQuery(cmd1)).thenReturn(resultSet1);

            //MOCKEO CONSULTA LISTAR_USUARIOS//
            String cmd2 = "select * from usuarios";
            ResultSet resultSet2 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet2.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet2.getInt("id")).thenReturn(1);
            Mockito.when(resultSet2.getString("nombre_usuario")).thenReturn("prueba");
            Mockito.when(resultSet2.getString("pass")).thenReturn("123456");
            Mockito.when(resultSet2.getString("nombre")).thenReturn("mauro");
            Mockito.when(resultSet2.getString("apellido")).thenReturn("gaspar");
            Mockito.when(resultSet2.getString("direccion")).thenReturn("pruebadir");
            Mockito.when(resultSet2.getString("telefono")).thenReturn("112233");
            Mockito.when(resultSet2.getString("ciudad")).thenReturn("mardel");
            Mockito.when(resultSet2.getString("provincia")).thenReturn("baires");
            Mockito.when(resultSet2.getString("pais")).thenReturn("argentina");
            Mockito.when(resultSet2.getString("mail")).thenReturn("prueba@prueba.com");
            Mockito.when(resultSet2.getBoolean("borrado")).thenReturn(false);
            Mockito.when(resultSet2.getBoolean("admin")).thenReturn(true);
            Mockito.when(statement.executeQuery(cmd2)).thenReturn(resultSet2);

            //MOCKEO CONSULTA LISTAR USUARIOS X NOMBRE
            String cmd3 = "select * from usuarios where nombre like '%mau%'";
            ResultSet resultSet3 = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet3.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet3.getInt("id")).thenReturn(1);
            Mockito.when(resultSet3.getString("nombre_usuario")).thenReturn("prueba");
            Mockito.when(resultSet3.getString("pass")).thenReturn("123456");
            Mockito.when(resultSet3.getString("nombre")).thenReturn("mauro");
            Mockito.when(resultSet3.getString("apellido")).thenReturn("gaspar");
            Mockito.when(resultSet3.getString("direccion")).thenReturn("pruebadir");
            Mockito.when(resultSet3.getString("telefono")).thenReturn("112233");
            Mockito.when(resultSet3.getString("ciudad")).thenReturn("mardel");
            Mockito.when(resultSet3.getString("provincia")).thenReturn("baires");
            Mockito.when(resultSet3.getString("pais")).thenReturn("argentina");
            Mockito.when(resultSet3.getString("mail")).thenReturn("prueba@prueba.com");
            Mockito.when(resultSet3.getBoolean("borrado")).thenReturn(false);
            Mockito.when(resultSet3.getBoolean("admin")).thenReturn(true);
            Mockito.when(statement.executeQuery(cmd3)).thenReturn(resultSet3);

            //MOCK AGREGAR USUARIO
            String cmd4 = "INSERT INTO usuarios (nombre_usuario, pass, nombre, apellido, direccion, telefono, ciudad, provincia, pais, mail, borrado, admin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            Mockito.doThrow(new SQLException()).when(pstatement).setString(1,"nombre_usuario1");
            Mockito.when(conn.prepareStatement(cmd4)).thenReturn(pstatement);

            //MOCK PARA ELIMINAR USUARIO
            String cmd5 = "update usuarios set borrado = 1 where id = ?";
            Mockito.doThrow(new SQLException()).when(pstatement).setInt(1,2);
            Mockito.when(conn.prepareStatement(cmd5)).thenReturn(pstatement);


            du = new DaoUsuarios();
            du.setConex(conn);
        }

         catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void get() {
        UsuarioSesion u = null;
        try
    {
        u = du.get("prueba", "123456");
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
        assertEquals("prueba", u.getNombreUsuario() );
        assertEquals("123456", u.getPassword() );
    }

    @Test
    public void getvacio() {
        boolean vacio  = false;
        try {
             UsuarioSesion u = du.get("prueba2", "654321");
            }
            catch (Exception e)
        {
            vacio = true;
        }
        assertTrue(vacio);

    }

    @Test
    public void listar_usuarios() {
        List<Usuario> lista = null;
        try
        {
            lista = du.listar_usuarios();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(1, lista.get(0).getId());
        assertEquals("prueba",lista.get(0).getNombre_usuario());
        assertEquals("123456",lista.get(0).getContrasenia());
        assertEquals("mauro",lista.get(0).getNombre());
        assertEquals("gaspar",lista.get(0).getApellido());
        assertEquals("pruebadir",lista.get(0).getDireccion());
        assertEquals("112233",lista.get(0).getTelefono());
        assertEquals("mardel",lista.get(0).getCiudad());
        assertEquals("baires",lista.get(0).getProvincia());
        assertEquals("argentina",lista.get(0).getPais());
        assertEquals("prueba@prueba.com",lista.get(0).getMail());
        assertTrue(!lista.get(0).isBorrado());
        assertTrue(lista.get(0).isAdmin());
    }


    @Test
    public void listar_usuarios_x_nombre()  {
        List<Usuario> lista = null;
        try
        {
            lista = du.listar_usuarios_x_nombre("mau");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(lista.size()==1);
        assertEquals(lista.get(0).getId(),1);
        assertEquals("prueba",lista.get(0).getNombre_usuario());
        assertEquals("123456",lista.get(0).getContrasenia());
        assertEquals("mauro",lista.get(0).getNombre());
        assertEquals("gaspar",lista.get(0).getApellido());
        assertEquals("pruebadir",lista.get(0).getDireccion());
        assertEquals("112233",lista.get(0).getTelefono());
        assertEquals("mardel",lista.get(0).getCiudad());
        assertEquals("baires",lista.get(0).getProvincia());
        assertEquals("argentina",lista.get(0).getPais());
        assertEquals("prueba@prueba.com",lista.get(0).getMail());
        assertTrue(!lista.get(0).isBorrado());
        assertTrue(lista.get(0).isAdmin());

    }

    @Test
    public void listar_usuarios_x_nombre_error() {
        boolean error = false;
        try
        {
            List<Usuario>lista = du.listar_usuarios_x_nombre("ma");
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void crearUsuario()  {
        boolean ok = false;
        try
        {
           du.crearUsuario("nombre_usuario", "pass", "nombre", "apellido", "direccion", "telefono", "ciudad", "provincia", "pais", "mail", true, true);
           ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);

    }

    @Test
    public void crearUsuario_error() {
        boolean error = false;
        try
        {
            du.crearUsuario("nombre_usuario1", "pass", "nombre", "apellido", "direccion", "telefono", "ciudad", "provincia", "pais", "mail", true, true);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);

    }

    @Test
    public void eliminar_usuario() {
        boolean ok = false;
        try
        {
            du.eliminar_usuario(1);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);

    }

    @Test
    public void eliminar_usuario_error() {
        boolean error = false;
        try
        {
            du.eliminar_usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }
}