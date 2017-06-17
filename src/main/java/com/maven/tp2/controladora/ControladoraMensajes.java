package com.maven.tp2.controladora;

import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.request.MensajeRequest;
import com.maven.tp2.servicio.ServicioMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mauro on 11/06/17.
 */
@RestController
@RequestMapping("/api")
public class ControladoraMensajes {
    @Autowired
    ServicioMensajes service;

    @RequestMapping(value = "/recibidos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Mensaje>> recibidos_x_usuario(@RequestHeader("id_usuario") int idu) {
        List<Mensaje> listamails = service.mensajes_x_usuario(idu);
        if (listamails.size() > 0) {
            return new ResponseEntity<List<Mensaje>>(listamails, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Mensaje>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/eliminados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Mensaje>> eliminados_x_usuario(@RequestParam("idb") int id) {
        List<Mensaje> listamails = service.mensajes_borrados(id);
        if (listamails.size() > 0) {
            return new ResponseEntity<List<Mensaje>>(listamails, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Mensaje>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/nuevomensaje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity nuevoMensaje(@RequestBody MensajeRequest request) {
        try {
            service.enviarMensaje(request.getIduf(),request.getIdut(),request.getRemitente(),request.getRecipiente(),request.getAsunto(),request.getCuerpo(),request.isTrash());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminarmensaje", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity eliminarMensaje(@RequestBody MensajeRequest request) {
        try {
            service.enviarMensaje(request.getIduf(),request.getIdut(),request.getRemitente(),request.getRecipiente(),request.getAsunto(),request.getCuerpo(),request.isTrash());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
