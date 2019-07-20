package com.kodilla.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBackendApplication.class, args);
	}

	//TODO:
	// - do booking
	// - FIX FRONTEND REFRESH[its done but cant hide elements) (i think problem is with navigate bar https://www.youtube.com/watch?v=-xejxaIQTO8
	// - MAYBE DO SEARCH DROPDOWN MENU
	// - MAYBE FIX ALL HISTORY SEARCH - ADD DATE ADDED TO ENTITIES AND SEARCH BY THAT
	// - MAYBE SEARCH HISTORY FOR HOLIDAY
	// - FLIGHT CONTROLLER BAD REQUEST 400 MAY HAPPEN PROBABLY BECAUSE OF WRONG DATE
	// - MAYBE DO ACTIONS IN SERVICES NOT CONTROLLERS

}
