package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DbHelper {

    private DbHelper() {
        // Prevent object creation
    }


    public static int countRows(String sql) {

        try (

                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            resultSet.next();

            return resultSet.getInt(1);

        }

        catch (SQLException exception) {

            throw new RuntimeException(
                    "Failed to execute count query",
                    exception
            );

        }

    }


    public static boolean exists(String sql) {

        return countRows(sql) > 0;

    }


    public static String getString(String sql) {

        try (

                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            if (resultSet.next()) {

                return resultSet.getString(1);

            }

            return null;

        }

        catch (SQLException exception) {

            throw new RuntimeException(
                    "Failed to execute query",
                    exception
            );

        }

    }


    public static int getInt(String sql) {

        try (

                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);

            }

            return 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(
                    "Failed to execute query",
                    exception
            );

        }

    }

}