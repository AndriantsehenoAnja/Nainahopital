package service;

import model.Patient;
import repository.PatientRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import mg.etu4370.annotation.Inject;

@Service
public class PatientService {

    @Inject 
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAllByOrderByNomAscPrenomAsc();
    }

    public Patient findById(Integer id) {
        return id == null ? null : patientRepository.findById(id).orElse(null);
    }

    public Patient save(Patient patient) {
        normaliser(patient);
        valider(patient);
        return patientRepository.save(patient);
    }

    public void delete(Integer id) {
        if (id != null && patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        }
    }

    private void normaliser(Patient p) {
        p.setNom(trimOrNull(p.getNom()));
        p.setPrenom(trimOrNull(p.getPrenom()));
        p.setEmail(trimOrNull(p.getEmail()));
        p.setTelephone(trimOrNull(p.getTelephone()));
    }

    private void valider(Patient p) {
        if (p.getNom() == null) throw new IllegalArgumentException("Le nom est obligatoire.");
        if (p.getPrenom() == null) throw new IllegalArgumentException("Le prénom est obligatoire.");
        if (p.getNom().length() > 50) throw new IllegalArgumentException("Le nom ne doit pas dépasser 50 caractères.");
        if (p.getPrenom().length() > 90) throw new IllegalArgumentException("Le prénom ne doit pas dépasser 90 caractères.");
        if (p.getEmail() != null) {
            if (p.getEmail().length() > 150) throw new IllegalArgumentException("L'email ne doit pas dépasser 150 caractères.");
            if (!p.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw new IllegalArgumentException("L'email n'est pas valide.");
        }
        if (p.getTelephone() != null && p.getTelephone().length() > 20) {
            throw new IllegalArgumentException("Le téléphone ne doit pas dépasser 20 caractères.");
        }
        // if (p.getDateNaissance() != null && p.getDateNaissance().isAfter(LocalDate.now())) {
        //     throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur.");
        // }
    }

    private String trimOrNull(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }
}