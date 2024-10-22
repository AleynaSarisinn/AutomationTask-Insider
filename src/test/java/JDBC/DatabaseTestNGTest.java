package JDBC;

import JDBC.utils.BaseTests;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DatabaseTestNGTest extends BaseTests {

    @Test
    public void testInsertData() throws SQLException {
        String insertSQL = "INSERT INTO employees (name, department) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, "Jane Doe");
            statement.setString(2, "Finance");
            int rowsInserted = statement.executeUpdate();
            assertEquals(1, rowsInserted);
        }

        String selectSQL = "SELECT name, department FROM employees WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, "Jane Doe");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String department = resultSet.getString("department");
                    assertEquals("Jane Doe", name);
                    assertEquals("Finance", department);
                }
            }
        }
    }

    @Test(dataProvider = "employeeData")
    public void testInsertDataWDD(String name, String department) throws SQLException {
        String insertSQL = "INSERT INTO employees (name, department) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, name);
            statement.setString(2, department);
            int rowsInserted = statement.executeUpdate();
            assertEquals(1, rowsInserted);
        }

        String selectSQL = "SELECT name, department FROM employees WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, name);  // name parametresi
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String fetchedName = resultSet.getString("name");
                    String fetchedDepartment = resultSet.getString("department");
                    assertEquals(name, fetchedName);  // name doğrulaması
                    assertEquals(department, fetchedDepartment);  // department doğrulaması
                }
            }
        }
    }
}
