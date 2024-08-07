package ra.rekkei.demo_session_cookie.repository;

import ra.rekkei.demo_session_cookie.model.entity.Users;

public interface UserRepository {
    Users findByUsernameAndPassword(String username, String password);
}
