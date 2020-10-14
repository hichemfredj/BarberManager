package cal.barbermanager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ReservationDTO {

    @NotBlank(message="uniqueid can't be blank")
    private UUID uniqueId;

    @NotBlank(message="employer id can't be blank")
    private UUID employer;

    @NotBlank(message="client id can't be blank")
    private UUID client;

    @NotBlank(message="date can't be blank")
    private String date;

    @NotBlank(message="time can't be blank")
    private String time;

    @NotBlank(message="client name can't be blank")
    private String clientName;

    @NotBlank(message="barber name can't be blank")
    private String barberName;


}
