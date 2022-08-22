package dao;

import model.RegionHouseRental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionHouseRentalDao {
    protected ConnectionManager connectionManager;

    private static RegionHouseRentalDao instance = null;

    protected RegionHouseRentalDao() {
        connectionManager = new ConnectionManager();
    }

    public static RegionHouseRentalDao getInstance() {
        if (instance == null) {
            instance = new RegionHouseRentalDao();
        }
        return instance;
    }

    public RegionHouseRental create(RegionHouseRental regionHouseRental) throws SQLException {
        String insertRegion = "INSERT INTO RegionHouseRental(ZipCode, Rental2020, Rental2021, Rental2022) RentalS(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRegion);
            insertStmt.setString(1, regionHouseRental.getZipCode());
            insertStmt.setDouble(2, regionHouseRental.getRental2020());
            insertStmt.setDouble(3, regionHouseRental.getRental2021());
            insertStmt.setDouble(4, regionHouseRental.getRental2022());
            insertStmt.executeUpdate();
            return regionHouseRental;
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
        }
    }
    public RegionHouseRental getRegionHouseRentalByZipCode(String zipcode) throws SQLException{
        String selectRegion = "SELECT ZipCode, Rental2020, Rental2021, Rental2022 FROM RegionHouseRental WHERE ZipCode=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRegion);
            selectStmt.setString(1, zipcode);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String ZipCode = results.getString("ZipCode");
                Double Rental20 = results.getDouble("Rental2020");
                Double Rental21 = results.getDouble("Rental2021");
                Double Rental22 = results.getDouble("Rental2022");
                RegionHouseRental regionHouseRental = new RegionHouseRental(ZipCode, Rental20, Rental21, Rental22);
                return regionHouseRental;
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

    public RegionHouseRental delete(RegionHouseRental regionHouseRental) throws SQLException {
        String deleteYelpBusiness = "DELETE FROM RegionHouseRental WHERE ZipCode=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteYelpBusiness);
            deleteStmt.setString(1, regionHouseRental.getZipCode());

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
