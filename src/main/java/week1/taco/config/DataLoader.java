package week1.taco.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import week1.taco.models.User;
import week1.taco.repository.UserRepository;

@Component
public class DataLoader {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadUsers() {
        if (userRepo.count() == 0) {
            User u = new User();
            u.setUsername("user");
            u.setPassword(passwordEncoder.encode("password"));
            u.setRoles("ROLE_USER");
            userRepo.save(u);
        }
    }
}