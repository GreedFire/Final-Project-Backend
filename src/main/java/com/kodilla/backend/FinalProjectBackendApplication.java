package com.kodilla.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBackendApplication.class, args);
	}

	//TODO:
	// - (TO DO) do booking database save and getter endpoint to booking history on ACCount view
	// - (TO DO) search by user or not user and add it to database
	// - (TO DO) finish mapperFactory
	// - (TO DO) MAYBE DO SEARCH DROPDOWN MENU
	// - (TO DO) MAYBE DO ACTIONS IN SERVICES NOT CONTROLLERS
	// - (TO DO) MAYBE THROW ERRORS
	// - (FIX) multiple code
	// - (FIX) probably two users will see same carrier after buying because instance is static https://vaadin.com/docs/v8/framework/advanced/advanced-global.html
	// - (FIX hard) probably after refreshing site user will be log out but in database will be log in
	// - (FIX hard) EOFException while reading PDF: Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);

}
