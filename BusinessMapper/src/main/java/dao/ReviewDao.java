package dao;

import model.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {

  protected ConnectionManager connectionManager;

  private static ReviewDao instance = null;

  protected ReviewDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReviewDao getInstance() {
    if (instance == null) {
      instance = new ReviewDao();
    }
    return instance;
  }

  public Review create(Review review) throws SQLException {
    String insertReview =
        "INSERT INTO Review(Rating,Content,Created,UserName,BusinessId) " +
            "VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setBigDecimal(1, review.getRating());
      insertStmt.setString(2, review.getContent());
      insertStmt.setTimestamp(3, review.getCreated());
      insertStmt.setString(4, review.getUser().getUserName());
      insertStmt.setString(5, review.getBusiness().getBusinessId());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int reviewId = -1;
      if (resultKey.next()) {
        reviewId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }

      review.setReviewId(reviewId);
      return review;
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

  public Review getReviewById(int reviewId) throws SQLException {
    String selectReview =
        "SELECT ReviewId,Rating,Content,Created,UserName,BusinessId " +
            "FROM Review " +
            "WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, reviewId);

      results = selectStmt.executeQuery();
      UserDao userDao = UserDao.getInstance();
      YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();

      if (results.next()) {
        int resultReviewId = results.getInt("ReviewId");
        BigDecimal rating = results.getBigDecimal("Rating");
        String content = results.getString("Content");
        Timestamp created = results.getTimestamp("Created");
        String userName = results.getString("UserName");
        String businessId = results.getString("BusinessId");

        User user = userDao.getUserByUserName(userName);
        YelpBusiness yelpBusiness = yelpBusinessDao.getYelpBusinessById(businessId);

        Review review = new Review(resultReviewId, rating, content, created, user, yelpBusiness);
        return review;
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

  public List<Review> getReviewsByBusinessId(String businessId) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    String selectReviews =
        "SELECT ReviewId,Rating,Content,Created,UserName,BusinessId " +
            "FROM Review " +
            "WHERE BusinessId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReviews);
      selectStmt.setString(1, businessId);
      results = selectStmt.executeQuery();

      UserDao userDao = UserDao.getInstance();
      YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();

      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        BigDecimal rating = results.getBigDecimal("Rating");
        String content = results.getString("Content");
        Timestamp created = results.getTimestamp("Created");
        String userName = results.getString("UserName");
        String resultBusinessId = results.getString("BusinessId");

        User user = userDao.getUserByUserName(userName);
        YelpBusiness business = yelpBusinessDao.getYelpBusinessById(resultBusinessId);

        Review review = new Review(reviewId, rating, content, created, user, business);
        reviews.add(review);
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
    return reviews;
  }

  public Review updateReviewContent(Review review, String newContent) throws SQLException {
    String updateReview =
        "UPDATE Review SET Content=? WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateReview);
      updateStmt.setString(1, newContent);
      updateStmt.setInt(2, review.getReviewId());

      updateStmt.executeUpdate();

      review.setContent(newContent);
      return review;
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

  public Review delete(Review review) throws SQLException {
    String deleteReview = "DELETE FROM Review WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, review.getReviewId());

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
