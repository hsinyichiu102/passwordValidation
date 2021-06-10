package com.example.passwordvalidation.validator;

import com.example.passwordvalidation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public boolean isValid(String pwd,ConstraintValidatorContext constraintValidatorContext) {
        Boolean checkedString;
        // pop the first character and check again if the pwd is still valid
        for(int i =0; i<pwd.length()-1;i++){
            checkedString= checkString(pwd.substring(i));
            if(checkedString){
                return false;
            }
        }
        return true;
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
