package com.maven.tp2.controladora;

import com.maven.tp2.Wrappers.MensajeWrapper;
import com.maven.tp2.modelo.Mensaje;
import com.maven.tp2.request.MensajeRequest;
import com.maven.tp2.servicio.ServicioMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauro on 11/06/17.
 */
@RestController
@RequestMapping("/api")
public class ControladoraMensajes {

    @Autowired
    ServicioMensajes service;

    public void setService(ServicioMensajes service) {
        this.service = service;
    }

    @RequestMapping(value = "/mensajes/recibidos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeWrapper>> recibidos_x_usuario(@RequestHeader("id_usuario") int idu) {
        try
        {
            List<Mensaje> listamails = service.mensajes_recibidos_x_usuario(idu);
            if (listamails.size() > 0) {
                List<MensajeWrapper> lista = new ArrayList<MensajeWrapper>();
                for (Mensaje m : listamails)
                {
                    MensajeWrapper mw = new MensajeWrapper(m);
                    lista.add(mw);
                }
                return new ResponseEntity<List<MensajeWrapper>>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mensajes/enviados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeWrapper>> enviados_x_usuario(@RequestHeader("id_usuario") int idu) {
        try
        {
            List<Mensaje> listamails = service.mensajes_enviados_x_usuario(idu);
            if (listamails.size() > 0) {
                List<MensajeWrapper> lista = new ArrayList<MensajeWrapper>();
                for (Mensaje m : listamails)
                {
                    MensajeWrapper mw = new MensajeWrapper(m);
                    lista.add(mw);
                }
                return new ResponseEntity<List<MensajeWrapper>>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mensajes/recibidos_eliminados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeWrapper>> rec_eliminados_x_usuario(@RequestHeader("id_usuario") int idu) {
        try
        {
            List<Mensaje> listamails = service.mensajes_recibidos_borrados(idu);
            if (listamails.size() > 0) {
                List<MensajeWrapper> lista = new ArrayList<MensajeWrapper>();
                for (Mensaje m : listamails)
                {
                    MensajeWrapper mw = new MensajeWrapper(m);
                    lista.add(mw);
                }
                return new ResponseEntity<List<MensajeWrapper>>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mensajes/enviados_eliminados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeWrapper>> env_eliminados_x_usuario(@RequestHeader("id_usuario") int idu) {
        try
        {
            List<Mensaje> listamails = service.mensajes_enviados_borrados(idu);
            if (listamails.size() > 0) {
                List<MensajeWrapper> lista = new ArrayList<MensajeWrapper>();
                for (Mensaje m : listamails)
                {
                    MensajeWrapper mw = new MensajeWrapper(m);
                    lista.add(mw);
                }
                return new ResponseEntity<List<MensajeWrapper>>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mensajes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity nuevoMensaje(@RequestBody MensajeRequest request) {
        try {
            service.enviarMensaje(request.getIduf(),request.getIdut(),request.getRemitente(),request.getRecipiente(),request.getAsunto(),request.getCuerpo(),request.isTrash_r(), request.isTrash_e());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/mensajes", method = RequestMethod.DELETE)
    public ResponseEntity eliminarMensaje(@RequestHeader("id_usuario") int idu, @RequestParam("id") int idm) {
        try {
            service.eliminarMensaje(idu, idm);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
