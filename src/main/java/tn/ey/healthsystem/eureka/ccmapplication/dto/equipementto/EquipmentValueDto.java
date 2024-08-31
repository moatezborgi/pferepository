package tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto;

import lombok.*;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryFieldDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentValueDto {
    private EquipmentDto equipment;
    private CategoryFieldDto categoryFieldDto;
    private String value;
}
