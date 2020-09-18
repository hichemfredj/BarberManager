package cal.barbermanager.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {


    /**
     * Email
     */
    @Email(message="Email is not valid")
    private String email;

    /**
     * Password
     */
    @NotBlank(message = "Password is mandatory")
    private String password;


}
