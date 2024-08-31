package tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryFieldDto {
    private String fieldId;
    private String label;
    private String type;
    private Boolean isNumeric;
    private Boolean isAlphanumeric;
    private Boolean isAlpha;
    private Integer length;
    private Integer minValue;
    private Integer maxValue;
}
