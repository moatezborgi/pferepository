package tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String idCategory;
    private String name;
    private List<CategoryFieldDto> fields;
}
