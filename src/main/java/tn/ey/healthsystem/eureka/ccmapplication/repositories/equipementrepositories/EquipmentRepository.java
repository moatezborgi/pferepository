package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment,String> {
    @Query("""
select e from Equipment e,EquipementStructure es where
es.idEquipement=e.equipmentId and es.idStructure=:structureId
""")
    List<Equipment> JPQL_GetStructureEquipement(String structureId);

    @Query("""
SELECT e
FROM Equipment e,Maintenance m , EquipementStructure es where

 
es.idStructure=:structureId
and
es.idEquipement=e.equipmentId
and
es.isActiveEquipement=true
and
m.equipment=e

""")
    List<Equipment> JPQL_GetEquipementByStructureId(String structureId);
}
