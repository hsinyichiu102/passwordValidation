package com.example.passwordvalidation.entity;

import com.example.passwordvalidation.annotation.Password;



import lombok.Data;
import lombok.Getter;

import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class UserInfo implements Serializable{

    public UserInfo(String usrName, String password) {
        this.usrName = usrName;
        this.password = password;
    }
    public UserInfo(){}

    private String usrName;



    @Password
    private String password;


    @Override
    public String toString() {
        return "UserInfo{" +
                ", password='" + password + '\'' +
                ", usrName='" + usrName + '\'' +
                '}';
    }


}
