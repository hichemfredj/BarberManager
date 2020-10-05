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

    private Date startDate;
    private Date endDate;

    private String startTime;
    private String endTime;


}
