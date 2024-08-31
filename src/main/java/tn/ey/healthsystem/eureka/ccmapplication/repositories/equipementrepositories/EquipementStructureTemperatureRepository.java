package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.EquipementStructureTemperature;

import java.util.List;

public interface EquipementStructureTemperatureRepository extends JpaRepository<EquipementStructureTemperature, Long> {

    @Query("""
select est from EquipementStructureTemperature est where
est.idEquipement=:equipementId
and
est.idStructure=:structureId
and
est.inventairenumber=:inventaireId
""")
List<EquipementStructureTemperature> JPQL_findByEquipementStructureId(String structureId,String equipementId,String inventaireId);

}
