package taskapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

public class OrderCreateTest extends BaseForOrderTest {

    /*
     * с авторизацией;
     * без авторизации;
     * с ингредиентами;
     * без ингредиентов;
     * с неверным хешем ингредиентов.
     */

    OrderCreateApi orderApi;
    OrderCreate order;

    /// Создаем заказ со случайным бургером из полученных из api базовых
    /// ингридиентов
    private OrderCreate getOrderBurgerRnd() {
        // Получаем все ингридиенты и сохраняем в OrderApi для использования в запросах
        orderApi.loadIngredients();

        // Собираем случайный бургер из 3 базовых ингридиентов: булочки, основного
        // ингридиента и соуса
        Random rnd = new Random();
        int bunsRnd = rnd.nextInt(orderApi.ingBuns.size());
        int mainRnd = rnd.nextInt(orderApi.ingBuns.size());
        int sauceRnd = rnd.nextInt(orderApi.ingBuns.size());

        // Формируем заказ
        order = new OrderCreate(Arrays.asList(
                orderApi.ingBuns.get(bunsRnd),
                orderApi.ingMains.get(mainRnd),
                orderApi.ingSauce.get(sauceRnd)));
        return order;
    }

    @Step("Проверка заказ создан")
    public void stepCheckOrderCreate(Response response) {
        response.then().assertThat().statusCode(200)
                .and()
                .body("success", Matchers.equalTo(true));
    }

    @Step("Проверка заказ не создан")
    public void stepCheckOrderNotCreate(Response response) {
        response.then().assertThat().statusCode(400)
                .and()
                .body("success", Matchers.equalTo(false));
    }

    @Step("Проверка заказ не создан c неправильным ингридиентом")
    public void stepCheckOrderCreateBadHash(Response response) {
        response.then().assertThat().statusCode(500);
    }

    @Test
    @DisplayName("Заказ от НЕ авторизированного юзера C Ингридиентами")
    @Description("Отправка заказа из случайного бургера без авторизации пользователя")
    public void sendOrderTest() {

        orderApi = new OrderCreateApi();

        getOrderBurgerRnd();

        Response response = orderApi.createOrder(order);

        // Вывод в консоль body ответа от Api
        // System.out.println(orderResponse.asString());
        stepCheckOrderCreate(response);

    }

    @Test
    @DisplayName("Заявка от авторизированного юзера C Ингридиентами")
    @Description("Отправка заказа из случайного бургера без авторизации пользователя")
    public void sendAuthOrderTest() {

        orderApi = new OrderCreateApi();

        getOrderBurgerRnd();

        UserAuth user = createUser();
        
        apiUser.registerUser(user);
        String token = apiUser.getUserAccessToken(user);

        Response response = orderApi.createOrder(order, token);
        // System.out.println(response.asString());

        // Вывод в консоль body ответа от Api
        apiUser.deleteUser(user);
        stepCheckOrderCreate(response);
    }

    @Test
    @DisplayName("Заказ без ингридиентов")
    @Description("Отправка заказа")
    public void sendOrderZeroTest() {

        orderApi = new OrderCreateApi();

        order = new OrderCreate(new ArrayList<>());

        UserAuth user = createUser();
        
        apiUser.registerUser(user);
        String token = apiUser.getUserAccessToken(user);

        Response response = orderApi.createOrder(order, token);

        // Вывод в консоль body ответа от Api
        // System.out.println(orderResponse.asString());
        stepCheckOrderNotCreate(response);

    }

    @Test
    @DisplayName("в запросе передан невалидный хеш ингредиента")
    @Description("запросе передан невалидный хеш ингредиента")
    public void sendOrderBadHashTest() {

        orderApi = new OrderCreateApi();

        order = new OrderCreate(Arrays.asList(new Ingredient("null", "bun")));

        UserAuth user = createUser();

        apiUser.registerUser(user);
        String token = apiUser.getUserAccessToken(user);

        Response response = orderApi.createOrder(order, token);

        // Вывод в консоль body ответа от Api
        // System.out.println(orderResponse.asString());
        stepCheckOrderCreateBadHash(response);

    }

}
