package containers;

import org.testcontainers.containers.MySQLContainer;

public final class MySqlContainerManager {

    private MySqlContainerManager() {
        // Prevent object creation
    }

    private static final MySQLContainer<?> MYSQL_CONTAINER =
            new MySQLContainer<>("mysql:8.0")

                    .withDatabaseName("tripstack")

                    .withUsername("test")

                    .withPassword("test");


    public static void start() {

        if (!MYSQL_CONTAINER.isRunning()) {

            MYSQL_CONTAINER.start();

            System.out.println("====================================");
            System.out.println("MySQL Testcontainer Started");
            System.out.println("JDBC URL : " + MYSQL_CONTAINER.getJdbcUrl());
            System.out.println("====================================");

        }

    }


    public static void stop() {

        if (MYSQL_CONTAINER.isRunning()) {

            MYSQL_CONTAINER.stop();

            System.out.println("MySQL Testcontainer Stopped");

        }

    }


    public static String getJdbcUrl() {

        return MYSQL_CONTAINER.getJdbcUrl();

    }


    public static String getUsername() {

        return MYSQL_CONTAINER.getUsername();

    }


    public static String getPassword() {

        return MYSQL_CONTAINER.getPassword();

    }

}