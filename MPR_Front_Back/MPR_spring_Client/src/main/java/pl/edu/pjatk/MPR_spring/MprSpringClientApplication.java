package pl.edu.pjatk.MPR_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.edu.pjatk.MPR_spring")
public class MprSpringClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MprSpringClientApplication.class, args);
	}

}
