package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for managing database connections.
 */
public class ConnectionFactory {
    /**
     * The logger instance for logging connection-related events.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     * The JDBC driver class name.
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * The database URL.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/order_management_db";
    /**
     * The database username.
     */
    private static final String USER = "root";
    /**
     * The database password.
     */
    private static final String PASS = "Root@123";
    /**
     * The singleton instance of the ConnectionFactory class.
     */
    private static ConnectionFactory instance = new ConnectionFactory();

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new database connection.
     *
     * @return a Connection object representing the database connection
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the data base");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Retrieves the singleton instance of the ConnectionFactory class.
     *
     * @return the singleton instance of ConnectionFactory
     */
    public static Connection getConnection() {
        return instance.createConnection();
    }

    /**
     * Closes the database connection.
     *
     * @param connection the Connection object to be closed
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
            }
        }
    }

    /**
     * Closes the database statement.
     *
     * @param statement the Statement object to be closed
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    /**
     * Closes the database result set.
     *
     * @param resultSet the ResultSet object to be closed
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the result set");
            }
        }
    }
}
