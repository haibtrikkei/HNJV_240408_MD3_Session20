package ra.rekkei.demo_session_cookie.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.rekkei.demo_session_cookie.model.entity.Users;
import ra.rekkei.demo_session_cookie.repository.UserRepository;
import ra.rekkei.demo_session_cookie.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
