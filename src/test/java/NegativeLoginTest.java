import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class NegativeLoginTest {

    @Test
    @DisplayName("Check message of courier login without login")
    public void LoginCourierWithoutLogin() {
        Courier courier = CourierGenerator.getDefault();
        CourierClient courierClient = new CourierClient();
        Credentials credentials = new Credentials(null, courier.getPassword());
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String message = responseLogin.extract().path("message");
        Assert.assertEquals("You shouldn't can login without login, but you can", "Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Check message of courier login without password")
    public void LoginCourierWithoutPassword() {
        Courier courier = CourierGenerator.getDefault();
        CourierClient courierClient = new CourierClient();
        Credentials credentials = new Credentials(courier.getLogin(), null);
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String message = responseLogin.extract().path("message");
        Assert.assertEquals("You shouldn't can login without password, but you can", "Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Check message of courier login with wrong login")
    public void LoginCourierWithWrongLogin() {
        Courier courier = CourierGenerator.getDefault();
        CourierClient courierClient = new CourierClient();
        Credentials credentials = new Credentials("wrong", courier.getPassword());
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String message = responseLogin.extract().path("message");
        Assert.assertEquals("You shouldn't can login with wrong login, but you can", "Учетная запись не найдена", message);
    }

    @Test
    @DisplayName("Check message of courier login with wrong password")
    public void LoginCourierWithWrongPassword() {
        Courier courier = CourierGenerator.getDefault();
        CourierClient courierClient = new CourierClient();
        Credentials credentials = new Credentials(courier.getLogin(), "wrong");
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String message = responseLogin.extract().path("message");
        Assert.assertEquals("You shouldn't can login with wrong password, but you can", "Учетная запись не найдена", message);
    }

    @Test
    @DisplayName("Check that not existing courier can't login")
    public void LoginNotExistCourier() {
        CourierClient courierClient = new CourierClient();
        Credentials credentials = new Credentials("wrong", "wrong");
        ValidatableResponse response = courierClient.login(credentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("This courier doesn't exist, but you can login", 404, statusCode);
    }
}
