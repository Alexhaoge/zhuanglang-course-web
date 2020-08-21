package xyz.alexhaoge.zhuanglang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import xyz.alexhaoge.zhuanglang.intercept.LoginInterceptor;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer{

    @Value("${web.upload.path}")
    private String uploadPath;

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/api/login/**")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/library/**")
                .excludePathPatterns("/api/resources/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/resources/**").addResourceLocations("file:"+uploadPath);
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS","DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}