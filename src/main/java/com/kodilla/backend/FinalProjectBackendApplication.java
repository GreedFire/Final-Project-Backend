package com.kodilla.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBackendApplication.class, args);
	}

	//TODO:
	// - check gradlew build
	// - do user register and login
	// - do booking
	// - MAYBE DO SEARCH DROPDOWN MENU
	// - MAYBE GENERATE PRICE RISE DEPENDING ON CARRIER CLASS
	// - MAYBE FIX FLIGHT HISTORY, CHANGE IT TO LIST OF OBJECTS
	// - MAYBE FIX ALL HISTORY SEARCH - ADD DATE ADDED TO ENTITIES AND SEARCH BY THAT
	// - MAYBE SEARCH HISTORY FOR HOLIDAY
	// - MAYBE FIX FRONTEND ENTERED DATA TO PREVENT ERRORS ON IDE
	// - MAYBE DO ACTIONS IN SERVICES NOT CONTROLLERS
	// - MAYBE FIX CONTROLLERS TO RETURN EMPTY LISTS OR NULL?
	// - MAYBE LATER TAKE URLs FROM APPLICATON PROPERTIES

}
