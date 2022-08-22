// Jiayan Ma

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

/**
 * Data access object (DAO) class to interact with the underlying Category table in your MySQL
 * instance. This is used to store {@link Category} into your MySQL instance and retrieve {@link
 * Category} from MySQL instance.
 */
public class CategoryDao {

  protected ConnectionManager connectionManager;

  private static CategoryDao instance = null;

  protected CategoryDao() {
    connectionManager = new ConnectionManager();
  }

  public static CategoryDao getInstance() {
    if (instance == null) {
      instance = new CategoryDao();
    }
    return instance;
  }

  /**
   * Save the Category instance by storing it in your MySQL instance. This runs a INSERT statement.
   */
  public Category create(Category category) throws SQLException {
    String insertCategory = "INSERT INTO Category(Type) VALUES(?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCategory);
      insertStmt = connection.prepareStatement(insertCategory,
          Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, category.getType());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int categoryId = -1;
      if (resultKey.next()) {
        categoryId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      category.setCategoryId(categoryId);

      return category;
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
   * Get the Category record by fetching it from your MySQL instance. This runs a SELECT statement
   * and returns a single Category instance.
   */
  public Category getCategoryById(int categoryId) throws SQLException {
    String selectCategory = "SELECT CategoryId, Type FROM Category WHERE categoryId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCategory);
      selectStmt.setInt(1, categoryId);

      results = selectStmt.executeQuery();

      if (results.next()) {
        Integer resultCategoryId = results.getInt("CategoryId");
        String type = results.getString("Type");
        Category category = new Category(resultCategoryId, type);
        return category;
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
   * Update the Type of the Category instance. This runs a UPDATE statement.
   */
  public Category updateType(Category category, String newType) throws SQLException {
    String updateType = "UPDATE Category SET Type=? WHERE CategoryId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateType);
      updateStmt.setString(1, newType);
      updateStmt.setString(2, category.getType());
      updateStmt.executeUpdate();
      category.setType(newType);
      return category;
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
   * Delete the Category instance. This runs a DELETE statement.
   */
  public Category delete(Category category) throws SQLException {
    String deleteCategory = "DELETE FROM Category WHERE CategoryId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCategory);
      deleteStmt.setInt(1, category.getCategoryId());
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
