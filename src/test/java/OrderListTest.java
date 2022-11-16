import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public class OrderListTest {

    @Test
    @DisplayName("Check that list of orders isn't empty")
    public void OrderListIsNotNull() {
        Order order = OrderGenerator.getDefaultOrderWithoutColor();
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.become().assertThat().body("isEmpty()", Matchers.is(false));
    }
}
