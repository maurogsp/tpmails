package com.maven.tp2.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mauro on 16/06/17.
 */
public class LoginResponseWrapper {
    @JsonProperty
    String sessionId ;


    public LoginResponseWrapper() {

    }

    public LoginResponseWrapper(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
