package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.Equipment;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.EquipmentValue;

import java.util.List;

public interface EquipmentValueRepository extends JpaRepository<EquipmentValue, Long> {

    List<EquipmentValue> findByEquipment(Equipment equipment);
}
