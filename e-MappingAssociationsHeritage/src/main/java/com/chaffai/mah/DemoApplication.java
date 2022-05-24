package com.chaffai.mah;



import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chaffai.mah.entities.Role;
import com.chaffai.mah.entities.User;
import com.chaffai.mah.services.UserService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService) {
		return args -> {

			// Ajouter User
			User u = new User();
			u.setUsername("user1");
			u.setPassword("123456");
			userService.addNewUser(u);

			User u2 = new User();
			u2.setUsername("admin");
			u2.setPassword("123456");
			userService.addNewUser(u2);

			// Ajouter Role
			Stream.of("STUDENT", "USER", "ADMIN").forEach(r -> {
				Role role1 = new Role();
				role1.setRoleName(r);
				userService.addNewRole(role1);
			});
			
			//Affecter les roles aux utilisateurs
			userService.addRoleToUser("user1", "STUDENT");
			userService.addRoleToUser("user1", "USER");
			
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");
			
			//authentification d'un utilisateur
			try {
				User user = userService.authenticate("user1", "123456");
				System.out.println(user.getUserId());
				System.out.println(user.getUsername());
				System.out.println("ROLES ==>");
				user.getRoles().forEach(r->{
					System.out.println("role: " + r.toString());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	}
}
