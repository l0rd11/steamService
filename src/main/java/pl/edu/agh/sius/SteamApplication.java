package pl.edu.agh.sius;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class SteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamApplication.class, args);
	}
}
