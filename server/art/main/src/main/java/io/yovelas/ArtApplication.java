package io.yovelas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class ArtApplication {


	@CrossOrigin(allowCredentials="true")
	@GetMapping(path = "/")
	ModelAndView home() {
		return new ModelAndView("index.html");
	}




	public static void main(String[] args) {
		SpringApplication.run(ArtApplication.class, args);


	}

}
