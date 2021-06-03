package com.example.passwordvalidation.service;

import com.example.passwordvalidation.constants.Constants;
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
    public Boolean validatePwd(String pwd){
        Boolean checkedString;

        // check if the String contains at least one numerical digit and lowercase letter && only numerical digits and lowercase letter
        if(pwd.matches(Constants.pwdReg) && pwd.matches(Constants.pwdRegex)){
            // pop the first character and check again if the pwd is still valid
            for(int i =0; i<pwd.length()-1;i++){
                checkedString= checkString(pwd.substring(i));
                if(checkedString){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * a helper to cut the string to at least two substring
     * and compare if the substrings are same
     * if return true means the substrings are same and it is not matched the conditions
     * @param pwd a substring of the password
     * @return true : the substring are equal, false: the substring are not same
     */
    private Boolean checkString(String pwd){
        for(int i=0; i<pwd.length();i++){
            for (int j = i+1; j<=pwd.substring(i).length()/2;j++){
                if (pwd.substring(i, j).equals(pwd.substring(j, j+ pwd.substring(i, j).length()))){
                    return true;
                }
            }
        }
        return false;
    }
}
