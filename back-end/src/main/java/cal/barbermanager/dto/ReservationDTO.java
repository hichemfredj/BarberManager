package cal.barbermanager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ReservationDTO {


    private String date;

    private String time;

    private String barberName;


}
