package com.application.Exercice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

// JwtResponse allows to get the token from the response body
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String token;

 
}
