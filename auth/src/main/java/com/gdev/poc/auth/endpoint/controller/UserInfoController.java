package com.gdev.poc.auth.endpoint.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdev.poc.core.model.ApplicationUser;

import java.security.Principal;


@RestController
@RequestMapping("user")
@Api(value = "Endpoint User")
public class UserInfoController {

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recupera as informações do usuário disponíveis no token", response = ApplicationUser.class)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(applicationUser, HttpStatus.OK);
    }
}
