package com.maven.tp2.controladora;

import com.maven.tp2.Wrappers.UsuarioWrapper;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.request.UsuarioRequest;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mauro on 21/06/17.
 */
@RunWith(PowerMockRunner.class)
public class ControladoraUsuariosTest extends TestCase {

    ControladoraUsuarios cu;
    ServicioUsuarios su;
    ServicioUsuarios suvacio;
    ServicioUsuarios suerror;
    Usuario u;
    UsuarioWrapper uw;
    UsuarioRequest ur;
    List<Usuario> lista;
    List<UsuarioWrapper> listaw;
    ResponseEntity<List<UsuarioWrapper>> luw;
    ResponseEntity<List<UsuarioWrapper>> luwv;
    ResponseEntity<List<UsuarioWrapper>> re;
    ResponseEntity rerr;
    ResponseEntity recre;
    ResponseEntity reok;

    @Before
    public void setUp() throws Exception {
        try
        {
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
            listaw= new ArrayList<UsuarioWrapper>();
            uw = new UsuarioWrapper(u);
            listaw.add(uw);
            ur = new UsuarioRequest();
            ur.setId(1);
            ur.setNombre_usuario("prueba");
            ur.setPassword("1234");
            ur.setNombre("mauro");
            ur.setApellido("gaspar");
            ur.setDireccion("abc123");
            ur.setTelefono("112233");
            ur.setCiudad("mardel");
            ur.setProvincia("baires");
            ur.setPais("argentina");
            ur.setMail("prueba@prueba");
            ur.setBorrado(false);
            ur.setAdmin(true);
            su = Mockito.mock(ServicioUsuarios.class);
            suvacio = Mockito.mock(ServicioUsuarios.class);
            suerror = Mockito.mock(ServicioUsuarios.class);
            Mockito.when(su.listar_usuarios()).thenReturn(lista);
            Mockito.when(suvacio.listar_usuarios()).thenReturn(new ArrayList<Usuario>());
            Mockito.when(suerror.listar_usuarios()).thenReturn(null);
            Mockito.when(su.listar_usuarios_x_nombre("mau")).thenReturn(lista);
            Mockito.when(su.listar_usuarios_x_nombre("vacio")).thenReturn(new ArrayList<Usuario>());
            Mockito.when(su.listar_usuarios_x_nombre("error")).thenReturn(null);
            Mockito.doThrow(new Exception()).when(suerror).crear_Usuario(ur.getNombre_usuario(),ur.getPassword(),ur.getNombre(),ur.getApellido(),ur.getDireccion(),ur.getTelefono(),ur.getCiudad(),ur.getProvincia(),ur.getPais(),ur.getMail(),ur.isBorrado(),ur.isAdmin());
            Mockito.doThrow(new Exception()).when(suerror).eliminar_Usuario(2);
            luw = new ResponseEntity<List<UsuarioWrapper>>(listaw, HttpStatus.OK);
            luwv = new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
            rerr = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            re = new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
            recre = new ResponseEntity(HttpStatus.CREATED);
            reok = new ResponseEntity(HttpStatus.OK);

            cu = new ControladoraUsuarios();
            cu.setService(su);


        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void listar_usuarios()  {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(luw.getBody().get(0).getId(),reluw.getBody().get(0).getId());
        assertEquals(luw.getBody().get(0).getNombre(),reluw.getBody().get(0).getNombre());
    }

    @Test
    public void listar_usuarios_vacios()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            cu.setService(suvacio);
            reluw = cu.listar_usuarios(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(luwv.getBody(),reluw.getBody());
    }

    @Test
    public void listar_usuarios_error()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            cu.setService(suerror);
            reluw = cu.listar_usuarios(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(rerr.getBody(),reluw.getBody());

    }

    @Test
    public void listar_usuarios_prohibido()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),reluw.getBody());

    }

    @Test
    public void listar_usuarios_x_nombre()  {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios_nombre(true,"mau");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(luw.getBody().get(0).getId(),reluw.getBody().get(0).getId());
        assertEquals(luw.getBody().get(0).getNombre(),reluw.getBody().get(0).getNombre());
    }

    @Test
    public void listar_usuarios_x_nombre_vacio()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios_nombre(true,"vacio");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(luwv.getBody(),reluw.getBody());
    }

    @Test
    public void listar_usuarios_x_nombre_prohibido()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios_nombre(false,"mau");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),reluw.getBody());
    }

    @Test
    public void listar_usuarios_x_nombre_error()
    {
        ResponseEntity<List<UsuarioWrapper>> reluw = null;
        try
        {
            reluw = cu.listar_usuarios_nombre(true,"error");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(rerr.getBody(),reluw.getBody());
    }

    @Test
    public void nuevoUsuario() {
        ResponseEntity re = null;
        try
        {
           re = cu.nuevoUsuario(ur,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),recre.getBody());
    }

    @Test
    public void nuevoUsuario_prohibido()
    {
        ResponseEntity res = null;
        try
        {
            res = cu.nuevoUsuario(ur,false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(res.getBody(),re.getBody());
    }

    @Test
    public void nuevoUsuario_error()
    {
        ResponseEntity re = null;
        try
        {
            cu.setService(suerror);
            re = cu.nuevoUsuario(ur,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),rerr.getBody());
    }


    @Test
    public void eliminarUsuario() {
        ResponseEntity re = null;
        try
        {
            re = cu.eliminarUsuario(true,1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),reok.getBody());

    }

    @Test
    public void eliminarUsuario_prohibido()
    {
        ResponseEntity res = null;
        try
        {
            res = cu.eliminarUsuario(false,1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(res.getBody(),re.getBody());
    }

    @Test
    public void eliminarUsuario_error()
    {
        ResponseEntity re = null;
        try
        {
            cu.setService(suerror);
            re = cu.eliminarUsuario(true,2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(re.getBody(),rerr.getBody());
    }
}