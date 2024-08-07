package ra.rekkei.demo_session_cookie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "username",length = 100, unique = true, nullable = false)
    private String username;
    @Column(name = "password",length = 100, nullable = false)
    private String password;
    @Column(name = "full_name", length = 50)
    private String fullName;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Roles> roles;
}
