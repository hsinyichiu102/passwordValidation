package com.example.passwordvalidation.controller;

import com.example.passwordvalidation.entity.UserInfo;
import com.example.passwordvalidation.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidateController {

    @Autowired
    ValidateService validate;

    /**
     * signin method to get the password from parameter and return if it is valid
     * @param request
     * @return SUCCESS: the password is valid; other warning: the password is invalid
     */
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public ResponseEntity<String> singIn(@RequestBody UserInfo request){
        UserInfo usr = new UserInfo();
        usr.setPassword(request.getPassword());
        return validate.validatePwd(usr.getPassword());
    }
}
