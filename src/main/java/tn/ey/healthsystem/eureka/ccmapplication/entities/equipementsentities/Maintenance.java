package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance implements Serializable {
    @Id
private String idMaintenance;
private String libelleMaintenance;
private int nbdaysaftermiseenservice;
@ManyToOne
private Equipment equipment;


}
