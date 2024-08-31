package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
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
public class Marque implements Serializable {
    @Id
    private String marqueId;
    private String marqueName;

    @OneToMany(mappedBy = "marque")
    @JsonIgnore
    private List<Equipment> equipmentList;
}
