import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {

    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
    }

    @Test
    @DisplayName("Check status code of courier login")
    public void CourierLoginStatus() {
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int statusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("StatusCode should be 200", 200, statusCode);
    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }
}
