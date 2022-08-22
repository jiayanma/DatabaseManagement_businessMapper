package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Region;

public class RegionDao {

  protected ConnectionManager connectionManager;

  private static RegionDao instance = null;

  protected RegionDao() {
    connectionManager = new ConnectionManager();
  }

  public static RegionDao getInstance() {
    if (instance == null) {
      instance = new RegionDao();
    }
    return instance;
  }

  public Region create(Region region) throws SQLException {
    String insertRegion = "INSERT INTO Region(ZipCode, StateName, City, CountyName) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRegion);
      insertStmt.setString(1, region.getZipCode());
      insertStmt.setString(2, region.getStateName());
      insertStmt.setString(3, region.getCity());
      insertStmt.setString(4, region.getCountyName());

      insertStmt.executeUpdate();
      return region;
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

  public Region getRegionByZipCode(String zipcode) throws SQLException {
    String selectRegion = "SELECT ZipCode, StateName, City, CountyName FROM Region WHERE ZipCode=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRegion);
      selectStmt.setString(1, zipcode);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String ZipCode = results.getString("ZipCode");
        String StateName = results.getString("StateName");
        String City = results.getString("City");
        String CountyName = results.getString("CountyName");
        Region Region = new Region(ZipCode, StateName, City, CountyName);
        return Region;
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

  public Region getRegionByZipCodeAndStateName(String zipcode, String statename)
      throws SQLException {
    String selectRegion = "SELECT ZipCode, StateName, City, CountyName FROM Region WHERE ZipCode=? AND StateName =?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRegion);
      selectStmt.setString(1, zipcode);
      selectStmt.setString(2, statename);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String ZipCode = results.getString("ZipCode");
        String StateName = results.getString("StateName");
        String City = results.getString("City");
        String CountyName = results.getString("CountyName");
        Region Region = new Region(ZipCode, StateName, City, CountyName);
        return Region;
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

  public Region delete(Region region) throws SQLException {
    String deleteRegion = "DELETE FROM Region WHERE ZipCode=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRegion);
      deleteStmt.setString(1, region.getZipCode());
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
