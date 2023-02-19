package com.happy3friends.eatcleanmenubackend.controller;

//import com.happy3friends.eatcleanmenubackend.security.TokenProvider;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.UserDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(value = "User API", description = "Provides User API's", tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private TokenProvider tokenProvider;

    @ApiOperation(value = "User Login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, Integer>>> login(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "User Info.")
            @RequestBody UserDTO user) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        int userId = userService.login(user);

        Map<String, Integer> response = new HashMap<>();
        response.put("userId", userId);

        return ResponseEntityBuilder.generateResponse(
                "Login successfully!",
                HttpStatus.OK,
                response
        );
    }

    @ApiOperation(value = "User Info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{gmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserDTO>> get(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "User Id.")
            @PathVariable String gmail) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

//        //int userId = userService.login(user);
//
//        Map<String, Integer> response = new HashMap<>();
//        response.put("userId", userId);

        UserDTO response = userService.get(gmail);

        return ResponseEntityBuilder.generateResponse(
                "Login successfully!",
                HttpStatus.OK,
                response
        );
    }

    @ApiIgnore
    @ApiOperation(value = "Update User Subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Update successfully, No-content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/{userId}/subscriptionType")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createUserSubscription(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @PathVariable("userId") int userId,
            @ApiParam(value = "User Subscription Info.", example = "1 tháng")
            @RequestParam(name = "subscriptionType") String subscriptionType) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userService.createUserSubscriptionByUserId(subscriptionType, userId);
    }

    /*@GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UsersEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new NotFoundException("User", "id", userPrincipal.getId()));
    }*/
}
