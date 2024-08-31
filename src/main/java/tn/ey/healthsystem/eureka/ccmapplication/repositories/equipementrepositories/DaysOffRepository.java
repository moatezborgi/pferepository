package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.DaysOff;

public interface DaysOffRepository extends JpaRepository<DaysOff, String> {
}
