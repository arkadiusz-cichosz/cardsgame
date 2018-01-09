package org.pwpw.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardsGameApplication {
 
 /*@Autowired
 private PlayerRepository playerRepository;
 
 @Bean
 public PlayerRepository createPlayerRepositoryBean() {
     System.out.println("Tworze PlayerRepository");
     return new PlayersStorage();
 }*/
 
	public static void main(String[] args) {
		SpringApplication.run(CardsGameApplication.class, args);
	}
}
