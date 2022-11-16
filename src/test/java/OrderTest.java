import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.hamcrest.Matchers.hasKey;

public class OrderTest {
    @Test
    @DisplayName("Check that response body, when you create order, extends track")
    public void OrderResponseBodyExtendsTrack() {
        Order order = OrderGenerator.getDefaultOrderWithoutColor();
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.create(order).assertThat().body("$", hasKey("track"));
    }
}
