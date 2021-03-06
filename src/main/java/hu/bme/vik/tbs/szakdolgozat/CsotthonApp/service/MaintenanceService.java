package hu.bme.vik.tbs.szakdolgozat.CsotthonApp.service;

import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.dto.MaintenanceDTO;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.mapper.MaintenanceMapper;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.model.Maintenance;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.model.User;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.repository.MaintenanceRepository;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.repository.UserRepository;
import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.util.Time;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceService() {
        maintenanceMapper = Mappers.getMapper(MaintenanceMapper.class);
    }

    @Transactional
    public MaintenanceDTO createMaintenance(MaintenanceDTO maintenanceDTO, Principal principal) {
        Maintenance maintenance = maintenanceMapper.maintenanceDTOtoMaintenance(maintenanceDTO);
        maintenance.setId(null);
        maintenance.setTime(Time.getNowInUTC());
        User user = userRepository.findByUsername(principal.getName());
        maintenance.setUser(user);
        maintenanceRepository.save(maintenance);
        return maintenanceMapper.maintenanceToMaintenanceDTO(maintenance);
    }

    public List<MaintenanceDTO> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAllByOrderByTimeDesc();
        List<MaintenanceDTO> maintenanceDTOs = new ArrayList<>();
        for (Maintenance maintenance : maintenances) {
            maintenanceDTOs.add(maintenanceMapper.maintenanceToMaintenanceDTO(maintenance));
        }
        return maintenanceDTOs;
    }

    @Transactional
    public Boolean delete(Integer id) {
        if (maintenanceRepository.findById(id).isPresent()) {
            maintenanceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
