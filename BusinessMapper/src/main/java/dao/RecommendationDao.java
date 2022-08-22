package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecommendationDao {

  protected ConnectionManager connectionManager;

  private static RecommendationDao instance = null;

  protected RecommendationDao() {
    connectionManager = new ConnectionManager();
  }

  public static RecommendationDao getInstance() {
    if (instance == null) {
      instance = new RecommendationDao();
    }
    return instance;
  }

  public Recommendation create(Recommendation recommendation) throws SQLException {
    String insertRecommendaiton =
        "INSERT INTO Recommendation(BusinessId,UserName) " +
            "VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRecommendaiton,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, recommendation.getBusiness().getBusinessId());
      insertStmt.setString(2, recommendation.getUser().getUserName());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int recommendationId = -1;
      if (resultKey.next()) {
        recommendationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }

      recommendation.setRecommendationId(recommendationId);
      return recommendation;
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

  public Recommendation getRecommendationById(int recommendationId) throws SQLException {
    String selectRecommendation =
        "SELECT RecommendationId,BusinessId,UserName " +
            "FROM Recommendation " +
            "WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      selectStmt.setInt(1, recommendationId);

      results = selectStmt.executeQuery();

      UserDao userDao = UserDao.getInstance();
      YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();

      if (results.next()) {
        int resultRecommendationId = results.getInt("RecommendationId");
        String businessId = results.getString("BusinessId");
        String userName = results.getString("UserName");

        User user = userDao.getUserByUserName(userName);
        YelpBusiness business = yelpBusinessDao.getYelpBusinessById(businessId);

        Recommendation recommendation = new Recommendation(resultRecommendationId, business, user);
        return recommendation;
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

  public List<Recommendation> getRecommendationsByBusinessId(String businessId)
      throws SQLException {
    List<Recommendation> recommendations = new ArrayList<>();
    String selectRecommendations =
        "SELECT RecommendationId,BusinessId,UserName " +
            "FROM Recommendation " +
            "WHERE BusinessId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendations);
      selectStmt.setString(1, businessId);

      UserDao userDao = UserDao.getInstance();
      YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();

      while (results.next()) {
        int recommendationId = results.getInt("RecommendationId");
        String resultBusinessId = results.getString("BusinessId");
        String userName = results.getString("UserName");

        User user = userDao.getUserByUserName(userName);
        YelpBusiness business = yelpBusinessDao.getYelpBusinessById(resultBusinessId);

        Recommendation recommendation = new Recommendation(recommendationId, business, user);
        recommendations.add(recommendation);
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

    return recommendations;
  }

  public Recommendation delete(Recommendation recommendation) throws SQLException {
    String deleteRecommendation = "DELETE FROM Recommendation WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecommendation);
      deleteStmt.setInt(1, recommendation.getRecommendationId());
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
