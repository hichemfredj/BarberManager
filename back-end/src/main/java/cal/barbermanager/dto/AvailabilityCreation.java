package cal.barbermanager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AvailabilityCreation implements Serializable {


    @NotBlank(message = "Day is mandatory")
    private String day;

    @NotBlank(message = "Start Hour is mandatory")
    private String startTime;

    @NotBlank(message = "End Hour is mandatory")
    private String endTime;

    private boolean isAvailable;
}
