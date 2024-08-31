package tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.EquipementStructure;

import java.util.List;

public interface EquipementStructureRepository extends JpaRepository<EquipementStructure, Long> {

    @Query("""
select es from EquipementStructure es where es.idStructure=:idStructure and es.idEquipement=:idEquipement
""")
    List<EquipementStructure>JQPL_getEquipementStructure(String  idEquipement,String idStructure);

    @Query("""
update EquipementStructure es set es.isActiveEquipement=false where
es.idEquipement=:idEquipement
and
es.idStructure=:idStructure
""")
    @Modifying
    @Transactional
    void disableequipementstructure(String idEquipement,String idStructure);

    @Query("""
update EquipementStructure es set es.isActiveEquipement=true where
es.idEquipement=:idEquipement
and
es.idStructure=:idStructure
""")
    @Modifying
    @Transactional
    void enableequipementstructure(String idEquipement,String idStructure);
}
