package com.ivoyant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        // Insert users
        userRepository.saveUser("John Doe", "john@example.com");
        userRepository.saveUser("Jane Smith", "jane@example.com");

        System.out.println("Printing user with id 2");
        User user = userRepository.getUserById(15);
        System.out.println(user);

        System.out.println("Updating the user email id to john12@gmail.com");
        userRepository.updateUserEmail(16, "jonh12@gmail.com");

        System.out.println("Deleting the user by id 5");
        userRepository.deleteUserById(17);

        // Query all users
        List<User> users = userRepository.getAllUsers();
        for (User user1 : users) {
            System.out.println("User ID: " + user1.getId() + ", Name: " + user1.getName() + ", Email: " + user1.getEmail());
        }

        userRepository.createUsersTransaction("John Doe", "john@example.com","Jane Smith", "jane@example.com");
    }
}