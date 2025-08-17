package taskapi;

public class UserAuth extends UserCreate {
    private String refreshToken; //токен для разлогирования
    private String accessToken; //токен для логирования

    // конструктор класса
    public UserAuth(String email, String password, String name, String token, String refToken) {
        super(email, password, name);
        this.accessToken = token;
        this.refreshToken = refToken;
    }

    // конструктор класса
    public UserAuth(UserCreate userCreate, String token, String refToken) {
        super(userCreate);
        this.accessToken = token;
        this.refreshToken = refToken;
    }

    public UserAuth(UserCreate userCreate) {
        super(userCreate);
    }

    public UserCreate getCreate() {
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    
 
}
