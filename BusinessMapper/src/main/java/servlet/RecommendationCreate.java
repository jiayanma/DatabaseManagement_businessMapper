package servlet;

import dao.*;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recommendationcreate")
public class RecommendationCreate extends HttpServlet {

  protected RecommendationDao recommendationDao;

  @Override
  public void init() throws ServletException {
    recommendationDao = RecommendationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String businessid = req.getParameter("businessid");
    String username = req.getParameter("username");
    if (businessid == null || businessid.trim().isEmpty()) {
      messages.put("success", "Invalid BusinessId!");
    } else if (username == null || username.trim().isEmpty()) {
      messages.put("success", "Invalid UserName!");
    } else {
      UserDao userDao = UserDao.getInstance();
      YelpBusinessDao yelpBusinessDao = YelpBusinessDao.getInstance();
      try {
        User user = userDao.getUserByUserName(username);
        YelpBusiness business = yelpBusinessDao.getYelpBusinessById(businessid);

        Recommendation recommendation = new Recommendation(business, user);
        recommendation = recommendationDao.create(recommendation);
        messages.put("success",
            "Successfully created recommendation " + recommendation.getRecommendationId());
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/RecommendationCreate.jsp").forward(req, resp);
  }
}
