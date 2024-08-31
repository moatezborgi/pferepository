package tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto;

import lombok.*;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryDto;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.EquipmentValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentDto {

    private String equipmentId;
    private String equipmentName;
    private String equipmentDescription;
    private boolean isPqsEquipement;
    private String equipmentType;
    private LocalDateTime addedDate;
    private String model;
    private String marque;
    private CategoryDto equipmentCategory;
    private List<EquipmentValueDto> equipmentValueDtos; // Valeurs associées à l'équipement
    private Boolean isActiveEquipement;
    private List<MaintenanceDto> maintenanceDtos;
    private LocalDate dateMiseEnservice;
    private LocalDate dataachat;
    private String serialNumber;
    private String inventairenumber;
    private int tempmax;
    private int tempmin;

}
