package servlet;

import dao.*;
import javax.servlet.http.HttpSession;
import model.*;
import model.YelpBusiness;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.YelpBusinessDao;

@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {

  protected ReviewDao reviewDao;
  protected YelpBusinessDao yelpBusinessDao;

  @Override
  public void init() throws ServletException {
    reviewDao = ReviewDao.getInstance();
    yelpBusinessDao = YelpBusinessDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String businessId = req.getParameter("businessid");
    String businessName  = null;
    try {
      YelpBusiness yelpBusiness = yelpBusinessDao.getYelpBusinessById(businessId);
       businessName = yelpBusiness.getName();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    req.setAttribute("businessid", businessId);
    req.setAttribute("businesname", businessName);
    req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    HttpSession session = req.getSession(true);
    String userName = (String) session.getAttribute("username");
    String rating = req.getParameter("rating");
    if (rating == null || rating.trim().isEmpty()) {
      messages.put("success", "Invalid Rating");
    } else {
      String businessId = req.getParameter("businessid");
      BigDecimal ratingNum = new BigDecimal(rating);
      String content = req.getParameter("content");
      Timestamp createdTs = Timestamp.from(Instant.now());

      try {
        UserDao userDao = UserDao.getInstance();
        YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();
        User user = userDao.getUserByUserName(userName);
        YelpBusiness business = yelpBusinessDao.getYelpBusinessById(businessId);

        Review review = new Review(ratingNum, content, createdTs, user, business);
        review = reviewDao.create(review);
        messages.put("success", "Successfully created review " + review.getReviewId());
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/searchbusiness").forward(req, resp);
  }
}
