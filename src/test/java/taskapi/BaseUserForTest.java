package taskapi;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.google.gson.Gson;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;

public class BaseUserForTest extends BaseTest {

    UserCreateApi api;

    // Список на удаление
    public List<UserAuth> deleteList = new ArrayList<>();

    @Attachment(value = "Данные юзера: {caseInfo}", type = "text/*")
    public String userDataAssert(UserCreate model, String caseInfo) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    @Before
    public void initApi() {
        api = new UserCreateApi();
    }

    @After
    public void deleteCouriers() {
        for (UserAuth user : deleteList) {
            Response logResponse = api.loginUser(user);
            if (logResponse.then().extract().statusCode() == 200)
                api.deleteUser(user);
        }
    }

}
