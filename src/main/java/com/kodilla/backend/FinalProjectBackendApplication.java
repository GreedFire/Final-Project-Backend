package com.kodilla.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBackendApplication.class, args);
	}

	//TODO:
	// - (TO DO) one more save to database
	// - (TO DO) search by user or not user and add it to database
	// - (TO DO) finish mapperFactory
	// - (TO DO) MAYBE DO SEARCH DROPDOWN MENU
	// - (TO DO) add invoice pdf to database, get invoice in account settings - history
	// - (FIX ?) maybe scheduler save table is too complicated - service is doing it but maybe i can do it faster - check it
	// - (FIX) probably two users will see same carrier after buying because instance is static https://vaadin.com/docs/v8/framework/advanced/advanced-global.html
	// - (FIX H) probably after refreshing site user will be log out but in database will be log in
	// - (FIX H) EOFException while reading PDF: Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);

}
