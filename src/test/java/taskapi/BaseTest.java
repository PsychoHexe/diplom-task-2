package taskapi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import io.restassured.RestAssured;

public class BaseTest {

    @Rule
    public final ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setUp() {
        RestAssured.baseURI = ListOfConstants.URL;
    }
}
