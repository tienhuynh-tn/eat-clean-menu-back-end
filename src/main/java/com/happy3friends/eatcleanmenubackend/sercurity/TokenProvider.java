package com.happy3friends.eatcleanmenubackend.sercurity;

import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
import com.happy3friends.eatcleanmenubackend.utils.JwtUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    public String generateToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Date now = DateTimeUtil.getDateNow();
        Date expiryDate = new Date(now.getTime() + JwtUtil.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(String.valueOf(customUserDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JwtUtil.JWT_SECRET)
                .claim("gmail", customUserDetails.getGmail())
                .claim("fullName", customUserDetails.getFullName())
                .claim("avatar", customUserDetails.getAvatar())
                .claim("authorities", customUserDetails.getAuthorities())
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JwtUtil.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }
}
