package tn.micro.service.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import feign.Logger;
import tn.micro.service.cloud.exception.CustomErrorDecoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("tn.micro.service.cloud.proxies") //se charge de crea

public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);

	}
	
	//@Value("${address.service.url}")
	//private String addressUrl;
	//@Bean 
	//   public WebClient webClient(WebClient.Builder builder) {
	  //      return builder.build(); // Initialisation d'un WebClient
	    //}
	
	
	@Bean 
	public CustomErrorDecoder CustomErrorDecoder(){
	return new CustomErrorDecoder();
	}
	
	@Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;  // Loguer les détails des requêtes et des réponses
    }
}