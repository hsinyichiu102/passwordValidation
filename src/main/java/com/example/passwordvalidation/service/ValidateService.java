package com.example.passwordvalidation.service;

import com.example.passwordvalidation.constants.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    /**
     * a method to check if the password is valid
     * the conditions are:
     * * Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
     * * Must be between 5 and 12 characters in length.
     * * Must not contain any sequence of characters immediately followed by the same sequence
     * @param pwd the password from client
     * @return 409: the password is invalid; ok: the password is valid
     */
    public ResponseEntity<String> validatePwd(String pwd){
        Boolean checkedString;
        // check firstly if password is contain at least one lowercase letter,numerical digits and the length is between 5 to 12
        if(pwd.matches(Constants.pwdReg)){
            // pop the first character and check again if the pwd is still valid
            for(int i =0; i<pwd.length()-1;i++){
                checkedString= checkString(pwd.substring(i));
                if(!checkedString){
                    return ResponseEntity.status(418).body(Constants.pwdStrInvalid);
                }
            }
            return ResponseEntity.ok().body(Constants.pwdValid);
        }
        return ResponseEntity.status(418).body(Constants.pwdInvalid);
    }

    /**
     * a helper to cut the pwd to two or more section and compare these sections with the first one
     * check if the words in two sections are equal then return false
     * if not equal means the pwd is still matched the condition
     * "Must not contain any sequence of characters immediately followed by the same sequence"
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
