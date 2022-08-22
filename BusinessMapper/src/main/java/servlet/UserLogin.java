package servlet;

import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet {

  protected UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = UserDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    HttpSession session = req.getSession(true);
    String userName = req.getParameter("UserName");
    String password = req.getParameter("Password");
    User user;
    try {
      user = userDao.getUserByUserNameAndPassword(userName, password);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    if (user == null) {
      req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
    } else {
      session.setAttribute("username", userName);
      req.getRequestDispatcher("/searchbusiness").forward(req, resp);
    }
  }
}
