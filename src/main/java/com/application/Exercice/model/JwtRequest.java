package com.application.Exercice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// JwtRequest allows to get the username and the password from the request body
public class  JwtRequest implements Serializable {
    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;

}
