import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourierTest {

    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
    }
//в этом классе есть аннотация Афтер, удаляющая данные после каждого теста
    @Test
    @DisplayName("Check status code of courier creation")
    public void courierCanBeCreated () {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int statusCode = responseCreate.extract().statusCode();
        Assert.assertEquals("StatusCode should be 201", 201, statusCode);
    }

    @Test
    @DisplayName("Check message of courier creation")
    public void courierSuccessfulCreationStatusIsTrue () {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        boolean statusMessage = responseCreate.extract().path("ok");
        Assert.assertTrue("The message should be true", statusMessage);
    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }
}
