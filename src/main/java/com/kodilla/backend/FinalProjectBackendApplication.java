package com.kodilla.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBackendApplication.class, args);
	}

	//TODO:
	// - (TO DO) finish mapperFactory
	// - (TO DO?) add facade design pattern
	// - (FIX M) probably two users will see same carrier after buying because instance is static https://vaadin.com/docs/v8/framework/advanced/advanced-global.html
	// - (FIX H) probably after refreshing site user will be log out but in database will be log in
	// - (FIX M) EOFException while reading PDF: Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
	// - (ADDITIONAL) MAYBE DO SEARCH DROPDOWN MENU
	// - (ADDITIONAL) add invoice pdf to database, get invoice in account settings - history
	// - (ADDITIONAL) additional backend scheduler - delete unused accounts

}
