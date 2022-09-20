package com.application.Exercice.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Exercice.jwt.JwtUserDetailsService;
import com.application.Exercice.jwt.TokenManager;
import com.application.Exercice.model.JwtRequest;
import com.application.Exercice.model.JwtResponse;



@RestController
public class AuthController {
   @Autowired
   private JwtUserDetailsService userDetailsService;
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private TokenManager tokenManager;
   @Autowired
   private HttpServletResponse response;

   @PostMapping("/login")
   public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request,
         HttpServletRequest httpRequest) throws Exception {
      try {
         authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getUsername(),
                     request.getPassword()));
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }

      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      final String jwtToken = tokenManager.generateJwtToken(userDetails);

      Cookie cookie = new Cookie("token", jwtToken);
      response.addCookie(cookie);

      HttpHeaders responseHeaders = new HttpHeaders();
      return ResponseEntity.ok().headers(responseHeaders).body(new JwtResponse(jwtToken));
   }
}
