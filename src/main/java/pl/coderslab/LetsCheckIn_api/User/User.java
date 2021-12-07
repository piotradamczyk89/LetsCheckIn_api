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
   // private String surname;
    private String phone;
    private String email;
    private String password;

    @OneToMany (fetch = FetchType.EAGER)
    private Set<Role> roles;


}
