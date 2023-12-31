package com.example.gestionparcautomobil.web;

import com.example.gestionparcautomobil.dtos.ConducteurDTO;
import com.example.gestionparcautomobil.entities.Conducteur;
import com.example.gestionparcautomobil.exceptions.*;
import com.example.gestionparcautomobil.services.ConducteurService;
import com.example.gestionparcautomobil.services.VoyageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@Slf4j
public class  ConducteurRestController {
    private ConducteurService conducteurService;
    private VoyageService voyageService;

    @GetMapping("/api/v1/conducteurs")
    public List<ConducteurDTO> Conducteurs() {
        return conducteurService.listConducteur();
    }

    @GetMapping("/api/v1/conducteurs/{id}")
    public ConducteurDTO getConducteur(@PathVariable(name = "id") Long conducteurId) throws ConducteurNotFoundException {
        return conducteurService.getConducteur(conducteurId);
    }

    @PostMapping("/api/v1/conducteurs")
    public ConducteurDTO saveConducteur(@RequestBody ConducteurDTO conducteurDTO) {
        return conducteurService.saveConducteur(conducteurDTO);
    }

    @DeleteMapping("/api/v1/conducteurs/{id}")
    public void deleteConducteur(@PathVariable(name = "id") Long conducteurId) {
        conducteurService.deleteConducteur(conducteurId);
    }
    @PostMapping("/api/v1/affectationConducteurToVoyage")
    public void affectationConducteur(@RequestBody ConducteurVoyageForm conducteurVoyageForm) throws ConducteurNotFoundException, VoyageNotFoundException, ConducteurNonDisponibleException {
       voyageService.AffectationConducteur(conducteurVoyageForm.getVoyageId(),conducteurVoyageForm.getConducteurId());
    }
    @PostMapping("/api/v1/affectationVehiculeToVoyage")
    public void affectationVehicule(@RequestBody VehiculeVoyageForm vehiculeVoyageForm) throws VehiculeNotFoundException, VoyageNotFoundException, VehiculeNonDisponibleException {
        voyageService.AffectationVehicule(vehiculeVoyageForm.getVoyageId(),vehiculeVoyageForm.getMatricule());
    }

}
@Data
 class ConducteurVoyageForm{
    private  Long voyageId;
    private  Long conducteurId;
 }
@Data
class VehiculeVoyageForm{
    private  Long voyageId;
    private  String matricule;
}