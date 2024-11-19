package org.example.movieapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
// default: scan all classes of this package and subpackages
//		to add others use params : scanBasePackages, scanBasePackageClasses
public class MovieapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieapiApplication.class, args);
	}

}
