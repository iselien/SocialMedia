package com.yovelas.controller;

import com.yovelas.dao.UserDao;
import com.yovelas.entity.JsonResult;
import com.yovelas.entity.User;
import com.yovelas.entity.UserLog;
import com.yovelas.service.UserLogService;
import com.yovelas.service.UserService;
import com.yovelas.util.VerifyCode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
//功能：
//- 验证码
//- 用户名验证
//- 登录
//- 注册
//- 获取登录状态
//- 忘记密码
//- 提交问题答案
//- 重置密码
//- 获取用户信息
//- 更新用户信息
//- 退出登录

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserLogService userLogService;

    @RequestMapping("/verification")
    void verification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get verifyCode
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        // save verifyCode
        HttpSession session = req.getSession();
        session.setAttribute("vcode", text+","+System.currentTimeMillis());
        VerifyCode.output(image, resp.getOutputStream());
    }

    @RequestMapping("/username")
    @ResponseBody
    JsonResult getUserName(@RequestBody Map jsonMap) {
        String username = (String) jsonMap.get("username");
        JsonResult jsonResult = new JsonResult();
        User user = userService.getUserByName(username);
        if (user != null) {
            jsonResult.setStatus(200);
            jsonResult.setMassage("username already exists");
        }
        return jsonResult;
    }

    @CrossOrigin(allowCredentials="true")
    @PostMapping(path = "/login", consumes = "application/json")
    JsonResult login(HttpServletRequest req, @RequestBody Map map, HttpServletResponse resp) {
        JsonResult jsonResult = new JsonResult();
        boolean flag = true;
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getValue().equals(map.get("username"))) {
                    flag = false;
                }
            }
        }
        HttpSession session = req.getSession();
        String vcode = (String)map.get("vcode");
        String se = session.getAttribute("vcode").toString();
        String seVcode = se.split(",")[0];
        if(System.currentTimeMillis()-Long.valueOf(se.split(",")[1]) > 60000 && flag){
            return jsonResult.setMassage("The verification code has expired").setStatus(1);
        }
        if((vcode == null || seVcode == null ||!vcode.equals(seVcode) ) && flag){
            return jsonResult.setMassage("Verification code mistake").setStatus(2);
        }else if(map.get("username")==null){
            return jsonResult.setMassage("The username is empty").setStatus(3);
        }else if(map.get("password") == null && flag){
            return jsonResult.setMassage("The password is empty").setStatus(4);
        }
        User userByName = userService.getUserByName((String)map.get("username"));
        if(userByName == null){
           return jsonResult.setMassage("The username is does not exist").setStatus(5);
        }
        UserLog userLog = new UserLog(req, userByName.getId(),"login", map);
        System.out.println(userLog);
        userLogService.insertUserLog(userLog);

        if (flag) {
            // no cookie
            User userByUP = userService.getUserByUP(new User((String)map.get("username"),(String)map.get("password")));

            if (userByUP == null) {
                return jsonResult.setMassage("Password mistake").setStatus(5);
            } else {
                Cookie login = new Cookie("user", userByUP.getUsername());
                login.setMaxAge(60 * 20);
                login.setPath("/");
                resp.addCookie(login);
            }

        }
        req.getSession().setAttribute("user", userByName);
        System.out.println(flag);
        return jsonResult.setMassage("login successful").setStatus(0).setData(userByName);
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/register", consumes = "application/json")
    Object register(HttpServletRequest req, @RequestBody User user, HttpServletResponse resp) {
        JsonResult jsonResult = new JsonResult();
        System.out.println(user);
        if(userService.insertUser(user) == 1){
            return jsonResult.setMassage("success").setStatus(200);
        }
        return jsonResult;
    }

    String forget() {
        return null;
    }

    String reset() {
        return null;
    }

    @CrossOrigin(allowCredentials="true")
    @PostMapping(value = "/userinfo", consumes = "application/json")
    JsonResult getUser(HttpServletRequest req) {
        JsonResult jsonResult = new JsonResult();
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            user.setPassword(null);
            jsonResult.setMassage("user information wa successfully obtained");
            jsonResult.setStatus(200);
            jsonResult.setData(user);
        }
        return jsonResult;
    }

    String updateUser() {
        return null;
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/logout", consumes = "application/json")
    Object logout(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getCookies() == null){
            return new JsonResult().setMassage("Not logged in or cookie not received").setStatus(1);
        }
        for (Cookie c : req.getCookies()) {
            if(("user").equals(c.getName())) {
                c.setMaxAge(0);
            }
        }
        req.getSession().removeAttribute("user");
        return new JsonResult().setMassage("Logout succuess").setStatus(0);
    }

}
