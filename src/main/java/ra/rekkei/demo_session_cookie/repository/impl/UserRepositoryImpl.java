package ra.rekkei.demo_session_cookie.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.rekkei.demo_session_cookie.model.entity.Users;
import ra.rekkei.demo_session_cookie.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        try{
            Users userRepo = (Users) session.createQuery("from Users where username=:username")
                    .setParameter("username", username)
                    .getSingleResult();
            if(BCrypt.checkpw(password,userRepo.getPassword()))
                return userRepo;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
