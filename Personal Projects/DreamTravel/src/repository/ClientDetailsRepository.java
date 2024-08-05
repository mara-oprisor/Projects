package repository;

import model.AdminModel;
import model.ClientModel;

import javax.swing.*;
import java.sql.*;

public class ClientDetailsRepository {
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

    public static void selectDetails(String username) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            String sql = "SELECT * from client_informations WHERE user_id = (SELECT user_id FROM \"user\" WHERE user_name=?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String s1 = resultSet.getString(2);
                Integer s2 = Integer.valueOf(resultSet.getString(3));
                String s3 = resultSet.getString(4);
                String s4 = resultSet.getString(5);
                String s5 = resultSet.getString(6);

                ClientModel.getCurrentClient().setName(s1);
                ClientModel.getCurrentClient().setAge(s2);
                ClientModel.getCurrentClient().setDateOfBirth(s3);
                ClientModel.getCurrentClient().setEmail(s4);
                ClientModel.getCurrentClient().setPhoneNr(s5);
                ClientModel.getCurrentClient().setUsername(ClientModel.getCurrentUser().getUsername());
                ClientModel.getCurrentClient().setTypeOfUser(ClientModel.getCurrentUser().getUserType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    }

    public static void deleteDetails(String username) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            String sql = "DELETE from client_informations WHERE user_id = (SELECT user_id FROM \"user\" WHERE user_name=?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Details deleted successfully!");
                ClientModel.getCurrentClient().setName(null);
                ClientModel.getCurrentClient().setAge(0);
                ClientModel.getCurrentClient().setDateOfBirth(null);
                ClientModel.getCurrentClient().setEmail(null);
                ClientModel.getCurrentClient().setPhoneNr(null);
            } else {
                JOptionPane.showMessageDialog(null, "No details found for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting details: " + e.getMessage());
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
    }

    public static void updateDetails(String username, String name, int age, String dateOfBirth, String email, String phoneNr) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            String sql = "UPDATE client_informations SET name=?, age=?, date_of_birth=?, email=?, phone_nr=? WHERE user_id = (SELECT user_id FROM \"user\" WHERE user_name=?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, dateOfBirth);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNr);
            preparedStatement.setString(6, username);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Updated details successfully!");
                ClientModel.getCurrentClient().setName(name);
                ClientModel.getCurrentClient().setAge(age);
                ClientModel.getCurrentClient().setDateOfBirth(dateOfBirth);
                ClientModel.getCurrentClient().setEmail(email);
                ClientModel.getCurrentClient().setPhoneNr(phoneNr);
            } else {
                JOptionPane.showMessageDialog(null, "No details found to be updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating details: " + e.getMessage());
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
    }

    public static void insertDetails(String username, String name, int age, String dateOfBirth, String email, String phoneNr) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            String sql = "SELECT user_id FROM \"user\" WHERE user_name=?";
            PreparedStatement getUserIdStatement = con.prepareStatement(sql);
            getUserIdStatement.setString(1, username);
            ResultSet resultSet = getUserIdStatement.executeQuery();

            int userId = -1;
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            String sql1 = "INSERT INTO client_informations (user_id, name, age, date_of_birth, email, phone_nr) VALUES (?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phoneNr);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Inserted details successfully!");
                ClientModel.getCurrentClient().setName(name);
                ClientModel.getCurrentClient().setAge(age);
                ClientModel.getCurrentClient().setDateOfBirth(dateOfBirth);
                ClientModel.getCurrentClient().setEmail(email);
                ClientModel.getCurrentClient().setPhoneNr(phoneNr);
            } else {
                JOptionPane.showMessageDialog(null, "No details found to be inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting details!");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
