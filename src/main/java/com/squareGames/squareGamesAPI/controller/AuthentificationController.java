package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.DTO.EntityDto;

import com.squareGames.squareGamesAPI.services.JwtTokenAuthenticationFilter;
import com.squareGames.squareGamesAPI.services.JwtTokenUtil;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/api/public/login")
public class AuthentificationController {
    @Autowired
    private JwtTokenAuthenticationFilter tokenAuthentication;
    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private EntityDto dto = new EntityDto();



    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody Map<String, String> response) {
        try {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(response.get("username"), response.get("password"));

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateJwtToken();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
            return ResponseEntity.ok().headers(headers).build();

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le compte est désactivé");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le compte est verrouillé");
        } catch (AccountExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le compte a expiré");
        } catch (CredentialsExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Les informations d'identification ont expiré");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentification échouée");
        }
    }


}
