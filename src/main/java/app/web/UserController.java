package app.web;

import app.model.UserEntity;
import app.model.UserRole;
import app.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register/client")
    public void registerClient(@RequestParam String username, @RequestParam String password) {
            UserEntity client = new UserEntity(username, bCryptPasswordEncoder.encode(password), UserRole.CLIENT);
            userRepository.save(client);
        }


    @PostMapping("/register/recruiter")
    public void registerRecruiter(@RequestParam String username, @RequestParam String password) {
        UserEntity recruiter = new UserEntity(username, bCryptPasswordEncoder.encode(password), UserRole.HR);
        userRepository.save(recruiter);
    }

    @PostMapping("/register/candidate")
    public void registerCandidate(@RequestParam String username, @RequestParam String password) {
        UserEntity candidate = new UserEntity(username, bCryptPasswordEncoder.encode(password), UserRole.CANDIDATE);
        userRepository.save(candidate);
    }

}
