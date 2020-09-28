package io.yovelas.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class CommentApp {

//	@LoadBalanced
//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//	@Autowired
//	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String home() {
		return "hello comment";
	}

//    @GetMapping("/student")
//    public Student getStudent() {
//        return new Student("毛病陈同学", 18);
//    }
	public static void main(String[] args) {
		SpringApplication.run(CommentApp.class, args);
	}

}
