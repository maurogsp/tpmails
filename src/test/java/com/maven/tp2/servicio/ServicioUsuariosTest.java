package com.maven.tp2.servicio;

import com.maven.tp2.dao.DaoUsuarios;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.util.UsuarioSesion;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
@RunWith(PowerMockRunner.class)
public class ServicioUsuariosTest extends TestCase {

    ServicioUsuarios su;
    UsuarioSesion us;
    Usuario u;
    List lista;
    DaoUsuarios duerror = Mockito.mock(DaoUsuarios.class);

    @Before
    public void setUp() throws Exception {

        try
        {
            us = new UsuarioSesion();
            us.setId(1);
            us.setNombreUsuario("prueba");
            us.setPassword("123456");
            us.setAdmin(true);
            u = new Usuario();
            u.setId(1);
            u.setNombre_usuario("prueba");
            u.setContrasenia("123456");
            u.setNombre("mauro");
            u.setApellido("gaspar");
            u.setDireccion("a");
            u.setTelefono("112233");
            u.setCiudad("mardel");
            u.setProvincia("baires");
            u.setPais("argentina");
            u.setMail("prueba@prueba.com");
            u.setBorrado(false);
            u.setAdmin(true);
            lista = new ArrayList<Usuario>();
            lista.add(u);
            DaoUsuarios du = Mockito.mock(DaoUsuarios.class);
            Mockito.when(du.get("prueba","123456")).thenReturn(us);
            Mockito.when(du.get("prueba2","123456")).thenThrow(new Exception());
            Mockito.when(du.listar_usuarios()).thenReturn(lista);
            Mockito.when(duerror.listar_usuarios()).thenThrow(new Exception());
            Mockito.when(du.listar_usuarios_x_nombre("mauro")).thenReturn(lista);
            Mockito.when(du.listar_usuarios_x_nombre("mauro2")).thenThrow(new Exception());
            Mockito.doThrow(new Exception()).when(du).crearUsuario("nombre_usuario2", "pass", "nombre", "apellido", "direccion", "telefono", "ciudad", "provincia", "pais", "mail", true, true);
            Mockito.doThrow(new Exception()).when(du).eliminar_usuario(2);
            su = new ServicioUsuarios();
            su.setUsuarioDao(du);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void login()  {
        UsuarioSesion p = null;
        try
        {
            p = su.login("prueba","123456");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(us,p);
    }

    @Test
    public void login_error()
    {
        boolean error = false;
        try
        {
            UsuarioSesion p = su.login("prueba2","123456");
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void listar_usuarios() {
        List<Usuario> l = null;
        try
        {
            l = su.listar_usuarios();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void listar_usuarios_error()
    {
        boolean error = false;
        try
        {
           su.setUsuarioDao(duerror);
           List<Usuario> l = su.listar_usuarios();
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }



    @Test
    public void listar_usuarios_x_nombre() {
        List<Usuario> l = null;
        try
        {
            l = su.listar_usuarios_x_nombre("mauro");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(lista,l);
    }

    @Test
    public void listar_usuarios_x_nombre_error()
    {
        boolean error = false;
        try
        {
            List<Usuario> l = su.listar_usuarios_x_nombre("mauro2");
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void crear_Usuario()  {
        boolean ok = false;
        try
        {
            su.crear_Usuario("nombre_usuario", "pass", "nombre", "apellido", "direccion", "telefono", "ciudad", "provincia", "pais", "mail", true, true);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);
    }

    @Test
    public void crear_Usuario_error()
    {
        boolean error = false;
        try
        {
            su.crear_Usuario("nombre_usuario2", "pass", "nombre", "apellido", "direccion", "telefono", "ciudad", "provincia", "pais", "mail", true, true);

        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void eliminar_Usuario()  {
        boolean ok = false;
        try
        {
            su.eliminar_Usuario(1);
            ok = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertTrue(ok);

    }

    @Test
    public void eliminar_usuario_error()
    {
        boolean error = false;
        try
        {
            su.eliminar_Usuario(2);
        }
        catch (Exception e)
        {
            error = true;
        }
        assertTrue(error);
    }

}