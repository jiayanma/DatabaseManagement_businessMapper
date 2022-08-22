package servlet;

import dao.YelpBusinessDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.YelpBusiness;

@WebServlet("/searchbusiness")
public class SearchBusiness extends HttpServlet {

  protected YelpBusinessDao yelpBusinessDao;

  @Override
  public void init() throws ServletException {
    yelpBusinessDao = YelpBusinessDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    String userName = (String) session.getAttribute("username");
    String zipCode = req.getParameter("zipcode");
    List<YelpBusiness> yelpBusinessList = new ArrayList<>();

    try {
      yelpBusinessList = yelpBusinessDao.getYelpBusinessListByZipCode(zipCode);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String p = req.getParameter("page");
    int page;
    try {
      page = Integer.valueOf(p);
    } catch (NumberFormatException e) {
      page = 1;
    }

    int totalBusiness = yelpBusinessList.size();
    int yelpBusinessPerPage = 10;
    int totalPages = totalBusiness % yelpBusinessPerPage == 0 ? totalBusiness / yelpBusinessPerPage
        : totalBusiness / yelpBusinessPerPage + 1;
    int beginIndex = (page - 1) * yelpBusinessPerPage;
    int endIndex = beginIndex + yelpBusinessPerPage;
    if (endIndex > totalBusiness) {
      endIndex = totalBusiness;
    }
    yelpBusinessList = yelpBusinessList.subList(beginIndex, endIndex);

    req.setAttribute("yelpBusinessList", yelpBusinessList);
    req.setAttribute("zipcode", zipCode);
    req.setAttribute("page", page);
    req.setAttribute("totalPages", totalPages);
    req.getRequestDispatcher("/SearchBusiness.jsp").forward(req, resp);
  }
}
