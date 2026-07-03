package HuyVe.Service;

public class LoginService {
    public boolean login(String username, String password) {
        return "admin".equals(username) && "123".equals(password);
    }
}

