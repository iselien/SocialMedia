package io.github.yovelas;

import io.github.yovelas.entity.JsonResult;
import io.github.yovelas.service.PhotosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * Hello world!
 */

@RestController
@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(PhotosService.class);

    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/")
    ModelAndView home() {
        return new ModelAndView("index.html");
    }

    @CrossOrigin(allowCredentials="true")
    @GetMapping(path = "/", consumes = "application/json")
    JsonResult ping() {
        return new JsonResult().setStatus(0).setMessage("Received successfully");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
