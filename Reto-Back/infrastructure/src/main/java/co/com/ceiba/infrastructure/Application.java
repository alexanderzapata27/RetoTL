package co.com.ceiba.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"co.com.ceiba.application", "co.com.ceiba.domain",
		"co.com.ceiba.infrastructure"})

public class Application {
	public static void main(String[] args) {
		if(args.length>0) {
		    SpringApplication.run(Application.class, args);
		}
	}
}
