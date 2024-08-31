package tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceDto {
    private String idMaintenance;
    private String libelleMaintenance;
    private int nbdaysaftermiseenservice;
}
