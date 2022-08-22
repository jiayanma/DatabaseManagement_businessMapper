package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDao {

  protected ConnectionManager connectionManager;
  private static UserDao instance = null;

  protected UserDao() {
    connectionManager = new ConnectionManager();
  }

  public static UserDao getInstance() {
    if (instance == null) {
      instance = new UserDao();
    }
    return instance;
  }

  public User create(User user) throws SQLException {
    String insertUser = "INSERT INTO User(UserName, Password, FirstName, LastName, Email, PhoneNumber) VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, user.getUserName());
      insertStmt.setString(2, user.getPassword());
      insertStmt.setString(3, user.getFirstName());
      insertStmt.setString(4, user.getLastName());
      insertStmt.setString(5, user.getEmail());
      insertStmt.setString(6, user.getPhoneNumber());
      insertStmt.executeUpdate();
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  public User getUserByUserName(String userName) throws SQLException {
    String selectUser = "SELECT UserName, Password, FirstName, LastName, Email, PhoneNumber FROM User WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String UserName = results.getString("UserName");
        String Password = results.getString("Password");
        String FirstName = results.getString("FirstName");
        String LastName = results.getString("LastName");
        String Email = results.getString("Email");
        String PhoneNumber = results.getString("PhoneNumber");
        User User = new User(UserName, Password, FirstName, LastName, Email, PhoneNumber);
        return User;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public User getUserByUserNameAndPassword(String userName, String password) throws SQLException {
    String selectUser = "SELECT UserName, Password, FirstName, LastName, Email, PhoneNumber FROM User WHERE UserName=? AND Password =?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, userName);
      selectStmt.setString(2, password);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String UserName = results.getString("UserName");
        String Password = results.getString("Password");
        String FirstName = results.getString("FirstName");
        String LastName = results.getString("LastName");
        String Email = results.getString("Email");
        String PhoneNumber = results.getString("PhoneNumber");
        User User = new User(UserName, Password, FirstName, LastName, Email, PhoneNumber);
        return User;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public User delete(User user) throws SQLException {
    String deleteUser = "DELETE FROM User WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setString(1, user.getUserName());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
