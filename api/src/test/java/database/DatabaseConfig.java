package database;

import containers.MySqlContainerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConfig {

    private DatabaseConfig() {
        // Prevent object creation
    }


    public static Connection getConnection() {

        try {

            MySqlContainerManager.start();

            return DriverManager.getConnection(

                    MySqlContainerManager.getJdbcUrl(),

                    MySqlContainerManager.getUsername(),

                    MySqlContainerManager.getPassword()

            );

        }

        catch (SQLException exception) {

            throw new RuntimeException(
                    "Unable to establish database connection",
                    exception
            );

        }

    }

}