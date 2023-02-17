//package com.happy3friends.eatcleanmenubackend.security;
//
//import com.happy3friends.eatcleanmenubackend.config.AppProperties;
//import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
//import com.happy3friends.eatcleanmenubackend.utils.JWTDecodeUtil;
//import io.jsonwebtoken.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimeZone;
//
///**
// * Utility class for generating and verifying JWT
// * The following utility class will be used for generating a JWT after a user logs in successfully,
// * and validating the JWT sent in the Authorization header of the requests
// * The utility class reads the JWT secret and expiration time from properties.
// */
//@Service
//public class TokenProvider {
//
//    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
//
//    private AppProperties appProperties;
//
//    public TokenProvider(AppProperties appProperties) {
//        this.appProperties = appProperties;
//    }
//
//    public String createToken(Authentication authentication) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")).getTime();
//        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
//
//        return Jwts.builder()
//                .setSubject(String.valueOf(userPrincipal.getId()))
//                .setIssuedAt(DateTimeUtil.convertZoneDateTimeToDate(DateTimeUtil.getZoneDateTimeNow()))
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
//                .claim("Id", userPrincipal.getId())
//                .claim("Gmail", userPrincipal.getEmail())
//                .claim("Fullname", userPrincipal.getAttribute("name"))
//                .claim("Avatar", userPrincipal.getAttribute("picture"))
//                .compact();
//    }
//
//    public String getTokenFromBearerToken(String bearerToken) {
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//
//    public int getUserIdFromToken(String token) {
//        Jws<Claims> jwt = JWTDecodeUtil.parseJwt(token);
//
//        return Integer.parseInt(jwt.getBody().getSubject());
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            logger.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            logger.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            logger.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            logger.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            logger.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//
//}