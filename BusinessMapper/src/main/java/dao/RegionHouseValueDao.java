package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.RegionHouseValue;

public class RegionHouseValueDao {
    protected ConnectionManager connectionManager;

    private static RegionHouseValueDao instance = null;

    protected RegionHouseValueDao() {
        connectionManager = new ConnectionManager();
    }

    public static RegionHouseValueDao getInstance() {
        if (instance == null) {
            instance = new RegionHouseValueDao();
        }
        return instance;
    }

    public RegionHouseValue create(RegionHouseValue regionHouseValue) throws SQLException {
        String insertRegion = "INSERT INTO RegionHouseValue(ZipCode, Value2020, Value2021, Value2022) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRegion);
            insertStmt.setString(1, regionHouseValue.getZipCode());
            insertStmt.setDouble(2, regionHouseValue.getValue2020());
            insertStmt.setDouble(3, regionHouseValue.getValue2021());
            insertStmt.setDouble(4, regionHouseValue.getValue2022());
            insertStmt.executeUpdate();
            return regionHouseValue;
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
    public RegionHouseValue getRegionHouseValueByZipCode(String zipcode) throws SQLException{
        String selectRegion = "SELECT ZipCode, Value2020, Value2021, Value2022 FROM RegionHouseValue WHERE ZipCode=?;";
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
                Double value20 = results.getDouble("Value2020");
                Double value21 = results.getDouble("Value2021");
                Double value22 = results.getDouble("Value2022");
                RegionHouseValue regionHouseValue = new RegionHouseValue(ZipCode, value20, value21, value22);
                return regionHouseValue;
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

    public RegionHouseValue delete(RegionHouseValue regionHouseValue) throws SQLException {
        String deleteYelpBusiness = "DELETE FROM RegionHouseValue WHERE ZipCode=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteYelpBusiness);
            deleteStmt.setString(1, regionHouseValue.getZipCode());

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
