package com.znt.demo.login.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //全参构造函数
@NoArgsConstructor //无参构造函数

public class User {

    private Integer id;
    private String  username;
    private String password;

}
