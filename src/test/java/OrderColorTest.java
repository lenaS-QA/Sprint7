import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)

public class OrderColorTest {

    private OrderClient orderClient;
    private static Order order;
    private List <String> color;

    public OrderColorTest(Order order, List <String> color) {
        this.order = order;
        this.color = color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
        order = OrderGenerator.getDefaultOrderWithoutColor();
    }


    @Parameterized.Parameters
    public static Object [][] getTestData() {
        return new Object[][] {
                {order, List.of()},
                {order, List.of("BLACK")},
                {order, List.of("GREY")},
                {order, List.of("GREY", "BLACK")}
        };
    }

    @Test
    @DisplayName("Check order creation possibility with different colors")
    public void OrderCanBeCreatedRegardlessOfColor() {
        order.setColor(List.of());
        ValidatableResponse response = orderClient.create(order);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(201, statusCode);
        int track = response.extract().path("track");
        orderClient.cancelOrder(track);
    }

}
