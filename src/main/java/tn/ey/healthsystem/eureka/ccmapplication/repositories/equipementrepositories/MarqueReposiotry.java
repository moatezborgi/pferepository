package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.Marque;

public interface MarqueReposiotry extends JpaRepository<Marque, String> {
}
