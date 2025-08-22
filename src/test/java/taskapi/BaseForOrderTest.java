package taskapi;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class BaseForOrderTest extends BaseTest{

    UserCreateApi apiUser = new UserCreateApi();;

    @Before
    public void initApi() {
        apiUser = new UserCreateApi();
    }

    @After
    public void deleteCouriers() {
        for (UserAuth user : deleteList) {
            Response logResponse = apiUser.loginUser(user);
            if (logResponse.then().extract().statusCode() == 200)
                apiUser.deleteUser(user);
        }
    }

    // Список на удаление
    public List<UserAuth> deleteList = new ArrayList<>();

    public final Faker faker = new Faker();
    public UserAuth createUser(){
        String pass = "qwert1234"; // faker.internet().password(8, 16, true, false, true);
        UserAuth user = new UserAuth(faker.internet().emailAddress(), pass, faker.name().fullName(), "", "");        
        deleteList.add(user);
        return user;
    }

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
