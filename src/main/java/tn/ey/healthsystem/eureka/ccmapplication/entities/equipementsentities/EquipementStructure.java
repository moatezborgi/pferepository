package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipementStructure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String idStructure;
    private String serialNumber;
    private String inventairenumber;
    private String idEquipement;
    private boolean isActiveEquipement;
    private LocalDate dateMiseEnservice;
    private LocalDate dataachat;
}
