package com.example.passwordvalidation.constants;

public interface Constants {

    String pwdReg="(^(?=.*\\d)(?=.*[a-z]).{5,12})";
    String pwdRegex="[\\da-z]{6,12}";

    String checkPwd="checkPwd";
    String pwdValid="SUCCESS";
    String pwdInvalid="ERROR";
    String ErrorException="ErrorException";

}
