package com.maven.tp2.controladora;

import com.maven.tp2.Wrappers.UsuarioWrapper;
import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.request.UsuarioRequest;
import com.maven.tp2.servicio.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauro on 18/06/17.
 */
@RestController
@RequestMapping("/api")
public class ControladoraUsuarios {
    public void setService(ServicioUsuarios service) {
        this.service = service;
    }

    @Autowired
    ServicioUsuarios service;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<UsuarioWrapper>> listar_usuarios(@RequestHeader("admin") boolean adm) {
        try
        {
            if (adm) {
                List<Usuario> listausuarios = service.listar_usuarios();
                if (listausuarios.size() > 0) {
                    List<UsuarioWrapper> lista = new ArrayList<UsuarioWrapper>();
                    for (Usuario u : listausuarios) {
                        UsuarioWrapper uw = new UsuarioWrapper(u);
                        lista.add(uw);
                    }
                    return new ResponseEntity<List<UsuarioWrapper>>(lista, HttpStatus.OK);
                } else {
                    return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/usuarios/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<UsuarioWrapper>> listar_usuarios_nombre(@RequestHeader("admin") boolean adm, @RequestParam("nombre") String nombre) {
        try
        {
            if (adm) {
                List<Usuario> listausuarios = service.listar_usuarios_x_nombre(nombre);
                if (listausuarios.size() > 0) {
                    List<UsuarioWrapper> lista = new ArrayList<UsuarioWrapper>();
                    for (Usuario u : listausuarios) {
                        UsuarioWrapper uw = new UsuarioWrapper(u);
                        lista.add(uw);
                    }
                    return new ResponseEntity<List<UsuarioWrapper>>(lista, HttpStatus.OK);
                } else {
                    return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity nuevoUsuario(@RequestBody UsuarioRequest request, @RequestHeader("admin") boolean adm) {
        try {
            if (adm) {
                service.crear_Usuario(request.getNombre_usuario(), request.getPassword(), request.getNombre(), request.getApellido(), request.getDireccion(), request.getTelefono(), request.getCiudad(), request.getProvincia(), request.getPais(), request.getMail(), request.isBorrado(), request.isAdmin());
                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.DELETE)
    public ResponseEntity eliminarUsuario(@RequestHeader("admin") boolean adm, @RequestParam("id") int idu) {
        try {
            if (adm) {
                service.eliminar_Usuario(idu);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
