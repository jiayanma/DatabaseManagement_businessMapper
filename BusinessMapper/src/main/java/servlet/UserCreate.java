package servlet;


import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

  protected UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = UserDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    String userName = req.getParameter("UserName");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Invalid UserName");
    } else {
      String UserName = req.getParameter("UserName");
      String Password = req.getParameter("Password");
      String FirstName = req.getParameter("FirstName");
      String LastName = req.getParameter("LastName");
      String Email = req.getParameter("Email");
      String PhoneNumber = req.getParameter("PhoneNumber");

      try {
        User user = new User(UserName, Password, FirstName, LastName, Email, PhoneNumber);
        userDao.create(user);
        messages.put("success", "Successfully created " + userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
  }
}
