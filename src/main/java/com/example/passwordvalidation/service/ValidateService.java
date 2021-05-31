package com.example.passwordvalidation.service;

import com.example.passwordvalidation.constants.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    /**
     * a method to check if the password is valid
     * @param pwd
     * @return 409: the password is invalid; ok: the password is valid
     */
    public ResponseEntity<String> validatePwd(String pwd){
        Boolean checkedString;
        if(pwd.matches(Constants.pwdReg)){
            // pop the first character and check again if the pwd is still valid
            for(int i =0; i<pwd.length()-1;i++){
                checkedString= checkString(pwd.substring(i));
                if(!checkedString){
                    return ResponseEntity.status(409).body(Constants.pwdStrInvalid);
                }
            }
            return ResponseEntity.ok().body(Constants.pwdValid);
        }
        return ResponseEntity.status(409).body(Constants.pwdInvalid);
    }

    /**
     * a helper to check if the substring is still matched the conditions by cutting the string to substring
     * and compare if the substrings are same words or not
     * @param pwd a substring of the password
     * @return true : still match, false: doesn't match
     */
    private Boolean checkString(String pwd){
        for(int i=0; i<pwd.length();i++){
            for (int j = i+1; j<=pwd.substring(i).length()/2;j++){
                if (pwd.substring(i, j).equals(pwd.substring(j, j+ pwd.substring(i, j).length()))){
                    return false;
                }
            }
        }
        return true;
    }
}
