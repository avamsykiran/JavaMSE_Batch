package in.bta.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BtaGateway3Application {

	public static void main(String[] args) {
		SpringApplication.run(BtaGateway3Application.class, args);
	}

}
