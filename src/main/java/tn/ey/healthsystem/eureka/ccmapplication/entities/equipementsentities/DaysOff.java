package tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DaysOff {
    @Id
    private String id;
    private String libelleDay;
    private LocalDate dayoffdate;
}
