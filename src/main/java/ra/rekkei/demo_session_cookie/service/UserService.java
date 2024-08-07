package ra.rekkei.demo_session_cookie.service;

import ra.rekkei.demo_session_cookie.model.entity.Users;

public interface UserService {
    Users findByUsernameAndPassword(String username, String password);
}
