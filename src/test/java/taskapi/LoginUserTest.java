package taskapi;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import taskapi.data.UserData;

public class LoginUserTest extends BaseUserForTest{

    /*вход под существующим пользователем;
    вход с неверным логином и паролем. */

    UserAuth user;

    @Before
    public void userInitPositive(){
        user = createUser();
        //Добавляем юзера в список на удаление в after 
        deleteList.add(user);
        api.registerUser(user);
    }

    @Step("Проверка наличия refreshToken у юзера")
    private void checkRefTokenUser(Response response) {
        response.then().assertThat()
                .statusCode(200)
                .and().body(Matchers.containsString("refreshToken"));
    }

    @Step("Неверные данные для входа")
    private void checkLoginNotEnter(UserCreate userNegative) {
        Response response = api.loginUser(userNegative);
        response.then().assertThat()
                .statusCode(401)
                .and().body(Matchers.containsString("email or password are incorrect"));
    }



    @Test
    @DisplayName("Логин юзера (позитивный)")
    @Description("Юзер может залогиниться ручка для логина юзера/api/auth/login")
    public void newUserLoginPositiveTest() {
        
        Response response = api.loginUser(user);
        
        checkRefTokenUser(response);        
    }

    @Test
    @DisplayName("Логин юзера (пустой логин)")
    @Description("Юзер не может залогиниться, если данные не верны ручка для логина юзера/api/auth/login")
    public void negativeLoginVoidTest(){
        checkLoginNotEnter(UserData.NEGATIVE_LOGIN_LIST.get(0));
    }

    @Test
    @DisplayName("Логин юзера (пустой пароль)")
    @Description("Юзер не может залогиниться, если данные не верны ручка для логина юзера/api/auth/login")
    public void negativePassVoidTest(){
        checkLoginNotEnter(UserData.NEGATIVE_LOGIN_LIST.get(1));
    }

    @Test
    @DisplayName("Логин юзера (не правильный логин)")
    @Description("Юзер не может залогиниться, если данные не верны ручка для логина юзера/api/auth/login")
    public void negativeLoginVrongTest(){
        checkLoginNotEnter(UserData.NEGATIVE_LOGIN_LIST.get(2));
    }

    @Test
    @DisplayName("Логин юзера (не правильный пароль)")
    @Description("Юзер не может залогиниться, если данные не верны ручка для логина юзера/api/auth/login")
    public void negativePassVrongTest(){
        checkLoginNotEnter(UserData.NEGATIVE_LOGIN_LIST.get(3));
    }



}
