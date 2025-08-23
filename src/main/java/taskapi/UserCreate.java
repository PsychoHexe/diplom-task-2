package taskapi;

public class UserCreate extends UserLogin {
    private String name;

    // конструктор класса
    public UserCreate(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public UserCreate(UserCreate userCreate) {
        super(userCreate);
        this.name = userCreate.name;
    }

    

    public UserLogin getLogin() {
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
