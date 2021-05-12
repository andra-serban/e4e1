package com.javamaster.e4e1.DBConnection;

import com.javamaster.e4e1.dtos.RegisterDto;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class DBQueriesService {

    /**
     * Insert an user into the database; The user ID is generated at db insertion (based on Autogenerate property set up
     * in the database and on the {@link com.example.javalabs.models.User} id field
     *
     * @param user - the model received in the controller
     * @throws Exception - if the database insertion fails
     */
    public void saveUser(RegisterDto user) throws Exception {
        String sql = String.format(
                "INSERT INTO users(first_name, last_name, email, username, password) VALUES('%s', '%s', '%s', '%s', '%s')",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword()
        );

        Statement statement = DBConnection.getConnection().createStatement();
        statement.execute(sql);
    }

    /**
     * Verify that an user exists in the database based on a given email address.
     *
     * @param email - the given email address
     * @return - a boolean of the user existence state
     * @throws Exception - if the db connection fails
     */
    public boolean userExistsByEmail(String email) throws Exception {
        String sql = String.format("SELECT email FROM users WHERE email='%s'", email);

        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet.next();
    }

}

