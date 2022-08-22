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
import javax.servlet.http.HttpSession;
import model.FavoriteBusiness;

@WebServlet("/addfavoritebusiness")
public class AddFavoriteBusiness extends HttpServlet {

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

    HttpSession session = req.getSession(true);
    String userName = (String) session.getAttribute("username");

    String businessId = req.getParameter("businessid");
    FavoriteBusiness favoriteBusiness = new FavoriteBusiness(null, userName, businessId);
    try {
      favoriteBusinessDao.create(favoriteBusiness);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.getRequestDispatcher("/showfavoritebusiness").forward(req, resp);
  }

}
