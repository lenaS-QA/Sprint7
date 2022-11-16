import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class NegativeCourierTest {

    @Test
    @DisplayName("Check status code of double courier creation")
    public void DoubleCourierCreation() {
        Courier courier1 = new Courier("grdjk", "34542", "ygfhg");
        Courier courier2 = new Courier("grdjk", "34542", "ygfhg");
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response1 = courierClient.create(courier1);
        ValidatableResponse response2 = courierClient.create(courier2);
        int statusCode = response2.extract().statusCode();
        Assert.assertEquals("You shouldn't can create two identical couriers, but you can", 409, statusCode);
    }
    @Test
    @DisplayName("Check message of courier creation with existing login")
    public void DoubleLoginCourierCreationMessage() {
        Courier courier1 = new Courier("J", "1234", "Jason");
        Courier courier2 = new Courier("J", "5678", "Jackson");
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response1 = courierClient.create(courier1);
        ValidatableResponse response2 = courierClient.create(courier2);
        String message = response2.extract().path("message");
        Assert.assertEquals("The message isn't right", "Этот логин уже используется", message);
    }
    @Test
    @DisplayName("Check message of courier creation without login")
    public void CreateCourierWithoutLogin() {
        Courier courier = CourierGenerator.getDefault();
        courier.setLogin(null);
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.create(courier);
        String message = response.extract().path("message");
        Assert.assertEquals("You shouldn't can create courier without login, but you can", "Недостаточно данных для создания учетной записи", message);
    }
    @Test
    @DisplayName("Check message of courier creation without password")
    public void CreateCourierWithoutPassword() {
        Courier courier = CourierGenerator.getDefault();
        courier.setPassword(null);
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.create(courier);
        String message = response.extract().path("message");
        Assert.assertEquals("You shouldn't can create courier without password, but you can", "Недостаточно данных для создания учетной записи", message);
    }
    @Test
    @DisplayName("Check message of courier creation without firstName")
    public void CreateCourierWithoutFirstName() {
        Courier courier = CourierGenerator.getDefault();
        courier.setFirstName(null);
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.create(courier);
        String message = response.extract().path("message");
        Assert.assertEquals("You shouldn't can create courier without firstName, but you can", "Недостаточно данных для создания учетной записи", message);
    }
}
