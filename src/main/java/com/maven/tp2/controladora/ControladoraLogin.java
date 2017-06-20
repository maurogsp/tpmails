package com.maven.tp2.controladora;

import com.maven.tp2.modelo.Usuario;
import com.maven.tp2.response.LoginResponseWrapper;
import com.maven.tp2.servicio.ServicioUsuarios;
import com.maven.tp2.util.SessionData;
import com.maven.tp2.util.UsuarioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mauro on 13/06/17.
 */
@RestController
@RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladoraLogin {

    @Autowired
    ServicioUsuarios serviusuario;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LoginResponseWrapper> getById(@RequestParam("user") String nombreUsuario, @RequestParam("pwd") String pwd){
        UsuarioSesion u = serviusuario.login(nombreUsuario, pwd);
        if (null != u) {
            String sessionId = sessionData.addSession(u);
            return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @RequestMapping("/logout")
    public @ResponseBody ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}