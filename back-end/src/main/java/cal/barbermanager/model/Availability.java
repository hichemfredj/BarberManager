package cal.barbermanager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Data
public class Availability {

    @Id
    private UUID uniqueId;

    private UUID employer;

    private String day;
    private String startTime;
    private String endTime;

    private boolean isAvailable;


}
