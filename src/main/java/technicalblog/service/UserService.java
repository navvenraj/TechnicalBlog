package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.PostRepository;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    /*
    public boolean login(User user) {
        if(user.getUsername().equals("validuser")) {
            return true;
        }
        else {
            return false;
        }


    }

     */
    public User login(User user) {

        User existingUser = repository.checkUser(user.getUsername(), user.getPassword());
        if(existingUser != null) {
            return existingUser;
        }
        else {
            return null;
        }
    }

    public void registerUser(User newUser) {
        repository.registerUser(newUser);
    }
}
