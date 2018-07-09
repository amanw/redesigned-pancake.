package com.rpancake.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SuppressWarnings("unused")
@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver =  new ServletContextTemplateResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		Set<IDialect> dialects = new HashSet<IDialect>();
		dialects.add(new LayoutDialect());
		
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.setAdditionalDialects(dialects);
		return engine;
	}
	
	@Bean
	public ThymeleafViewResolver ViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setOrder(1);
		resolver.setViewNames(new String[] {"*", "js/*", "template/*"});
		return resolver;
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/vendor/bootstrap/css/**",
                "/vendor/jquery/**",
                "/vendor/bootstrap/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/public/img/",
                        "classpath:/public/css/",
                        "classpath:/public/js/",
                        "classpath:/public/vendor/bootstrap/css/",
                        "classpath:/public/vendor/jquery/",
                        "classpath:/public/vendor/bootstrap/js/");
    }
	

}
