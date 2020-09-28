package io.yovelas.EurekaServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistryApp {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistryApp.class, args);
	}

}
