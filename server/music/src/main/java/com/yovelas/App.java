package com.yovelas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Hello world!
 */

@Controller
@SpringBootApplication
public class App {

    @RequestMapping("/")
    String home() {
        return "index.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
