package com.yovelas;

import com.yovelas.service.PhotosService;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Scanner;


/**
 * Hello world!
 */

@Controller
@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(PhotosService.class);

    @RequestMapping("/")
    String home() {
        return "index.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
