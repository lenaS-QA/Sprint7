import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    private static final String CREATE_COURIER_PATH = "/api/v1/courier/";
    private static final String LOGIN_COURIER_PATH = "/api/v1/courier/login/";
    private static final String DELETE_COURIER_PATH = "/api/v1/courier/";

    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .log().all()
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpec())
                .log().all()
                .body(credentials)
                .when()
                .post(LOGIN_COURIER_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE_COURIER_PATH + id)
                .then();
    }
}
