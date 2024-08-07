package ra.rekkei.demo_session_cookie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotBlank(message = "Chưa nhập username")
    private String username;
    @NotBlank(message = "Chưa nhập password")
    private String password;
}
