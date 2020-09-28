package io.github.yovelas.controller;

import io.github.yovelas.entity.JsonResult;
import io.github.yovelas.entity.User;
import io.github.yovelas.entity.UserLog;
import io.github.yovelas.service.UserLogService;
import io.github.yovelas.service.UserService;
import io.github.yovelas.util.VerifyCode;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
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
    // 设置个人信息
    // 1. 确定当前用户登录状态
    // 2. 提当修改的项

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserLogService userLogService;

    // 基于Session判断连接状态
    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/")
    JsonResult user(HttpServletRequest req){
        JsonResult connected = isConnected(req);
        if(connected.getStatus() != 0) return connected;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        return new JsonResult().setStatus(0).setMessage("ok").setData(userService.getUserByName(user.getUsername()));
    }

    // 基于Session判断连接状态
    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/isconnected")
    JsonResult isConnectedI(HttpServletRequest req){
        return isConnected(req);
    }

    // 基于Cookie判断登录状态
    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/islogined")
    JsonResult isLoginedI(HttpServletRequest req){
        return isLogined(req);
    }

    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/isexist")
    JsonResult isExistI(HttpServletRequest req, @Param("username") String username){
        return isExist(username);
    }

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

    @CrossOrigin(allowCredentials="true")
    @PostMapping(path = "/login/ia", consumes = "application/json")
    JsonResult interactiveLogin(HttpServletRequest req, @RequestBody Map map, HttpServletResponse resp) {
        JsonResult jsonResult = new JsonResult();

        // 判断参数是否为空
        System.out.println("map:"+map);
        if(map.isEmpty()){
            return jsonResult.setMessage("Parameter is null").setStatus(1);
        }

//        // 判断是否已经登录
//        HttpSession session = req.getSession();
//        if(session.getAttribute("user") != null){
//            return jsonResult.setMessage("You are logged in, please log out first").setStatus(2);
//        }

        // 帐号与密码匹配
        User userByUP = userService.getUserByUP(new User((String)map.get("username"),(String)map.get("password")));
        if(userByUP == null){
            return jsonResult.setMessage("Wrong account number or password").setStatus(3);
        }

        // 存储Session
        req.getSession().setAttribute("user", userByUP);

        // 是否加入cookie
        if(map.get("status").equals("true")){
            Cookie login = new Cookie("user", userByUP.getUsername());
            login.setMaxAge(60 * 20);
            login.setPath("/");
            resp.addCookie(login);
        }

        return jsonResult.setMessage("login successful").setStatus(0).setData(userByUP);
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
//        String vcode = (String)map.get("vcode");
//        String se = session.getAttribute("vcode").toString();
//        String seVcode = se.split(",")[0];
//        if(System.currentTimeMillis()-Long.valueOf(se.split(",")[1]) > 60000 && flag){
//            return jsonResult.setMessage("The verification code has expired").setStatus(1);
//        }
//        if((vcode == null || seVcode == null ||!vcode.equals(seVcode) ) && flag){
//            return jsonResult.setMessage("Verification code mistake").setStatus(2);
//        }else
        if(map.get("username")==null) {
            return jsonResult.setMessage("The username is empty").setStatus(3);
        }else if(map.get("password") == null && flag){
            return jsonResult.setMessage("The password is empty").setStatus(4);
        }
        User userByName = userService.getUserByName((String)map.get("username"));
        if(userByName == null){
           return jsonResult.setMessage("The username is does not exist").setStatus(5);
        }
        UserLog userLog = new UserLog(req, userByName.getId(),"login", map);
        System.out.println(userLog);
        userLogService.insertUserLog(userLog);

        if (flag) {
            // no cookie
            User userByUP = userService.getUserByUP(new User((String)map.get("username"),(String)map.get("password")));

            if (userByUP == null) {
                return jsonResult.setMessage("Password mistake").setStatus(5);
            } else {
                Cookie login = new Cookie("user", userByUP.getUsername());
                login.setMaxAge(60 * 20);
                login.setPath("/");
                resp.addCookie(login);
            }

        }
        req.getSession().setAttribute("user", userByName);
        System.out.println(flag);
        return jsonResult.setMessage("login successful").setStatus(0).setData(userByName);
    }

    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/login", consumes = "application/json")
    JsonResult autoLogin(HttpServletRequest req){
        JsonResult jsonResult = new JsonResult();
        String username = null;
        User user = (User)req.getSession().getAttribute("user");
        if(user != null){
            username = user.getUsername();
        }else{
            Cookie[] cookies = req.getCookies();
            for(Cookie c : cookies){
                if(c.getName().equals("user")){
                    username = c.getValue();
                }
            }
        }
        if(isConnected(req).getStatus() == 0 || isLogined(req).getStatus() == 0){
           return jsonResult.setStatus(0).setMessage("successfully").setData(userService.getUserByName(username));
        }
        return jsonResult.setStatus(1).setMessage("fail");
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/register", consumes = "application/json")
    Object register(HttpServletRequest req, @RequestBody User user, HttpServletResponse resp) {
        JsonResult jsonResult = new JsonResult();
        System.out.println(user);

        JsonResult exist = isExist(user.getUsername());
        if(exist.getStatus()==0){
            return exist;
        }

        if(userService.insertUser(user) == 1){
            return jsonResult.setMessage("success").setStatus(200);
        }
        return jsonResult;
    }

    @CrossOrigin(allowCredentials="true")
    @PostMapping(value = "/userinfo", consumes = "application/json")
    JsonResult getUser(HttpServletRequest req) {
        JsonResult jsonResult = new JsonResult();
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            user.setPassword(null);
            jsonResult.setMessage("user information was successfully obtained");
            jsonResult.setStatus(200);
            jsonResult.setData(user);
        }
        return jsonResult;
    }

    @CrossOrigin(allowCredentials="true")
    @PostMapping(value = "/userinfobyid", consumes = "application/json")
    JsonResult getUserById(@RequestBody Map map) {
        return new JsonResult().setStatus(0).setMessage("successfully").setData(userService.getUserById((int)map.get("id")));
    }

    // 退出登录
    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/logout", consumes = "application/json")
    JsonResult logout(HttpServletRequest req, HttpServletResponse resp) {

        JsonResult isCon =  isConnected(req);
        if(isCon.getStatus() != 0){
            return isCon;
        }
        req.getSession().removeAttribute("user");
        return new JsonResult().setMessage("Logout succuess").setStatus(0);
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/update", consumes = "application/json")
    Object update(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }


    JsonResult isConnected(HttpServletRequest req){
        System.out.println("isConnected:"+req.getRequestURL());
        HttpSession session = req.getSession();
        JsonResult jsonResult = new JsonResult();
        if (session.getAttribute("user")!=null) return jsonResult.setStatus(0).setMessage("Is connected");
        else return jsonResult.setStatus(1).setMessage("Is not connected");
    }

    JsonResult isLogined(HttpServletRequest req){
        System.out.println("isLogined:"+req.getRequestURL());
        Cookie[] cookies = req.getCookies();
        JsonResult jsonResult = new JsonResult();
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("user")) return jsonResult.setStatus(0).setMessage("Is logined");
            }
        }
        return jsonResult.setStatus(1).setMessage("Is not logined");
    }

    JsonResult isExist(String username){
        User userByName = userService.getUserByName(username);
        JsonResult jsonResult = new JsonResult();
        if (userByName!=null) return jsonResult.setStatus(0).setMessage("Is already exist");
        return jsonResult.setStatus(1).setMessage("Is not exist");
    }

    String forget() {
        return null;
    }
    String reset() {
        return null;
    }
    String updateUser() {
        return null;
    }
}
