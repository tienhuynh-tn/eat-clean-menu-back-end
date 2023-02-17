package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.security.TokenProvider;
import com.happy3friends.eatcleanmenubackend.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/users")
@Api(value = "User API", description = "Provides User API's", tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @ApiOperation(value = "Update User Subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Update successfully, No-content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/subscriptionType")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createUserSubscription(
            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "User Subscription Info.", example = "1 tháng")
            @RequestParam(name = "subscriptionType") String subscriptionType) {

        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userService.createUserSubscriptionByUserId(subscriptionType, userId);
    }

    /*@GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UsersEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new NotFoundException("User", "id", userPrincipal.getId()));
    }*/
}
