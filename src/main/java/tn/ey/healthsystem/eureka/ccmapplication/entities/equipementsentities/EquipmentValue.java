package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Equipment equipment; // Équipement associé

    @ManyToOne // Changed from @OneToOne to @ManyToOne
     @JsonIgnore
    private CategoryField categoryField; // Champ associé

    private String value; // Valeur saisie pour ce champ

}
