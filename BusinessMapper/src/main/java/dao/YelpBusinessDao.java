package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class YelpBusinessDao {

  protected ConnectionManager connectionManager;

  private static YelpBusinessDao instance = null;

  protected YelpBusinessDao() {
    connectionManager = new ConnectionManager();
  }

  public static YelpBusinessDao getInstance() {
    if (instance == null) {
      instance = new YelpBusinessDao();
    }
    return instance;
  }

  public YelpBusiness create(YelpBusiness yelpBusiness) throws SQLException {
    String insertYelpBusiness =
        "INSERT INTO YelpBusiness(Name,Address,ZipCode,Latitude,Longitude,Rating,ReviewCount,isOpen) "
            +
            "VALUES(?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertYelpBusiness, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, yelpBusiness.getName());
      insertStmt.setString(2, yelpBusiness.getAddress());
      insertStmt.setString(3, yelpBusiness.getZipCode());
      insertStmt.setDouble(4, yelpBusiness.getLatitude());
      insertStmt.setDouble(5, yelpBusiness.getLongitude());
      insertStmt.setDouble(6, yelpBusiness.getRating());
      insertStmt.setInt(7, yelpBusiness.getReviewCount());
      insertStmt.setBoolean(8, yelpBusiness.getOpen());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      String BusinessId = "-1";
      if (resultKey.next()) {
        BusinessId = resultKey.getString(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      yelpBusiness.setBusinessId(BusinessId);
      return yelpBusiness;
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
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  public YelpBusiness getYelpBusinessById(String BusinessId) throws SQLException {
    String selectYelpBusiness =
        "SELECT BusinessId,Name,Address,ZipCode,Latitude,Longitude,Rating,ReviewCount,isOpen FROM YelpBusiness WHERE BusinessId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectYelpBusiness);
      selectStmt.setString(1, BusinessId);
      results = selectStmt.executeQuery();

      if (results.next()) {
        String Name = results.getString("Name");
        String Address = results.getString("Address");
        String ZipCode = results.getString("ZipCode");
        Double Latitude = results.getDouble("Latitude");
        Double Longitude = results.getDouble("Longitude");
        Double Rating = results.getDouble("Rating");
        Integer ReviewCount = results.getInt("ReviewCount");
        Boolean isOpen = results.getBoolean("isOpen");
        YelpBusiness yelpBusiness = new YelpBusiness(BusinessId, Name, Address, ZipCode, Latitude,
            Longitude, Rating, ReviewCount, isOpen);
        return yelpBusiness;
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

  public List<YelpBusiness> getYelpBusinessListByZipCode(String zipCode) throws SQLException {
    List<YelpBusiness> yelpBusinessList = new ArrayList<>();
    String selectYelpBusiness = null;
    if (zipCode == null || zipCode.length() == 0) {
      selectYelpBusiness = "SELECT BusinessId,Name,Address,ZipCode,Latitude,Longitude,Rating,ReviewCount,isOpen FROM YelpBusiness;";
    } else {
      selectYelpBusiness = "SELECT BusinessId,Name,Address,ZipCode,Latitude,Longitude,Rating,ReviewCount,isOpen FROM YelpBusiness WHERE ZipCode=?;";
    }
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectYelpBusiness);
      if (zipCode != null && zipCode.length() != 0) {
        selectStmt.setString(1, zipCode);
      }
      results = selectStmt.executeQuery();
      while (results.next()) {
        String BusinessId = results.getString("BusinessId");
        String Name = results.getString("Name");
        String Address = results.getString("Address");
        String ZipCode = results.getString("ZipCode");
        Double Latitude = results.getDouble("Latitude");
        Double Longitude = results.getDouble("Longitude");
        Double Rating = results.getDouble("Rating");
        Integer ReviewCount = results.getInt("ReviewCount");
        Boolean isOpen = results.getBoolean("isOpen");
        YelpBusiness yelpBusiness = new YelpBusiness(BusinessId, Name, Address, ZipCode, Latitude,
            Longitude, Rating, ReviewCount, isOpen);
        if (Name == null || Name.length() == 0) {
          continue;
        }
        if (Address == null || Address.length() == 0) {
          continue;
        }
        yelpBusinessList.add(yelpBusiness);
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
    return yelpBusinessList;
  }


  public YelpBusiness delete(YelpBusiness yelpBusiness) throws SQLException {
    String deleteYelpBusiness = "DELETE FROM YelpBusiness WHERE BusinessId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteYelpBusiness);
      deleteStmt.setString(1, yelpBusiness.getBusinessId());

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
