package com.znt.demo.login.controller;

import com.znt.demo.login.pojo.User;
import com.znt.demo.login.utils.LoginCacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target, HttpSession session,@CookieValue(required = false,value="TOKEN") Cookie cookie){
        if(target.isEmpty()){
            target="http://www.codeshop.com:9003/view/index";
        }
        if(cookie!=null) {
            String value = cookie.getValue();
            User user = LoginCacheUtil.loginUser.get(value);
            if (user != null) {
                return "redirect:" + target;
            }
        }
        //重定向
        session.setAttribute("target",target);
        return "login";
    }


}
