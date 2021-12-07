package pl.coderslab.LetsCheckIn_api.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final pl.coderslab.LetsCheckIn_api.User.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.LetsCheckIn_api.User.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.coderslab.LetsCheckIn_api.User.User getUser() {
        return user;
    }
}
