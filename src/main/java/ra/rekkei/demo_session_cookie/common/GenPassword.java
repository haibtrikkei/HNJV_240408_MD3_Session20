package ra.rekkei.demo_session_cookie.common;

import org.mindrot.jbcrypt.BCrypt;

public class GenPassword {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("12345",BCrypt.gensalt(12)));
        System.out.println(BCrypt.checkpw("12345","$2a$12$fSkeDz6rKGvnvKFUZqd4desEIhERYbZZ3ZRTLS.iiRmFMwAeuJ0JS"));
    }
}
