package servlet;

import dao.*;
import model.RegionHouseRental;
import model.RegionHouseValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search_region")
public class SearchRegion extends HttpServlet {

  protected RegionHouseValueDao valueDao;
  protected RegionHouseRentalDao rentalDao;

  @Override
  public void init() throws ServletException {
    valueDao = RegionHouseValueDao.getInstance();
    rentalDao = RegionHouseRentalDao.getInstance();
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
    String zipCode = req.getParameter("zipcode");
    RegionHouseValue houseValue = null;
    RegionHouseRental houseRental = null;

    try {
      houseValue = valueDao.getRegionHouseValueByZipCode(zipCode);
      houseRental = rentalDao.getRegionHouseRentalByZipCode(zipCode);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.setAttribute("regionHouseValue", houseValue);
    req.setAttribute("regionHouseRental", houseRental);
    req.setAttribute("zipcode", zipCode);
    req.getRequestDispatcher("/SearchRegion.jsp").forward(req, resp);
  }
}
