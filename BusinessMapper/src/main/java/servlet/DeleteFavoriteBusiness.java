package servlet;

import dao.FavoriteBusinessDao;
import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FavoriteBusiness;
import model.User;

@WebServlet("/deletefavoritebusiness")
public class DeleteFavoriteBusiness extends HttpServlet {

  protected FavoriteBusinessDao favoriteBusinessDao;
  protected UserDao userDao;

  @Override
  public void init() throws ServletException {
    favoriteBusinessDao = FavoriteBusinessDao.getInstance();
    userDao = UserDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String userName = req.getParameter("userName");
    try {
      User loginUser = userDao.getUserByUserName(userName);
      req.setAttribute("loginUser", loginUser);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Integer favoriteId = Integer.valueOf(req.getParameter("favoriteid"));
    try {
      FavoriteBusiness favoriteBusiness = favoriteBusinessDao.getFavoriteBusinessById(favoriteId);
      favoriteBusinessDao.delete(favoriteBusiness);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    req.getRequestDispatcher("/showfavoritebusiness").forward(req, resp);
  }
}
