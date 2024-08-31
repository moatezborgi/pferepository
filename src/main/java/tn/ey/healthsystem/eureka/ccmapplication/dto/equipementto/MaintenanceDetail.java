package tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto;

import lombok.*;

import java.time.LocalDate;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceDetail {

    private String idMaintenance;

    private LocalDate maintenanceDate;
    private String equipmentName;
    private String maintenanceLabel;
}
