package taskapi;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class OrderCreateApi {

    public List<Ingredient> ingBuns=new ArrayList<>(); //Список булок
    public List<Ingredient> ingMains=new ArrayList<>(); //Список основных ингридиентов
    public List<Ingredient> ingSauce=new ArrayList<>(); //Список соусов
    
    @Step("Загружаем список ингридиентов")
    public int loadIngredients() {
        Response ingResponse = given()
                .header("Content-type", "application/json")
                .when()
                .get(ListOfConstants.INGREDIENTS);
        List<Ingredient> ings = ingResponse.body().jsonPath().getList("data", Ingredient.class);
        
        for (Ingredient elem : ings) {
            switch (elem.type) {
                case "bun": ingBuns.add(elem); break;
                case "main": ingMains.add(elem); break;
                case "sauce": ingSauce.add(elem); break;
            }
        }
        return ings.size();
    }

    // Получение id ингридиента
    @Step("Запрашиваем id ингридиента")
    public String getListIngredient(UserAuth model) {
        // Response response = loginUser(model);
        // model.setRefreshToken(response.then().extract().path("refreshToken")); // Сохраняем token в объект
        return model.getRefreshToken();
    }

    @Step("Отправляем запрос на создание заказа API")
    public Response createOrder(OrderCreate order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                // .log().all() // Пишет в консоль отправляемый запрос
                .when().post(ListOfConstants.ORDERS);
    }

    @Step("Отправляем запрос на создание заказа API")
    public Response createOrder(OrderCreate order, String userAccessToken) {
        return given()
                .header("Authorization", userAccessToken)
                .header("Content-type", "application/json")
                .body(order)
                // .log().all() // Пишет в консоль отправляемый запрос
                .when().post(ListOfConstants.ORDERS);
    }
}
