package team1.togather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TogatherWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogatherWithJpaApplication.class, args); 
	}

}
