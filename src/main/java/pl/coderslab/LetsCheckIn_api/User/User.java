package pl.coderslab.LetsCheckIn_api.User;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String phone;
    private String email;
    private String password;
    private boolean enabled;

    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Role> roles;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
