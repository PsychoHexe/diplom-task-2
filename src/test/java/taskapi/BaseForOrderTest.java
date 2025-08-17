package taskapi;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class BaseForOrderTest extends BaseTest{

    // Вызов списка ингридиентов
    @Step("Отправляем запрос на вывод списка ингридиентов")
    public Response createUser(UserCreate model) {
        return given()
                .header("Content-type", "application/json")
                .body(model)
                .when()
                .post(ListOfConstants.USER_REG);
    }
}
