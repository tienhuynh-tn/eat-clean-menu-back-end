package com.happy3friends.eatcleanmenubackend.utils;

import com.happy3friends.eatcleanmenubackend.sercurity.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;

public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static final String JWT_SECRET = "bONJQcpVJ/2DSkWbkxm0xFEA9w6cALzJRQaIIfMS/hbYeuKNob2eclbqCUVWHXudz+FsTCCUbFjXvdTgF/KhSQ==";
    public static final long JWT_EXPIRATION = 604800000L;
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";

    public static String getJwtFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getJwtFromRequest(request);
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);

        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            String jwt = bearerToken.substring(7);
            if (StringUtils.hasText(jwt) && TokenProvider.validateToken(jwt)) {
                return jwt;
            }
        }

        return null;
    }

    public static Jws<Claims> parseJwt(String token) {

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET),
                SignatureAlgorithm.HS512.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);

        return jwt;
    }

    public static Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }

    public static int getAccountIdFromToken(String token) {
        Jws<Claims> jwt = JwtUtil.parseJwt(token);

        return Integer.parseInt(jwt.getBody().getSubject());
    }
}
