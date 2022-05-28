package com.yoda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yoda.filter.JwtFilter;
import com.yoda.filter.jwtFilterMgnt;


@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.yoda.services","com.yoda.models","com.yoda.controller","com.yoda"})
public class YodaProjectApplication extends SpringBootServletInitializer {

	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/authenticated/*");

        return registrationBean;
    }
	
	@Bean
    public FilterRegistrationBean jwtFilterMgnt() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new jwtFilterMgnt());
        registrationBean.addUrlPatterns("/mgnt/*");

        return registrationBean;
    }

	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YodaProjectApplication.class);
    }
    

    public static void main(String[] args) throws Exception {
        SpringApplication.run(YodaProjectApplication.class, args);
       /* new SpringApplicationBuilder(YodaProjectApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);*/
    }
  
    
}