package tn.ey.healthsystem.eureka.ccmapplication.service.equipementservice;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.categorydto.CategoryFieldDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.EquipmentDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.EquipmentValueDto;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDetail;
import tn.ey.healthsystem.eureka.ccmapplication.dto.equipementto.MaintenanceDto;
import tn.ey.healthsystem.eureka.ccmapplication.entities.equipementsentities.*;
import tn.ey.healthsystem.eureka.ccmapplication.repositories.equipementrepositories.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipementInterfaceImp implements EquipementInterface{
    private final CategoryRepository categoryRepository;
    private final CategoryFieldRepository CategoryFieldRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentValueRepository equipmentValueRepository;
    private final MarqueReposiotry marqueReposiotry;
    private final CategoryFieldRepository categoryFieldRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final EquipementStructureRepository equipementStructureRepository;
    private final DaysOffRepository daysOffRepository;
    private final EquipementStructureTemperatureRepository equipementStructureTemperatureRepository;

    @Override
    public Category addNewCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setIdCategory(UUID.randomUUID().toString());
        category.setNameCategory(categoryDto.getName());
        category.setActiveCategory(true);
        // Enregistrer la catégorie avant d'ajouter les champs
        Category savedCategory = categoryRepository.save(category);
        for (CategoryFieldDto fieldDto : categoryDto.getFields()) {
            CategoryField field = new CategoryField();
            field.setCategoryFieldId(UUID.randomUUID().toString());
            field.setCategoryFieldlabel(fieldDto.getLabel());
            field.setCategoryFieldtype(fieldDto.getType());
            field.setCategoryFieldisNumeric(fieldDto.getIsNumeric());
            field.setCategoryFieldisAlphanumeric(fieldDto.getIsAlphanumeric());
            field.setCategoryFieldlength(fieldDto.getLength());
            field.setCategoryFieldminValue(fieldDto.getMinValue());
            field.setCategoryFieldmaxValue(fieldDto.getMaxValue());
            field.setIsAlpha(fieldDto.getIsAlpha());
            field.setCategory(savedCategory);

            CategoryField categoryFieldSave=CategoryFieldRepository.save(field);
            if(savedCategory.getFields()==null)
            {
                savedCategory.setFields(new ArrayList<>());
            }
            savedCategory.getFields().add(categoryFieldSave);
            categoryRepository.save(savedCategory);
        }

        return savedCategory;
     }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryRepository.findByActiveCategoryTrue()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setIdCategory(category.getIdCategory());
            categoryDto.setName(category.getNameCategory());
            categoryDto.setFields(new ArrayList<>());
            for(CategoryField categoryField : category.getFields()) {
                CategoryFieldDto categoryFieldDto = new CategoryFieldDto();
                categoryFieldDto.setFieldId(categoryField.getCategoryFieldId());
                categoryFieldDto.setLabel(categoryField.getCategoryFieldlabel());
                categoryFieldDto.setType(categoryField.getCategoryFieldtype());
                categoryFieldDto.setIsNumeric(categoryField.getCategoryFieldisNumeric());
                categoryFieldDto.setIsAlphanumeric(categoryField.getCategoryFieldisAlphanumeric());
                categoryFieldDto.setLength(categoryField.getCategoryFieldlength());
                categoryFieldDto.setMinValue(categoryField.getCategoryFieldminValue());
                categoryFieldDto.setMaxValue(categoryField.getCategoryFieldmaxValue());
                categoryDto.getFields().add(categoryFieldDto);
            }
            categoryDtoList.add(categoryDto);


        }
        return categoryDtoList;
    }

    @Override
    public List<EquipmentDto> getAllEquipmentsAnnuaire() {
        List<Equipment> equipmentList=equipmentRepository.findAll();
        List<EquipmentDto> equipmentDtoList=new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setEquipmentId(equipment.getEquipmentId());
            equipmentDto.setEquipmentDescription(equipment.getEquipmentDescription());
            equipmentDto.setEquipmentName(equipment.getEquipmentName());
            equipmentDto.setEquipmentType(equipment.getEquipmentType());
            equipmentDto.setPqsEquipement(equipment.isPqsEquipement());
            equipmentDto.setMarque(equipment.getMarque().getMarqueName());
            equipmentDto.setAddedDate(equipment.getAddedDate());
            equipmentDto.setModel(equipment.getModel());
            equipmentDto.setIsActiveEquipement(equipment.getIsActiveEquipement());
            equipmentDto.setEquipmentCategory(this.getEquipementCategorie(equipment.getCategory().getIdCategory()));
            equipmentDto.setEquipmentValueDtos(this.getequipementValuebyEquipement(equipment.getEquipmentId()));
            equipmentDto.setMaintenanceDtos(this.getMaintenanceDtoListbyEquipemen(equipment.getEquipmentId()));
            equipmentDtoList.add(equipmentDto);
        }
        return equipmentDtoList;
    }

    @Override
    public Equipment addEquipement(EquipmentDto equipmentDto) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(UUID.randomUUID().toString());
        equipment.setEquipmentDescription(equipmentDto.getEquipmentDescription());
        equipment.setEquipmentName(equipmentDto.getEquipmentName());
        equipment.setEquipmentType(equipmentDto.getEquipmentType());
        equipment.setPqsEquipement(equipment.isPqsEquipement());
        equipment.setAddedDate(LocalDateTime.now());
        equipment.setModel(equipmentDto.getModel());
        equipment.setIsActiveEquipement(true);
        Marque marque=marqueReposiotry.findById(equipmentDto.getMarque()).orElse(null);
        equipment.setMarque(marque);
        Category category=categoryRepository.findById(equipmentDto.getEquipmentCategory().getIdCategory()).orElse(null);
        equipment.setCategory(category);
        Equipment equipmentSave=equipmentRepository.save(equipment);
        equipmentSave.setEquipmentValueList(new ArrayList<>());
        Equipment equipementfinalSave=new Equipment();
        assert category != null;
        category.getEquipmentsList().add(equipmentSave);
        for(EquipmentValueDto equipmentValueDto:equipmentDto.getEquipmentValueDtos())
        {
            EquipmentValue equipmentValue=new EquipmentValue();
            equipmentValue.setEquipment(equipmentSave);
            equipmentValue.setValue(equipmentValueDto.getValue());
            CategoryField categoryField=categoryFieldRepository.findById(equipmentValueDto.getCategoryFieldDto().getFieldId()).orElse(null);
            equipmentValue.setCategoryField(categoryField);
           EquipmentValue equipmentValueSave= equipmentValueRepository.save(equipmentValue);
            assert categoryField != null;
            categoryField.getEquipmentValue().add(equipmentValueSave);
            categoryFieldRepository.save(categoryField);
            equipmentSave.getEquipmentValueList().add(equipmentValueSave);
            equipementfinalSave= equipmentRepository.save(equipmentSave);

        }
         return equipementfinalSave;
    }

    @Override
    public List<Marque> getAllMarques() {
        return marqueReposiotry.findAll();
    }

    @Override
    public List<CategoryFieldDto> getCategoryFieldOfSpeceficCategory(String categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        List<CategoryFieldDto> categoryFieldDtoList=new ArrayList<>();
        for(CategoryField categoryField : category.getFields()) {
            CategoryFieldDto categoryFieldDto = new CategoryFieldDto();
            categoryFieldDto.setFieldId(categoryField.getCategoryFieldId());
            categoryFieldDto.setLabel(categoryField.getCategoryFieldlabel());
            categoryFieldDto.setType(categoryField.getCategoryFieldtype());
            categoryFieldDto.setIsNumeric(categoryField.getCategoryFieldisNumeric());
            categoryFieldDto.setIsAlphanumeric(categoryField.getCategoryFieldisAlphanumeric());
            categoryFieldDto.setLength(categoryField.getCategoryFieldlength());
            categoryFieldDto.setMinValue(categoryField.getCategoryFieldminValue());
            categoryFieldDto.setMaxValue(categoryField.getCategoryFieldmaxValue());
            categoryFieldDtoList.add(categoryFieldDto);
         }

        return categoryFieldDtoList;
    }

    @Override
    public boolean disableEquipementAnnuaire(String equipementId) {
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        if(equipment!=null)
        {
            equipment.setIsActiveEquipement(false);
            equipmentRepository.save(equipment);
        }
        return false;
    }

    @Override
    public boolean enableEquipementAnnuaire(String equipementId) {
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        if(equipment!=null)
        {
            equipment.setIsActiveEquipement(true);
            equipmentRepository.save(equipment);
        }
        return false;    }

    @Override
    public Maintenance addMaintenance(MaintenanceDto maintenanceDto, String equipementId) {
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        if(equipment!=null)
        {
            Maintenance maintenance=new Maintenance();
            maintenance.setEquipment(equipment);
            maintenance.setLibelleMaintenance(maintenanceDto.getLibelleMaintenance());
            maintenance.setIdMaintenance(UUID.randomUUID().toString());
            maintenance.setNbdaysaftermiseenservice(maintenanceDto.getNbdaysaftermiseenservice());
            Maintenance maintenanceSave= maintenanceRepository.save(maintenance);
            equipment.getMaintenanceList().add(maintenanceSave);
            equipmentRepository.save(equipment);
            return maintenanceSave;
        }
        return null;
    }

    @Override
    public EquipmentDto getEquipementDetails(String equipementId) {
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        if(equipment!=null) {
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setEquipmentId(equipment.getEquipmentId());
            equipmentDto.setEquipmentDescription(equipment.getEquipmentDescription());
            equipmentDto.setEquipmentName(equipment.getEquipmentName());
            equipmentDto.setEquipmentType(equipment.getEquipmentType());
            equipmentDto.setPqsEquipement(equipment.isPqsEquipement());
            equipmentDto.setMarque(equipment.getMarque().getMarqueName());
            equipmentDto.setAddedDate(equipment.getAddedDate());
            equipmentDto.setModel(equipment.getModel());
            equipmentDto.setIsActiveEquipement(equipment.getIsActiveEquipement());
            equipmentDto.setEquipmentCategory(this.getEquipementCategorie(equipment.getCategory().getIdCategory()));
            equipmentDto.setEquipmentValueDtos(this.getequipementValuebyEquipement(equipment.getEquipmentId()));
            equipmentDto.setMaintenanceDtos(this.getMaintenanceDtoListbyEquipemen(equipment.getEquipmentId()));

        return equipmentDto;
        }
        return null;
        }

    @Override
    public boolean assignEquipementToStructure(EquipementStructure equipementStructure, String structureId) {
        Equipment equipment=equipmentRepository.findById(equipementStructure.getIdEquipement()).orElse(null);
        if(equipment!=null) {
            {
                 equipementStructure.setActiveEquipement(true);
                equipementStructure.setIdStructure(structureId);
                equipementStructure.setIdEquipement(equipementStructure.getIdEquipement());
                equipementStructureRepository.save(equipementStructure);
                return true;

            }
        }
        return false;
    }

    @Override
    public List<EquipmentDto> getAllEquipmentsAnnuairebyStructure(String structureId) {
        List<Equipment> equipmentList=equipmentRepository.JPQL_GetStructureEquipement(structureId);
        List<EquipmentDto> equipmentDtoList=new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            for(EquipementStructure equipementStructure: equipementStructureRepository.JQPL_getEquipementStructure(equipment.getEquipmentId(),structureId))
            {            EquipmentDto equipmentDto = new EquipmentDto();

                if(equipementStructure.getIdEquipement().equals(equipment.getEquipmentId()))
                {
                    equipmentDto.setIsActiveEquipement(equipementStructure.isActiveEquipement());
                    equipmentDto.setDataachat(equipementStructure.getDataachat());
                    equipmentDto.setDateMiseEnservice(equipementStructure.getDateMiseEnservice());
                    equipmentDto.setSerialNumber(equipementStructure.getSerialNumber());
                    equipmentDto.setInventairenumber(equipementStructure.getInventairenumber());
                }
            equipmentDto.setEquipmentId(equipment.getEquipmentId());
            equipmentDto.setEquipmentDescription(equipment.getEquipmentDescription());
            equipmentDto.setEquipmentName(equipment.getEquipmentName());
            equipmentDto.setEquipmentType(equipment.getEquipmentType());
            equipmentDto.setPqsEquipement(equipment.isPqsEquipement());
            equipmentDto.setMarque(equipment.getMarque().getMarqueName());
            equipmentDto.setAddedDate(equipment.getAddedDate());
            equipmentDto.setModel(equipment.getModel());
            equipmentDto.setTempmax(equipment.getTempmax());
            equipmentDto.setTempmin(equipment.getTempmin());
             equipmentDto.setEquipmentCategory(this.getEquipementCategorie(equipment.getCategory().getIdCategory()));
            equipmentDto.setEquipmentValueDtos(this.getequipementValuebyEquipement(equipment.getEquipmentId()));
            equipmentDto.setMaintenanceDtos(this.getMaintenanceDtoListbyEquipemen(equipment.getEquipmentId()));

            equipmentDtoList.add(equipmentDto);
        }
        }
        return equipmentDtoList;
     }

    @Override
    @Transactional
    public boolean disableequipementstructure(String equipementId, String structureId) {
        equipementStructureRepository.disableequipementstructure(equipementId,structureId);
        return true;
    }

    @Override
