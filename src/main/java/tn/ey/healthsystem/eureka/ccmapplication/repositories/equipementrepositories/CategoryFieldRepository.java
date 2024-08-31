package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.CategoryField;

public interface CategoryFieldRepository extends JpaRepository<CategoryField, String> {
}
