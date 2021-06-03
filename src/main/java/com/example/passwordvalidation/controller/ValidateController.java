package com.example.passwordvalidation.controller;

import com.example.passwordvalidation.constants.Constants;
import com.example.passwordvalidation.entity.UserInfo;
import com.example.passwordvalidation.service.ValidateService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidateController {

    @Autowired
    ValidateService validate;

    /**
     * the controller to get the user's information and check if the password is valid
     * @param request contains user's information
     * @return a json string
     */
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String singIn(@RequestBody UserInfo request) {
        UserInfo usr = new UserInfo();
        JSONObject obj = new JSONObject();
        try {
            usr.setPassword(request.getPassword());
            if(validate.validatePwd(usr.getPassword())){
                obj.put(Constants.checkPwd,Constants.pwdValid);
            }
            else{
                obj.put(Constants.checkPwd,Constants.pwdInvalid);
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return obj.toString();
    }
}
