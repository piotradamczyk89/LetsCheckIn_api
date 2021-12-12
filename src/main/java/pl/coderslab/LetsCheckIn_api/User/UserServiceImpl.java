package pl.coderslab.LetsCheckIn_api.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Role.Role;
import pl.coderslab.LetsCheckIn_api.Role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean a=true;
        user.setEnabled(a);
        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles= new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }


}
