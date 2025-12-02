package com.pluralsight.db;

import com.pluralsight.model.Actor;
import com.pluralsight.model.Film;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> displayActorName(String lastName){
        List<Actor> actors = new ArrayList<>();

        String actorQuery = "SELECT actor_id, first_name, last_name FROM sakila.actor WHERE last_name LIKE ? ORDER BY first_name;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(actorQuery)){
            preparedStatement.setString(1,lastName);

            try(ResultSet results = preparedStatement.executeQuery()){
                if (results.next()) {
                    do {
                        int actorId = results.getInt("actor_id");
                        String actorFirstName = results.getString("first_name");
                        String actorLastName = results.getString("last_name");

                        actors.add(new Actor(actorId, actorFirstName, actorLastName));
                    }while (results.next());
                } else {
                    System.out.println("Actor not found!");
                }
            }
        }catch (Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
        return actors;
    }

    public List<Film> getFilmByName(String firstName, String lastName){
        List<Film> films = new ArrayList<>();

        String filmQuery = """
                             SELECT f.film_id, f.title, f.`description`, f.release_year, f.length
                             FROM actor a
                             join film_actor fa on a.actor_id = fa.actor_id
                             join film f on fa.film_id = f.film_id
                             where last_name like ?
                             and first_name like ?;
                             """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(filmQuery)){
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        int filmId = resultSet.getInt("film_id");
                        String title = resultSet.getString("title");
                        String description = resultSet.getString("description");
                        int releaseYear = resultSet.getInt("release_year");
                        double length = resultSet.getInt("length");

                        films.add(new Film(filmId, title, description, releaseYear, length));
                    }while (resultSet.next());

                } else {
                    System.out.println("Film not found!");
                }
            }


        }catch (Exception e){
            System.out.println("error");
            e.printStackTrace();


        }
        return films;


    }


}
