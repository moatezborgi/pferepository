package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByActiveCategoryTrue();
}
