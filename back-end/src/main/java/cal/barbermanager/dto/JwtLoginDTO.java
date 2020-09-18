package cal.barbermanager.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtLoginDTO {

    /**
     * Associated authenticated user unique id
     * <p>
     * Redundant since the token contains the uuid. However,
     * this makes it easier to retrieve the uuid since the token
     * doesn't need to be decoded.
     */
    private UUID userUniqueId;

    /**
     * Generated jwt authentication token
     */
    private String token;
}
