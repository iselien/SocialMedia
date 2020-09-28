package io.yovelas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GatewayApp {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(GatewayApp.class,args);
    }
}
