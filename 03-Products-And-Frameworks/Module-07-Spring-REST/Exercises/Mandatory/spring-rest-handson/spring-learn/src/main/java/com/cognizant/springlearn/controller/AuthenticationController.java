package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start authenticate");
        LOGGER.debug("authHeader={}", authHeader);

        String user = getUser(authHeader);
        LOGGER.debug("user={}", user);

        String token = generateJwt(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("End authenticate");
        return map;
    }

    private String getUser(String authHeader) {
        LOGGER.info("Start getUser");

        String encodedCredentials = authHeader.substring("Basic ".length());
        LOGGER.debug("encodedCredentials={}", encodedCredentials);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes);
        LOGGER.debug("decodedString={}", decodedString);

        String user = decodedString.substring(0, decodedString.indexOf(":"));
        LOGGER.debug("user={}", user);

        LOGGER.info("End getUser");
        return user;
    }

    private String generateJwt(String user) {
        LOGGER.info("Start generateJwt");

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();
        LOGGER.debug("token={}", token);

        LOGGER.info("End generateJwt");
        return token;
    }
}
