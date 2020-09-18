package cal.barbermanager.dto;

import cal.barbermanager.validator.UnregisteredEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {


    /**
     * Email
     */
    @Email(message = "Email is not valid")
    @UnregisteredEmail(message = "Email is already in use")
    private String email;

    /**
     * Password
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 18, message = "Password size must be between 6 and 18")
    private String password;

    /**
     * First Name
     */
    @NotBlank(message = "First name is mandatory")
    private String firstName;


    /**
     * Last Name
     */
    @NotBlank(message = "First name is manadatory")
    private String lastName;

}
