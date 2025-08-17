package taskapi;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class UserCreateApi {

    // Создание нового юзера
    @Step("Отправляем запрос на создание юзера API")
    public Response createUser(UserCreate model) {
        return given()
                .header("Content-type", "application/json")
                .body(model)
                .when()
                .post(ListOfConstants.USER_REG);
    }
    
    // Логин юзера
    @Step("Логинимся юзером")
    public Response loginUser(UserCreate model) {
        return given()
                .header("Content-type", "application/json")
                .body(model)
                .when()
                .post(ListOfConstants.USER_LOGIN);
    }

    // Логfen юзера
    @Step("Выходим юзером")
    public Response logoutUser(UserCreate model) {
        return given()
                .header("Content-type", "application/json")
                .body(model.getRefreshToken())
                .when()
                .post(ListOfConstants.USER_LOGOUT);
    }

    // Получение токена для выхода 
    @Step("Запрашиваем токен для разлогирования юзера")
    public String getUserRefreshToken(UserCreate model) {
        Response response = loginUser(model);
        model.setRefreshToken(response.then().extract().path("refreshToken")); // Сохраняем token в объект
        return model.getRefreshToken();
    }

    // Получение токена для получить, обновить и удалить данные
    @Step("Запрашиваем токен для получить, обновить и удалить данные")
    public String getUserAccessToken(UserCreate model) {
        Response response = loginUser(model);
        model.setAccessToken(response.then().extract().path("accessToken")); // Сохраняем token в объект
        return model.getAccessToken();
    }

    // Удаление юзера
    @Step("Удаляем юзера")
    public Response deleteUser(UserCreate model) {
        String userAccessToken = getUserAccessToken(model);
        return given()
                .when()
                .delete(ListOfConstants.USER);
    }
}
