package com.christianpari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchWalkthroughApplication {

	public static void main(String[] args)  throws Exception {
		System.exit(
			SpringApplication.exit(
				SpringApplication.run(BatchWalkthroughApplication.class, args)
			)
		);
	}

}
