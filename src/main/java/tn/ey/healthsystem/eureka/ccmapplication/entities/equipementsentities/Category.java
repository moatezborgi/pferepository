package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    private String idCategory;
    private String nameCategory;
    private boolean activeCategory;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<CategoryField> fields; // Champs associés à la catégorie
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Equipment> equipmentsList;

}
