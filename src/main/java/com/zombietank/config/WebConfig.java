package com.zombietank.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logged-out").setViewName("logged-out");
		registry.addViewController("/timeout").setViewName("timeout");
		registry.addViewController("/error").setViewName("error");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	/*** Support for @ExceptionHandler annotations in controllers for more specific handling. */
	public HandlerExceptionResolver annotationExceptionResolver() {
		AnnotationMethodHandlerExceptionResolver resolver = new AnnotationMethodHandlerExceptionResolver();
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	/*** Logs error with associated URL; redirects to error page. */
	public HandlerExceptionResolver defaultExceptionResolver() {
		AbstractHandlerExceptionResolver resolver = new AbstractHandlerExceptionResolver() {
			@Override
			protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
				logger.error("Error generated at: " + request.getRequestURL(), ex);
				return new ModelAndView("redirect:/error");
			}
		};
		resolver.setOrder(2);
		return resolver;
	}
}
