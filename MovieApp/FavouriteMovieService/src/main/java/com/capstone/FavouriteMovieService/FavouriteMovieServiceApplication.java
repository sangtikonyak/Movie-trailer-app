package com.capstone.FavouriteMovieService;

import com.capstone.FavouriteMovieService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@CrossOrigin("http://localhost:4200")
@SpringBootApplication
@EnableEurekaClient
public class FavouriteMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteMovieServiceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v2/user/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		final CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:4200");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",configuration);
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new CorsFilter(source));
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filterRegistrationBean;

	}
}
