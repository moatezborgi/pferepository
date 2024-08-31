package tn.ey.healthsystem.eureka.ccmapplication.Controllers.EquipementsManagementController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryFieldDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.EquipmentDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDetail;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDto;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.*;
import tn.ey.healthsystem.eureka.ccmapplication.service.equipementservice.EquipementInterface;

import java.util.List;

@RestController
@RequestMapping("/EquipementController")
@RequiredArgsConstructor
@Slf4j
public class EquipementsManagementController {
    private final EquipementInterface equipementInterface;
    @GetMapping("/hello")
     public String hello(HttpServletRequest request)
    {
         return "Hello World";
    }
    @PostMapping("/addNewCategory")
    public Category addNewCategory(@RequestBody CategoryDto categoryDto){
        return equipementInterface.addNewCategory(categoryDto);
    }
    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories()
    {
        return equipementInterface.getAllCategories();
    }

    @GetMapping("/getAllEquipmentsAnnuaire")
    public List<EquipmentDto> getAllEquipmentsAnnuaire()
    {
        return equipementInterface.getAllEquipmentsAnnuaire();
    }
    @PostMapping("/addEquipement")
    public Equipment addEquipement(@RequestBody EquipmentDto equipmentDto)
    {
        return equipementInterface.addEquipement(equipmentDto);
    }

    @GetMapping("/getAllMarques")
    public List<Marque> getAllMarques()
    {
        return equipementInterface.getAllMarques();
    }
    @GetMapping("/getCategoryFieldOfSpeceficCategory/{categoryId}")
    public List<CategoryFieldDto> getCategoryFieldOfSpeceficCategory(@PathVariable String categoryId)
    {
        return equipementInterface.getCategoryFieldOfSpeceficCategory(categoryId);
    }


    @GetMapping("/disableEquipementAnnuaire/{equipementId}")
    public boolean disableEquipementAnnuaire(@PathVariable String equipementId)
    {
        return equipementInterface.disableEquipementAnnuaire(equipementId);
    }

    @GetMapping("/enableEquipementAnnuaire/{equipementId}")
    public boolean enableEquipementAnnuaire(@PathVariable String equipementId)
    {
        return equipementInterface.enableEquipementAnnuaire(equipementId);
    }

    @PostMapping("/addMaintenance/{equipementId}")
    public Maintenance addMaintenance(@RequestBody MaintenanceDto maintenanceDto,@PathVariable String equipementId)
    {
        return equipementInterface.addMaintenance(maintenanceDto, equipementId);
    }
    @GetMapping("/getEquipementDetails/{equipementId}")
    public EquipmentDto getEquipementDetails(@PathVariable String equipementId)
    {
        return equipementInterface.getEquipementDetails(equipementId);
    }

    @PostMapping("/assignEquipementToStructure/{structureId}")
    public boolean assignEquipementToStructure(@RequestBody EquipementStructure equipementStructure, @PathVariable String structureId)
    {
        return equipementInterface.assignEquipementToStructure(equipementStructure,structureId);
    }

    @GetMapping("/getAllEquipmentsAnnuairebyStructure/{structureId}")
    public List<EquipmentDto> getAllEquipmentsAnnuairebyStructure(@PathVariable String structureId)
    {
        return equipementInterface.getAllEquipmentsAnnuairebyStructure(structureId);
    }
    @GetMapping("/disableequipementstructure/{equipementId}/{structureId}")
    public boolean disableequipementstructure(@PathVariable String equipementId,@PathVariable String structureId)
    {
        return equipementInterface.disableequipementstructure(equipementId,structureId);
    }
    @GetMapping("/enableequipementstructure/{equipementId}/{structureId}")
    public boolean enableequipementstructure(@PathVariable String equipementId,@PathVariable String structureId)
    {
        return equipementInterface.enableequipementstructure(equipementId,structureId);
    }

    @GetMapping("/getMaintenanceForStructure/{structureId}")
    public List<MaintenanceDetail> getMaintenanceForStructure(@PathVariable String structureId)
    {
        return equipementInterface.getMaintenanceForStructure(structureId);
    }

    @GetMapping("/addtemperature/{temperature}")
    public EquipementStructureTemperature addtemperature(@PathVariable String temperature)
    {
        return equipementInterface.addtemperature(temperature);
    }


    @GetMapping("/getemperatureequipement/{idEquipement}/{idStructure}/{inventairenumber}")
    public List<EquipementStructureTemperature> getemperatureequipement(@PathVariable String idEquipement,@PathVariable String idStructure,@PathVariable String inventairenumber) {

        {
        return equipementInterface.getemperatureequipement(idEquipement,idStructure,inventairenumber);}
    }
    }
