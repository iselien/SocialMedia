package com.yovelas.controller;

import com.yovelas.dao.UserDao;
import com.yovelas.entity.JsonResult;
import com.yovelas.entity.User;
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
    UserDao userDao;

    private String vCode;


    @RequestMapping("/verification")
    void verification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get verifyCode
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        vCode = text;

        // save verifyCode
        HttpSession session = req.getSession();
        session.setAttribute("vCode", text);
        VerifyCode.output(image, resp.getOutputStream());
    }

    @RequestMapping("/username")
    @ResponseBody
    JsonResult getUserName(@RequestBody Map jsonMap) {
        String username = (String) jsonMap.get("username");
        JsonResult jsonResult = new JsonResult();
        User user = userDao.getUserByName(username);
        if (user != null) {
            jsonResult.setStatus(200);
            jsonResult.setMassage("username already exists");
        }
        return jsonResult;
    }

    @CrossOrigin(allowCredentials="true")
    @PostMapping(path = "/login", consumes = "application/json")
    JsonResult login(HttpServletRequest req, @RequestBody User user, HttpServletResponse resp) {
        JsonResult jsonResult = new JsonResult();
        boolean flag = true;
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getValue().equals(user.getUsername())) {
                    flag = false;
                }
            }
        }
        User userByName = userDao.getUserByName(user.getUsername());
        if (flag) {
            // not cookie
            User userByUP = userDao.getUserByUP(user);
            if (userByUP != null) {
                Cookie login = new Cookie("user", userByUP.getUsername());
                login.setMaxAge(60 * 20);
                login.setPath("/");
                resp.addCookie(login);
            } else {
                return jsonResult;
            }
        }
        req.getSession().setAttribute("user", userByName);
        jsonResult.setStatus(200);
        jsonResult.setMassage("login successful");
        System.out.println(flag);
        return jsonResult;
    }

    String register() {

        return null;
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

    String logout() {
        return null;
    }

}
