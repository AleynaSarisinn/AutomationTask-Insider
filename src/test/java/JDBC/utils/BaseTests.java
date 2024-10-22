package JDBC.utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseTests {

    protected PropertiesReader propertiesReader;
    protected Connection connection;

    @BeforeClass
    public void setUpProperties() {
        propertiesReader = new PropertiesReader();  // PropertiesReader sınıfını başlatıyorsun
    }

    @BeforeMethod
    public void setUp() throws SQLException, IOException {
        connection = DatabaseUtils.getConnection();
    }

    @AfterMethod
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {
        return new Object[][]{
                {propertiesReader.getProperty("employee1.name"), propertiesReader.getProperty("employee1.department")},
                {propertiesReader.getProperty("employee2.name"), propertiesReader.getProperty("employee2.department")}
        };
    }
}
