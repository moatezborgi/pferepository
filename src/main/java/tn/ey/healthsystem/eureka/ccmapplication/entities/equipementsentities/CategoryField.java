package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class CategoryField implements Serializable {
    @Id
    private String categoryFieldId;
    private String categoryFieldlabel; // Libellé du champ
    private String categoryFieldtype;  // Type du champ (texte, numérique, etc.)
    private Boolean categoryFieldisNumeric; // Indique si le champ est numérique
    private Boolean categoryFieldisAlphanumeric; // Indique si le champ est alphanumérique
    private Boolean isAlpha;
    private Integer categoryFieldlength; // Longueur maximale
    private Integer categoryFieldminValue; // Valeur minimale (pour les champs numériques)
    private Integer categoryFieldmaxValue; // Valeur maximale (pour les champs numériques)
    @ManyToOne
    @JsonIgnore
    private Category category; // La catégorie à laquelle ce champ appartient
    @OneToMany(mappedBy = "categoryField")
    @JsonIgnore
    private List<EquipmentValue> equipmentValue;
}
