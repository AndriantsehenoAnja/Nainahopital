package controller;

import model.Patient;
import service.PatientService;
import java.io.IOException;
import java.util.List;

import mg.etu4370.annotation.Controller;
import mg.etu4370.annotation.Inject;
import mg.etu4370.annotation.AnjaParameter;
import mg.etu4370.annotation.ApiRest;
import mg.etu4370.annotation.UrlMapping;
import mg.etu4370.utils.ModelAndView;

@Controller
public class PatientController {

    @Inject
    private PatientService patientService;

    @UrlMapping(value = "/patients", method = "GET")
    public ModelAndView liste() {
        return listeAvecErreur(null);
    }

    @ApiRest 
    @UrlMapping (value = "/Allpatients",method = "GET")
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @ApiRest 
    @UrlMapping (value = "/patient",method = "GET") 
    public Patient findByIdPatient(@AnjaParameter(name="id") Integer id){
        return patientService.findById(id);
    }

    @UrlMapping(value = "/patients/new", method = "GET")
    public ModelAndView nouveau() {
        return formulaire(new Patient(), null);
    }

    @UrlMapping(value = "/patients/edit", method = "GET")
    public ModelAndView modifier(@AnjaParameter(name="id") Integer id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return listeAvecErreur("Patient introuvable.");
        }
        return formulaire(patient, null);
    }

    @UrlMapping(value = "/patient/fiche", method = "GET")
    public ModelAndView fiche(@AnjaParameter(name="id") Integer id) {
        Patient patient = patientService.findById(id);
        ModelAndView mv = new ModelAndView("views/patient/fiche");
        
        if (patient == null) {
            return listeAvecErreur("Patient introuvable.");
        }
        mv.addAttribut("patient", patient);
        return mv;
    }

    @UrlMapping(value = "/patients/save", method = "POST")
    public ModelAndView enregistrer(@AnjaParameter(name="patient") Patient patient) throws IOException {
        try {
            patientService.save(patient);
        } catch (IllegalArgumentException e) {
            return formulaire(patient, e.getMessage());  
        }
        return listeAvecErreur(null);
    }

    @UrlMapping(value = "/patients/delete", method = "POST")
    public ModelAndView supprimer(@AnjaParameter(name="id") Integer id) throws IOException {

        patientService.delete(id);
        return listeAvecErreur(null);
    }

    private ModelAndView listeAvecErreur(String erreur) {
        ModelAndView mv = new ModelAndView("views/patient/list");
        mv.addAttribut("patients", patientService.findAll());
        if (erreur != null) mv.addAttribut("erreur", erreur);
        return mv;
    }

    private ModelAndView formulaire(Patient patient, String erreur) {
        ModelAndView mv = new ModelAndView("views/patient/form");
        mv.addAttribut("patient", patient);
        if (erreur != null) mv.addAttribut("erreur", erreur);
        return mv;
    }
}