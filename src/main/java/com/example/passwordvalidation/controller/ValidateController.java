package com.example.passwordvalidation.controller;

import com.example.passwordvalidation.entity.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ValidateController {

    /**
     * handle request to /signin endpoint
     * @param user contains the userName and password
     * @param result contains the result after validated
     * @return json string to presenting whether or not the password is valid,
     *  and what the results of the checks were
     */

    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String singIn(@Valid @RequestBody UserInfo user, BindingResult result) {
        JSONObject obj = new JSONObject();
        try{
            if(result.hasErrors()){
                List<String> err= new ArrayList<>();
                List<ObjectError> ls=result.getAllErrors();
                for (int i = 0; i < ls.size(); i++) {
                    err.add(ls.get(i).getDefaultMessage());
                }
                obj.put("message",err);
            }else{
                obj.put("message","valid");
            }
        }catch (JSONException e){
            obj.put("message",e.getMessage());
        }
        return obj.toString();
    }

}
