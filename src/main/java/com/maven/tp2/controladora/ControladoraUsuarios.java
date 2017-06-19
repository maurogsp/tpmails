package com.maven.tp2.controladora;

import com.maven.tp2.Wrappers.UsuarioWrapper;
import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.modelo.Usuario;
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
    @Autowired
    ServicioUsuarios service;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<UsuarioWrapper>> listar_usuarios(@RequestHeader("id_usuario") int idu, @RequestHeader("admin") boolean adm) {
        if (adm)
        {
            List<Usuario> listausuarios = service.listar_usuarios();
            if (listausuarios.size() > 0) {
                List<UsuarioWrapper> lista = new ArrayList<UsuarioWrapper>();
                for (Usuario u : listausuarios)
                {
                    UsuarioWrapper uw = new UsuarioWrapper(u);
                    lista.add(uw);
                }
                return new ResponseEntity<List<UsuarioWrapper>>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
            }
        }
        else
        {
            return new ResponseEntity<List<UsuarioWrapper>>(HttpStatus.FORBIDDEN);
        }
    }
}
