package dataAccessLayer;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A generic Data Access Object class for performing CRUD operations on a database table.
 *
 * @param <T> the type of entity this DAO operates on
 */
public class AbstractDAO<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Constructs a new instance of AbstractDAO with the specified entity class.
     *
     * @param entityClass the class of the entity this DAO operates on
     */
    public AbstractDAO(Class<T> entityClass) {
        this.type = entityClass;
    }

    /**
     * Creates a SELECT SQL query for retrieving all records from the database table associated with the entity class.
     *
     * @return the generated SELECT query as a string
     */
    private String createSelectQuery() {
        return "SELECT * FROM " + type.getSimpleName();
    }

    /**
     * Retrieves all records from the database table associated with the entity class.
     *
     * @return an ArrayList containing all records retrieved from the database
     */
    public ArrayList<T> selectAll() {
        ArrayList<T> list = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectStatement = createSelectQuery();

        try {
            statement = connection.prepareStatement(selectStatement);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T obj = type.getDeclaredConstructor().newInstance();

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Field field = type.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, resultSet.getObject(columnName));
                }

                list.add(obj);
            }
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, "Error selecting all objects");
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return list;
    }

    /**
     * Creates an INSERT SQL query for inserting a new record into the database table associated with the entity class.
     *
     * @return the generated INSERT query as a string
     */
    private String createInsertQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        int nrFields = 0;

        stringBuilder.append("INSERT INTO `");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append("` (");
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                ++nrFields;
                stringBuilder.append(field.getName());
                stringBuilder.append(", ");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append(")");
        stringBuilder.append(" VALUES (");

        for (int i = 1; i <= nrFields; i++) {
            stringBuilder.append("?");
            if (i != nrFields) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    /**
     * Inserts a new record into the database table associated with the entity class.
     *
     * @param t the object representing the record to be inserted
     * @return <p>the auto-generated ID of the inserted record<br>-1 if the insertion fails</p>
     */
    public int insert(T t) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createInsertQuery();
        PreparedStatement statement = null;
        ResultSet rs = null;
        int id = -1;
        int i = 1;

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    statement.setObject(i++, field.get(t));
                }
            }

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error inserting the object");
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return id;
    }

    /**
     * Creates a DELETE SQL query for deleting records from the database table associated with the entity class.
     *
     * @return the generated DELETE query as a string
     */
    private String createDeleteQuery() {
        return "DELETE FROM " + type.getSimpleName() + " WHERE name = ?";
    }

    /**
     * Deletes a record from the database table associated with the entity class.
     *
     * @param name the name or identifier of the record to be deleted
     * @return <p>true if the deletion is successful<br>false otherwise</p>
     */
    public boolean delete(String name) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createDeleteQuery();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error deleting the object");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return true;
    }

    /**
     * Creates an UPDATE SQL query for updating records in the database table associated with the entity class.
     *
     * @return the generated UPDATE query as a string
     */
    private String createUpdateQuery() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            stringBuilder.append(field.getName());
            stringBuilder.append(" = ?, ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append(" WHERE id = ?");

        return stringBuilder.toString();
    }

    /**
     * Updates a record in the database table associated with the entity class.
     *
     * @param t the object representing the updated record
     * @return <p>true if the update is successful<br>false otherwise</p>
     */
    public boolean update(T t) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createUpdateQuery();
        PreparedStatement statement = null;
        int i = 0;

        try {
            statement = connection.prepareStatement(query);
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                statement.setObject(++i, field.get(t));
            }
            statement.setObject(++i, fields[0].get(t));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error updating the object");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a SELECT SQL query for retrieving a record by its ID from the database table associated with the entity class.
     *
     * @return the generated SELECT query as a string
     */
    private String createFindByIdQuery() {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE id = ?";
    }

    /**
     * Retrieves a record by its ID from the database table associated with the entity class.
     *
     * @param id the ID of the record to retrieve
     * @return <p>the retrieved record<br>null if no record is found with the specified ID</p>
     */
    public T findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createFindByIdQuery();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            T obj = type.getDeclaredConstructor().newInstance();
            if (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int j = 1; j <= columnCount; j++) {
                    String columnName = metaData.getColumnName(j);
                    Field field = type.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(columnName));
                }
                return obj;
            }

            return null;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException |
                 InstantiationException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Error finding the object");
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a SELECT SQL query for retrieving a record by its name from the database table associated with the entity class.
     *
     * @return the generated SELECT query as a string
     */
    private String createFindByNameQuery() {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE name = ?";
    }

    /**
     * Retrieves a record by its name from the database table associated with the entity class.
     *
     * @param name the name of the record to retrieve
     * @return <p>the retrieved record<br>null if no record is found with the specified name</p>
     */
    public T findByName(String name) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createFindByNameQuery();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            rs = statement.executeQuery();
            T obj = type.getDeclaredConstructor().newInstance();
            if (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int j = 1; j <= columnCount; j++) {
                    String columnName = metaData.getColumnName(j);
                    Field field = type.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(columnName));
                }

                return obj;
            }
            return null;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException |
                 InstantiationException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Error finding the object");
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
