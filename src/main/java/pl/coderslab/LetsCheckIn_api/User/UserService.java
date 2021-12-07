package pl.coderslab.LetsCheckIn_api.User;

public interface UserService {

    User findByUserName(String username);
    void saveUser(User user);
}
