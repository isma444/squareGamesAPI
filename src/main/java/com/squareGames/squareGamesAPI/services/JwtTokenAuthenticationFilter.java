package com.squareGames.squareGamesAPI.services;

import com.squareGames.squareGamesAPI.controller.UserController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.DoubleToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {


    @Value("${jwt.secret}")
    private String key;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    private final String BEARER_TOKEN_REGEX = "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+$";
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    public boolean isValidBearerToken(String token) {
        if(token != null) {
            Pattern pattern = Pattern.compile(BEARER_TOKEN_REGEX);
            Matcher matcher = pattern.matcher(token);
            return matcher.matches();
        }
        return false;
    }
    public JwtTokenAuthenticationFilter() {

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


           String authorization = request.getHeader("Authorization");

           if (isValidBearerToken(authorization)) {

                   String token = authorization.substring(7);

                   JwtParserBuilder parseBuilder = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes()));
                   JwtParser parser = parseBuilder.build();
                   try {
                       Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                       if(claimsJws == null){
                           throw new IOException();
                       }
                       Claims claim = claimsJws.getBody();
                       if(claim == null){
                           throw new RuntimeException();
                       }
                       String username = claim.getSubject();
                       System.out.println(username);
                       UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                       if(userDetails == null){
                           throw new ServletException("utilisateur non trouvé");
                       }
                       System.out.println(userDetails.getUsername());
                       UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                       System.out.println(auth.isAuthenticated());
                       System.out.println(userDetails.getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(auth);
                   }catch(SignatureException e){
                       logger.error("le token ne correspond a rien");
                       return;
                   }catch(ExpiredJwtException e){
                       logger.error("le token a expiré");
                       return;
                   }
                   
           }

        filterChain.doFilter(request, response);

    }


}
