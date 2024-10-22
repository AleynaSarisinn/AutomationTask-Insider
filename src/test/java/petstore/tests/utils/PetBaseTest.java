package petstore.tests.utils;

import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import petstore.utils.ConfigReader;

public class PetBaseTest {
    protected static String baseUrl;
    protected ConfigReader configReader = new ConfigReader();

    public PetBaseTest() {
        baseUrl = ConfigReader.getProperty("baseUrl");
    }

    @BeforeClass
    public static void setUp() {
        baseUrl = ConfigReader.getProperty("baseUrl");
    }

    public void verifyHttpStatusCode(Response response, int... expectedStatusCodes) {
        List<Integer> expectedList = Arrays.stream(expectedStatusCodes).boxed().toList();
        Assert.assertTrue("Expected one of the status codes: " + expectedList + " but got: " + response.statusCode(), expectedList.contains(response.statusCode()));
    }


}
