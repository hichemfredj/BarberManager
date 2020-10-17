package cal.barbermanager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ReservationDTO {

    private UUID client;

    private UUID employer;

    @NotBlank(message = "DATE IS MANADATORY")
    private String date;

    @NotBlank(message = "TIME IS MANADATORY")
    private String time;

    @NotBlank(message = "BARBER NAME IS MANADATORY")
    private String barberName;

    @NotBlank(message = "CLIENT NAME IS MANADATORY")
    private String clientName;


}
