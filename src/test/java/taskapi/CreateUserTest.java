package taskapi;
import org.hamcrest.Matchers;
import org.junit.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import taskapi.data.UserData;

public class CreateUserTest extends BaseUserForTest{
        
    //создать уникального пользователя;
    @Step("Проверка юзер создан")
    public void stepCheckUserCreate(Response response) {
        response.then().assertThat().statusCode(200)
        .and()
        .body("success",Matchers.equalTo(true));
    }
    
    //создать пользователя, который уже зарегистрирован;
    @Step("Проверка создания юзера с одинаковой почтой")
    private void stepCheckDubleSame(Response response) {
        response.then().assertThat()
                .statusCode(403)
                .and().body(Matchers.containsString("User already exists"));
    }

    //создать пользователя и не заполнить одно из обязательных полей.
    @Step("Проверка на ответ при создании с невалидными данными")
    private void checkCreateNegative(Response respons) {
        respons.then().assertThat()
                .statusCode(403)
                .and().body(Matchers.containsString("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание юзера (позитивный тест)")
    @Description("ручка для создания юзера /api/auth/register")
    public void newUserCreatePositiveTest() {

        UserAuth user = new UserAuth(UserData.POSITIV_CREATE_LIST.get(0)) ;
        deleteList.add(user);

        Response response = api.registerUser(user.getCreate());

        stepCheckUserCreate(response);
    }

    @Test
    @DisplayName("Создание юзера, который уже зарегистрирован(негативный тест)")
    @Description("ручка для создания юзера /api/auth/register")
    public void newUserCreateSameTest() {

        UserAuth user1 = new UserAuth(UserData.POSITIV_CREATE_LIST.get(0));
        deleteList.add(user1);
        Response response1 = api.registerUser(user1);

        UserAuth user2 = new UserAuth(UserData.POSITIV_CREATE_LIST.get(1));
        deleteList.add(user2);
        Response response2 = api.registerUser(user2);
        
        stepCheckDubleSame(response2);

    }

    @Test
    @DisplayName("Создание пользователя (негативный тест)")
    @Description("ручка для создания пользователя /api/auth/register с отсутсвующим логином")
    public void newUserCreateWithoutLoginNegativeTest() {
        UserCreate user = UserData.NEGATIVE_CREATE_LIST.get(0);
        Response response = api.registerUser(user);
        checkCreateNegative(response);
    }

    @Test
    @DisplayName("Создание пользователя (негативный тест)")
    @Description("ручка для создания пользователя /api/auth/register с отсутсвующим паролем")
    public void newUserCreateWithoutPasswordNegativeTest() {

        UserCreate userCreate = UserData.NEGATIVE_CREATE_LIST.get(1);
        Response response = api.registerUser(userCreate);
        checkCreateNegative(response);

    }
}
