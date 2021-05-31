package com.example.passwordvalidation.constants;

public interface Constants {
    String digitReg="(?=.*\\d)";
    String alphaReg="(?=.*[a-z])";
    String pwdReg="((?=.*\\d)(?=.*[a-z]).{5,12})";

    String pwdValid="SUCCESS";
    String pwdStrInvalid="Please note that the password cannot not contain any sequence of characters immediately followed by the same sequence";
    String pwdInvalid="lowercase letters and numerical digits only, at least one of each! need 5 to 12 characters!";
    String error="ERROR";
}
