//package com.happy3friends.eatcleanmenubackend.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//import java.util.Base64;
//
//public class JWTDecodeUtil {
//
//    public static String decodeJWTToken(String token) {
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//
//        String[] chunks = token.split("\\.");
//
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//
//        return header + " " + payload;
//    }
//
//    /*
//    public static String decodeJWTToken(String token, String secretKey) throws Exception {
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//
//        String[] chunks = token.split("\\.");
//
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//
//        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
//        String signature = chunks[2];
//
//        SignatureAlgorithm sa = SignatureAlgorithm.HS512;
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
//
//        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);
//
//        if (!validator.isValid(tokenWithoutSignature, signature)) {
//            throw new Exception("Could not verify JWT token integrity!");
//        }
//
//        return header + " " + payload;
//    }
//    */
//
//    public static Jws<Claims> parseJwt(String token) {
//
//        String secret = "AD54AEBF234949FA7CC10A5F1D12A8AF8D22F4EA47DDC400C4DAE19C14932523pNMY6mJhUVe7Ou5DTp77OMu585lb0awp5vAncf2bZIiQpntC4cVIRp8cLxjQUq7";
//        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
//                SignatureAlgorithm.HS512.getJcaName());
//
//        Jws<Claims> jwt = Jwts.parserBuilder()
//                .setSigningKey(hmacKey)
//                .build()
//                .parseClaimsJws(token);
//
//        return jwt;
//    }
//}
