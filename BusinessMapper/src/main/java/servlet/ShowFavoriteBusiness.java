package servlet;


import dao.FavoriteBusinessDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FavoriteBusiness;


@WebServlet("/showfavoritebusiness")
public class ShowFavoriteBusiness extends HttpServlet {

  protected FavoriteBusinessDao favoriteBusinessDao;

  @Override
  public void init() throws ServletException {
    favoriteBusinessDao = FavoriteBusinessDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    HttpSession session = req.getSession(true);
    String userName = (String) session.getAttribute("username");
    req.setAttribute("messages", messages);

    if (userName == null || userName.trim().isEmpty()) {
      messages.put("title", "Invalid username.");
    } else {
      messages.put("title", "Favorite Business for " + userName);
    }

    List<FavoriteBusiness> favoriteBusinessList = new ArrayList<>();
    try {
      favoriteBusinessList = favoriteBusinessDao.getFavoriteBusinessByUserName(userName);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("favoriteBusinessList", favoriteBusinessList);
    req.getRequestDispatcher("/ShowFavoriteBusiness.jsp").forward(req, resp);
  }
}
