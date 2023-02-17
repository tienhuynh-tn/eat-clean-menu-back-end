//package com.happy3friends.eatcleanmenubackend.controller;
//
//import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
//import com.happy3friends.eatcleanmenubackend.dto.TokenDTO;
//import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
//import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
//import com.happy3friends.eatcleanmenubackend.security.TokenProvider;
//import com.happy3friends.eatcleanmenubackend.utils.JWTDecodeUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//@ApiIgnore
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ResponseDTO<List<Object>>> login(HttpServletRequest request) {
//        String token = request.getParameter("token");
//
//        List<Object> datas = new ArrayList<>();
//        datas.add(new TokenDTO(token));
//        datas.add(JWTDecodeUtil.parseJwt(token));
//
//        return ResponseEntityBuilder.generateResponse(
//                "Auth login successfully!",
//                HttpStatus.OK,
//                datas
//        );
//    }
//}