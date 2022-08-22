package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

/**
 * Data access object (DAO) class to interact with the underlying BusinessCategory table in your
 * MySQL instance. This is used to store {@link BusinessCategory} into your MySQL instance and
 * retrieve {@link BusinessCategory} from MySQL instance.
 */
public class BusinessCategoryDao {

  protected ConnectionManager connectionManager;

  private static BusinessCategoryDao instance = null;

  protected BusinessCategoryDao() {
    connectionManager = new ConnectionManager();
  }

  public static BusinessCategoryDao getInstance() {
    if (instance == null) {
      instance = new BusinessCategoryDao();
    }
    return instance;
  }

  /**
   * Save the BusinessCategory instance by storing it in your MySQL instance. This runs a INSERT
   * statement.
   */
  public BusinessCategory create(BusinessCategory businessCategory) throws SQLException {
    String insertBusinessCategory = "INSERT INTO BusinessCategory(BusinessId, CategoryId) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertBusinessCategory);
      insertStmt = connection.prepareStatement(insertBusinessCategory,
          Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, businessCategory.getBusinessId());
      insertStmt.setInt(2, businessCategory.getCategory().getCategoryId());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int businessCategoryId = -1;
      if (resultKey.next()) {
        businessCategoryId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      businessCategory.setBusinessCategoryId(businessCategoryId);

      return businessCategory;
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

  /**
   * Get the BusinessCategory record by fetching it from your MySQL instance. This runs a SELECT
   * statement and returns a single BusinessCategory instance.
   */
  public BusinessCategory getBusinessCategoryById(int businessCategoryId) throws SQLException {
    String selectBusinessCategory = "SELECT BusinessCategoryId, BusinessId, CategoryId FROM BusinessCategory WHERE BusinessCategoryId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectBusinessCategory);
      selectStmt.setInt(1, businessCategoryId);
      results = selectStmt.executeQuery();
      CategoryDao categoryDao = CategoryDao.getInstance();

      if (results.next()) {
        Integer resultBusinessCategoryId = results.getInt("BusinessCategoryId");
        String BusinessId = results.getString("BusinessId");
        Integer categoryId = results.getInt("CategoryId");
        Category category = categoryDao.getCategoryById(categoryId);
        BusinessCategory businessCategory = new BusinessCategory(resultBusinessCategoryId,
            BusinessId, category);
        return businessCategory;
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

  /**
   * Update the Type of the BusinessCategory instance. This runs a UPDATE statement.
   */
  public BusinessCategory updateBusinessId(BusinessCategory businessCategory, String newBusinessId)
      throws SQLException {
    String updateBusinessId = "UPDATE BusinessCategory SET BusinessId=? WHERE BusinessCategoryId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateBusinessId);
      updateStmt.setString(1, newBusinessId);
      updateStmt.setString(2, businessCategory.getBusinessId());
      updateStmt.executeUpdate();
      businessCategory.setBusinessId(newBusinessId);
      return businessCategory;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Delete the BusinessCategory instance. This runs a DELETE statement.
   */
  public BusinessCategory delete(BusinessCategory businessCategory) throws SQLException {
    String deleteBusinessCategory = "DELETE FROM BusinessCategory WHERE BusinessCategoryId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteBusinessCategory);
      deleteStmt.setInt(1, businessCategory.getBusinessCategoryId());
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