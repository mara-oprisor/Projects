package repository;

import model.UserModel;

import java.sql.*;

public class LogInRepository {
    private static Connection connectToDataBase() {
        Connection con = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/travel site";
            String user = "postgres";
            String password = "Qwerty1!";

            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isUsername(String username) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();
        ResultSet resultSet = null;

        try {
            String sql = "SELECT user_name FROM \"user\" WHERE user_name = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserModel veritfyLogInProcess(String username, String password, String userType) {
        if (!isUsername(username)) {
            return null;
        }

        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            String sql = "SELECT * FROM \"user\" WHERE user_name = ? AND password = ? AND status = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UserModel.setUser(resultSet.getString(2), resultSet.getString(3), resultSet.getString(3));
                return UserModel.getCurrentUser();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
