package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment implements Serializable {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentDescription;
    private boolean isPqsEquipement;
    private String equipmentType;
    private LocalDateTime addedDate;
    private Boolean isActiveEquipement;
    private int tempmax;
    private int tempmin;
    @ManyToOne
    @JsonIgnore
    private Marque marque;
    private String model;
    @ManyToOne
    @JsonIgnore
    private Category category; // Catégorie de l'équipement

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<EquipmentValue> equipmentValueList; // Valeurs associées à l'équipement

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<Maintenance> maintenanceList;
}
