package cal.barbermanager.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Reservation {

    @Id
    private UUID uniqueId;

    private UUID client;
    private UUID employer;


    private String date;

    private String time;

    private String barberName;

    private String clientName;






}
