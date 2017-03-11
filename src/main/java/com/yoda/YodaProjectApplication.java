package com.yoda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration
/*@SpringBootApplication(scanBasePackages = { "com.services" })
public class YodaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YodaProjectApplication.class, args);
	}
}*/

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.yoda.services","com.yoda.models","com.yoda.controller","com.yoda"})
public class YodaProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YodaProjectApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(YodaProjectApplication.class, args);
    }
    
}