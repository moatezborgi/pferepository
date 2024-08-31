package tn.ey.healthsystem.eureka.ccmapplication.service.equipementservice;

import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryFieldDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.EquipmentDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDetail;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDto;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.*;

import java.util.List;

public interface EquipementInterface {


    Category addNewCategory(CategoryDto categoryDto);
   List<CategoryDto> getAllCategories();
   List<EquipmentDto> getAllEquipmentsAnnuaire();
   Equipment addEquipement(EquipmentDto equipmentDto);
   List<Marque> getAllMarques();
    List<CategoryFieldDto> getCategoryFieldOfSpeceficCategory(String categoryId);
    boolean disableEquipementAnnuaire(String equipementId);
    boolean enableEquipementAnnuaire(String equipementId);
    Maintenance addMaintenance(MaintenanceDto maintenanceDto,String equipementId);
    EquipmentDto getEquipementDetails(String equipementId);
    boolean assignEquipementToStructure(EquipementStructure equipementStructure, String structureId);
    List<EquipmentDto> getAllEquipmentsAnnuairebyStructure(String structureId);
    boolean disableequipementstructure(String equipementId,String structureId);
    boolean enableequipementstructure(String equipementId,String structureId);
    public List<MaintenanceDetail> getMaintenanceForStructure(String structureId);

    EquipementStructureTemperature addtemperature(String temperature);

    List<EquipementStructureTemperature> getemperatureequipement(String idEquipement,String idStructure,String inventairenumber);
}