@Transactional
    public boolean enableequipementstructure(String equipementId, String structureId) {
        equipementStructureRepository.enableequipementstructure(equipementId,structureId);
        return true;
    }

    private List<MaintenanceDto> getMaintenanceDtoListbyEquipemen(String equipementId) {
        List<MaintenanceDto> maintenanceDtoList=new ArrayList<>();
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        for(Maintenance maintenance:equipment.getMaintenanceList())
        {
            MaintenanceDto maintenanceDto=new MaintenanceDto();
            maintenanceDto.setIdMaintenance(maintenance.getIdMaintenance());
            maintenanceDto.setLibelleMaintenance(maintenance.getLibelleMaintenance());
            maintenanceDto.setNbdaysaftermiseenservice(maintenance.getNbdaysaftermiseenservice());
            maintenanceDtoList.add(maintenanceDto);
        }
        return maintenanceDtoList;
    }
    public  List<EquipmentValueDto> getequipementValuebyEquipement(String equipementId)
    {
        List<EquipmentValueDto> equipementValueDtoList=new ArrayList<>();
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        List<EquipmentValue> equipmentValueList=equipmentValueRepository.findByEquipment(equipment);
        for(EquipmentValue equipmentValue:equipmentValueList)
        {
            EquipmentValueDto equipmentValueDto=new EquipmentValueDto();
            equipmentValueDto.setValue(equipmentValue.getValue());
            equipmentValueDto.setCategoryFieldDto(this.getCategoryFieldDtoEquipement(equipmentValue.getCategoryField()));
            equipementValueDtoList.add(equipmentValueDto);
        }
        return equipementValueDtoList;
    }
    private CategoryFieldDto getCategoryFieldDtoEquipement(CategoryField categoryField)
    {
        CategoryFieldDto categoryFieldDto = new CategoryFieldDto();

        categoryFieldDto.setFieldId(categoryField.getCategoryFieldId());
        categoryFieldDto.setLabel(categoryField.getCategoryFieldlabel());
        categoryFieldDto.setType(categoryField.getCategoryFieldtype());
        categoryFieldDto.setIsNumeric(categoryField.getCategoryFieldisNumeric());
        categoryFieldDto.setIsAlphanumeric(categoryField.getCategoryFieldisAlphanumeric());
        categoryFieldDto.setLength(categoryField.getCategoryFieldlength());
        categoryFieldDto.setMinValue(categoryField.getCategoryFieldminValue());
        categoryFieldDto.setMaxValue(categoryField.getCategoryFieldmaxValue());
        return categoryFieldDto;
    }
    public  CategoryDto getEquipementCategorie(String idcat) {
        Category category=categoryRepository.findById(idcat).orElse(null);
             CategoryDto categoryDto = new CategoryDto();
        assert category != null;
        categoryDto.setIdCategory(category.getIdCategory());
            categoryDto.setName(category.getNameCategory());
            categoryDto.setFields(new ArrayList<>());
            for(CategoryField categoryField : category.getFields()) {
                CategoryFieldDto categoryFieldDto = new CategoryFieldDto();
                categoryFieldDto.setFieldId(categoryField.getCategoryFieldId());
                categoryFieldDto.setLabel(categoryField.getCategoryFieldlabel());
                categoryFieldDto.setType(categoryField.getCategoryFieldtype());
                categoryFieldDto.setIsNumeric(categoryField.getCategoryFieldisNumeric());
                categoryFieldDto.setIsAlphanumeric(categoryField.getCategoryFieldisAlphanumeric());
                categoryFieldDto.setLength(categoryField.getCategoryFieldlength());
                categoryFieldDto.setMinValue(categoryField.getCategoryFieldminValue());
                categoryFieldDto.setMaxValue(categoryField.getCategoryFieldmaxValue());
                categoryDto.getFields().add(categoryFieldDto);



        }
        return categoryDto;
    }
    List<MaintenanceDto> getMaintenanceDtoPerEquipement(String equipementId) {
        List<MaintenanceDto> maintenanceDtoList=new ArrayList<>();
        Equipment equipment=equipmentRepository.findById(equipementId).orElse(null);
        for(Maintenance maintenance:equipment.getMaintenanceList())
        {
            MaintenanceDto maintenanceDto=new MaintenanceDto();
            maintenanceDto.setIdMaintenance(maintenance.getIdMaintenance());
            maintenanceDto.setLibelleMaintenance(maintenance.getLibelleMaintenance());
            maintenanceDto.setNbdaysaftermiseenservice(maintenance.getNbdaysaftermiseenservice());
            maintenanceDtoList.add(maintenanceDto);
        }
        return maintenanceDtoList;
    }

    public Map<LocalDate, List<MaintenanceDetail>> generateWeeklyMaintenanceDetails(LocalDate startDate, LocalDate serviceDate,
                                                                                    String equipmentName, String maintenanceLabel,
                                                                                    int frequencyDays,String idmain) {
        Map<LocalDate, List<MaintenanceDetail>> weeklyMaintenanceDetails = new TreeMap<>();
        LocalDate nextMaintenanceDate = serviceDate.plusDays(frequencyDays);

        while (!nextMaintenanceDate.isAfter(startDate.plusMonths(12))) {
            // Ajuster la date si elle est un jour férié ou un week-end
            while (isWeekend(nextMaintenanceDate)) {
                nextMaintenanceDate = nextMaintenanceDate.plusDays(1); // Aller au jour suivant
            }

            // Get the start of the week for the next maintenance date
            LocalDate startOfWeek = nextMaintenanceDate.with(DayOfWeek.MONDAY);

            // Add the maintenance detail to the appropriate week
            weeklyMaintenanceDetails
                    .computeIfAbsent(startOfWeek, k -> new ArrayList<>())
                    .add(new MaintenanceDetail(idmain,nextMaintenanceDate, equipmentName, equipmentName + " - " + maintenanceLabel));

            // Move to the next maintenance date
            nextMaintenanceDate = nextMaintenanceDate.plusDays(frequencyDays);
        }

        return weeklyMaintenanceDetails;
    }

    @Override
    public List<MaintenanceDetail> getMaintenanceForStructure(String structureId) {
        List<Equipment> equipments = equipmentRepository.JPQL_GetEquipementByStructureId(structureId);
        List<MaintenanceDetail> maintenanceDetails = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Use a map to aggregate maintenance details by week
        Map<LocalDate, List<MaintenanceDetail>> weeklyMaintenanceMap = new TreeMap<>();

        for (Equipment equipment : equipments) {
            for (Maintenance maintenance : equipment.getMaintenanceList()) {
                // Generate maintenance details for each equipment and maintenance
                Map<LocalDate, List<MaintenanceDetail>> weeklyDetails = generateWeeklyMaintenanceDetails(
                        today,
                        equipment.getAddedDate().toLocalDate(),
                        equipment.getEquipmentName(),
                        maintenance.getLibelleMaintenance(),
                        maintenance.getNbdaysaftermiseenservice(),
                        maintenance.getIdMaintenance()
                );

                // Aggregate details by week
                for (Map.Entry<LocalDate, List<MaintenanceDetail>> entry : weeklyDetails.entrySet()) {
                    weeklyMaintenanceMap
                            .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                            .addAll(entry.getValue());
                }
            }
        }

        // Flatten the map into a list
        for (List<MaintenanceDetail> details : weeklyMaintenanceMap.values()) {
            maintenanceDetails.addAll(details);
        }

        return maintenanceDetails;
    }


    @Override
    public EquipementStructureTemperature addtemperature(String temperature) {
        EquipementStructureTemperature equipementStructureTemperature=new EquipementStructureTemperature();
        equipementStructureTemperature.setIdEquipement("7a0762f5-3490-4d15-accf-7ed18d8d9bf1");
        equipementStructureTemperature.setTemperature(temperature);
        equipementStructureTemperature.setIdStructure("202407175243563");
        equipementStructureTemperature.setInventairenumber("56985225");
        equipementStructureTemperature.setDatetemp(LocalDateTime.now());
        return equipementStructureTemperatureRepository.save(equipementStructureTemperature);
    }

    @Override
    public List<EquipementStructureTemperature> getemperatureequipement(String idEquipement, String idStructure, String inventairenumber) {

        return equipementStructureTemperatureRepository.JPQL_findByEquipementStructureId(idStructure,idEquipement,inventairenumber);
    }

    // Méthode pour vérifier si une date est un jour férié
    private boolean isHoliday(LocalDate date) {
        List<DaysOff> holidays = daysOffRepository.findAll();
        return holidays.stream().anyMatch(holiday -> holiday.getDayoffdate().isEqual(date));
    }

    // Méthode pour vérifier si une date est un week-end
    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
