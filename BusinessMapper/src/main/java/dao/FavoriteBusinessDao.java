package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.FavoriteBusiness;

public class FavoriteBusinessDao {

  protected ConnectionManager connectionManager;
  private static FavoriteBusinessDao instance = null;

  protected FavoriteBusinessDao() {
    connectionManager = new ConnectionManager();
  }

  public static FavoriteBusinessDao getInstance() {
    if (instance == null) {
      instance = new FavoriteBusinessDao();
    }
    return instance;
  }

  public FavoriteBusiness create(FavoriteBusiness FavoriteBusiness) throws SQLException {
    String insertFavoriteBusiness = "INSERT INTO FavoriteBusiness(UserName, BusinessId) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertFavoriteBusiness,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, FavoriteBusiness.getUserName());
      insertStmt.setString(2, FavoriteBusiness.getBusinessId());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int FavoriteId = -1;
      if (resultKey.next()) {
        FavoriteId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      FavoriteBusiness.setFavoriteId(FavoriteId);
      return FavoriteBusiness;
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

  public FavoriteBusiness getFavoriteBusinessById(Integer FavoriteId) throws SQLException {
    String selectFavoriteBusiness = "SELECT FavoriteId, UserName, BusinessId FROM FavoriteBusiness WHERE FavoriteId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavoriteBusiness);
      selectStmt.setInt(1, FavoriteId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        Integer id = results.getInt("FavoriteId");
        String UserName = results.getString("UserName");
        String BusinessId = results.getString("BusinessId");
        FavoriteBusiness FavoriteBusiness = new FavoriteBusiness(id, UserName,
            BusinessId);
        return FavoriteBusiness;
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

  public List<FavoriteBusiness> getFavoriteBusinessByUserName(String userName) throws SQLException {
    List<FavoriteBusiness> favoriteBusinessList = new ArrayList<>();
    String selectFavoriteBusiness = "SELECT FavoriteId, UserName, BusinessId FROM FavoriteBusiness WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavoriteBusiness);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      while (results.next()) {
        Integer FavoriteId = results.getInt("FavoriteId");
        String UserName = results.getString("UserName");
        String BusinessId = results.getString("BusinessId");
        FavoriteBusiness FavoriteBusiness = new FavoriteBusiness(FavoriteId, UserName,
            BusinessId);
        favoriteBusinessList.add(FavoriteBusiness);
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
    return favoriteBusinessList;
  }

  public List<FavoriteBusiness> getFavoriteBusinessByBusinessId(String BusinessId)
      throws SQLException {
    List<FavoriteBusiness> FavoriteBusinessList = new ArrayList<>();
    String selectFavoriteBusiness = "SELECT FavoriteId, UserName, BusinessId FROM FavoriteBusiness WHERE BusinessId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavoriteBusiness);
      selectStmt.setString(1, BusinessId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        Integer FavoriteId = results.getInt("FavoriteId");
        String UserName = results.getString("UserName");
        FavoriteBusiness FavoriteBusiness = new FavoriteBusiness(FavoriteId, UserName,
            BusinessId);
        FavoriteBusinessList.add(FavoriteBusiness);
        return FavoriteBusinessList;
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

  public FavoriteBusiness delete(FavoriteBusiness FavoriteBusiness) throws SQLException {
    String deleteFavoriteBusiness = "DELETE FROM FavoriteBusiness WHERE FavoriteId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteFavoriteBusiness);
      deleteStmt.setInt(1, FavoriteBusiness.getFavoriteId());
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
