package com.znt.demo.login.controller;

import com.znt.demo.login.pojo.User;
import com.znt.demo.login.utils.LoginCacheUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static  Set<User> dbUsers;

    // 这个是什么东西啊，为什么HashSet要放这里面呢？？
    static {
        dbUsers = new HashSet<User>();
        dbUsers.add(new User(0,"Mary","12345"));
        dbUsers.add(new User(1,"Lisa","12345"));
        dbUsers.add(new User(2,"Lala","12345"));

    }

    @PostMapping
    public String doLogin(User user, HttpSession session,HttpServletResponse response){
        System.out.println(session);
        System.out.println(user.getUsername()+user.getPassword());
        String target =(String) session.getAttribute("target");
        System.out.println(target);

        Optional<User> first = dbUsers.stream().filter(dbUsers->dbUsers.getUsername().equals(user.getUsername())&&
                      dbUsers.getPassword().equals(user.getPassword())).findFirst();
        System.out.println(first);
        if(first.isPresent()){
            //保存用户登录信息
            String token = UUID.randomUUID().toString();
            //设置携带用户信息的cookie
            Cookie cookie = new Cookie("TOKEN",token);
            cookie.setDomain("codeshop.com");
            response.addCookie(cookie);
            LoginCacheUtil.loginUser.put(token,first.get());
        }else{
            session.setAttribute("msg","用户名或密码错误");
            return "login";
        }
        // 重定向到target地址
        return "redirect:"+target;
    }

    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token){
        if(!StringUtils.isEmpty(token)){
            User user = LoginCacheUtil.loginUser.get(token);
            return  ResponseEntity.ok(user);
        }else{
            return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("logout")
    @ResponseBody
    public String doLogout(String token,HttpServletResponse response){
        System.out.println(token);
        if(!StringUtils.isEmpty(token)){
            LoginCacheUtil.loginUser.remove(token);
            System.out.println( LoginCacheUtil.loginUser.get(token));
        }
        Cookie cookie = new Cookie("TOKEN",token);
        cookie.setDomain("codeshop.com");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

//        session.invalidate();
//        Cookie[] cookies = request.getCookies();
//        for(int i=0;i<cookies.length;i++){
//            System.out.println("cookies "+ cookies[i].getName()+" "+cookies[i].getValue()+" "+cookies[i].getMaxAge());
//            cookies[i].setMaxAge(0);
//            System.out.println(cookies[i].getName()+" "+cookies[i].getValue()+" "+cookies[i].getMaxAge());
//            response.addCookie(cookies[i]);
//        }
        return "ok";
    }

}
