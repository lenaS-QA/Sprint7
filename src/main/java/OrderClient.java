import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    private static final String ORDER_CREATION_PATH = "/api/v1/orders";
    private static final String ORDER_CANCEL_PATH = "/api/v1/orders/cancel?track=";
    private static final String ORDER_LIST_PATH = "/api/v1/orders";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_CREATION_PATH)
                .then();
    }

    public void cancelOrder (Integer track) {
        String json = "{\"track\": "+ track + "}";
        String CANCEL_FULL_PATH = ORDER_CANCEL_PATH + track;
        ValidatableResponse cancel = given()
                .spec(getSpec())
                .log().all()
                .body(json)
                .when()
                .put(CANCEL_FULL_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse become() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_LIST_PATH)
                .then();
    }
}
