package taskapi.data;

import java.util.Arrays;
import java.util.List;

import taskapi.UserCreate;

//Wasd@ya.ru
public class UserData {

    public static final List<UserCreate> NEGATIVE_CREATE_LIST = Arrays.asList(
                new UserCreate("", "1113wasd", "Margo"),
                new UserCreate("Wasd@ya.ru", "", "Margo"),
                new UserCreate("Wasd@ya.ru", "1113", ""));

        public static final List<UserCreate> NEGATIVE_LOGIN_LIST = Arrays.asList(
                        new UserCreate("", "1113wasd", ""),
                        new UserCreate("Wasd@ya.ru", "", ""),
                        new UserCreate("Wasdaasd@ya.ru", "1113wasd", ""),
                        new UserCreate("Wasd@ya.ru", "1112", ""));

        public static final List<UserCreate> POSITIV_CREATE_LIST = Arrays.asList(
                        new UserCreate("Wasd@ya.ru", "1113wasd", "Margo"),
                        new UserCreate("Wasd@ya.ru", "1113wasd", "MargoN"));

}
