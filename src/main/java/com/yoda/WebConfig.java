package com.yoda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String directory = env.getProperty("yoda.uploadedFiles.rootDir");
        System.out.println(directory);

        // registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
       // registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+directory);
      //  registry.addResourceHandler("/images/**").addResourceLocations("file:/Users/thienkyo/Downloads/pic/");
    }

}
