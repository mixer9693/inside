package ru.inside.demo.command;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.inside.demo.entity.User;
import ru.inside.demo.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserCommand implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserCommand.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String username = "john";
        String password = "1234";

        Optional<User> userOptional = userRepository.findByName(username);

        if (userOptional.isEmpty()){
            User user = new User();
            user.setName("john");
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            LOGGER.info("User with name \"{}\" and password \"{}\" created", username, password);
        } else {
            LOGGER.info("There is a user with name \"{}\" and password \"{}\"",username, password);
        }
    }
}
