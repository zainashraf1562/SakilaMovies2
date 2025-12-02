package com.pluralsight;

import com.pluralsight.db.DataManager;
import com.pluralsight.model.Actor;
import com.pluralsight.model.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("User and Password are needed to connect to the database!");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        DataManager dataManager = new DataManager(dataSource);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a last name: ");
        String lastName = scanner.nextLine();

        List<Actor> actorList = dataManager.displayActorName(lastName);
        actorList.forEach(System.out::println);

        System.out.println("Enter the first name: ");
        String firstName = scanner.nextLine();

        List<Film> filmList = dataManager.getFilmByName(firstName, lastName);
        filmList.forEach(System.out::println);







    }
}
