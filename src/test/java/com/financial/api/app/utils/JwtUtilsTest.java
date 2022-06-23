package com.financial.api.app.utils;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {"jwt_secret=123456","jwt_expiration=120"})
public class JwtUtilsTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void testIfCreateJwtToken() {
        //GIVEN
           String userId = UUID.randomUUID().toString();
        //WHEN
            String token = jwtUtils.generateJwtToken(userId);
            String tokenUserId = jwtUtils.getUserIdFromToken(token);
        //THEN
            Assertions.assertInstanceOf(String.class, token);
            Assertions.assertEquals(userId, tokenUserId);
    }

    @Test
    public void testIfGetUserIdFromToken() {
        //GIVEN
        String userId = UUID.randomUUID().toString();
        //WHEN
        String token = jwtUtils.generateJwtToken(userId);
        String tokenUserId = jwtUtils.getUserIdFromToken(token);
        //THEN
        Assertions.assertEquals(userId, tokenUserId);
    }

    @Test
    public void testIfGetAllClaims() {
        //GIVEN
        String userId = UUID.randomUUID().toString();
        Integer totalClaims = 4;
        //WHEN
        String token = jwtUtils.generateJwtToken(userId);
        Jws<Claims> claimsJws = jwtUtils.getAllClaims(token);
        System.out.println(claimsJws.getBody());
        //THEN
        Assertions.assertInstanceOf(Jws.class, claimsJws);
        Assertions.assertEquals(totalClaims, claimsJws.getBody().size());
    }
}
