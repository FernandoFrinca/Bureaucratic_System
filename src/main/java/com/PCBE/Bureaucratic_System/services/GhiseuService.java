package com.PCBE.Bureaucratic_System.services;

import com.PCBE.Bureaucratic_System.Ghiseu;
import com.PCBE.Bureaucratic_System.repository.DocumentRepository;
import com.PCBE.Bureaucratic_System.repository.GhiseuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class GhiseuService {

    private final GhiseuRepository ghiseuRepository; // Injectăm repository-ul pentru ghiseu

    public GhiseuService(GhiseuRepository ghiseuRepository) {
        this.ghiseuRepository = ghiseuRepository;
    }

    // 1. CREATE - Creare un ghiseu nou
    public Ghiseu createGhiseu(Ghiseu ghiseu) {
        return ghiseuRepository.save(ghiseu);  // Salvează ghiseul în baza de date
    }

    // 2. READ - Obține toate ghișeele
    public List<Ghiseu> getAllGhisee() {
        return ghiseuRepository.findAll();  // Returnează toate ghișeele
    }

    // 3. READ - Obține un ghiseu după ID
    public Optional<Ghiseu> getGhiseuById(int id) {
        return ghiseuRepository.findById(id);  // Căutăm ghiseul după ID
    }

    // 4. UPDATE - Actualizează un ghiseu existent
    /*public Ghiseu updateGhiseu(int id, Ghiseu ghiseu) {
        Optional<Ghiseu> existingGhiseu = ghiseuRepository.findById(id);  // Căutăm ghiseul existent
        if (existingGhiseu.isPresent()) {
            Ghiseu ghiseuToUpdate = existingGhiseu.get();
            ghiseuToUpdate.setTip_de_document_eliberat(ghiseu.getTip_de_document_eliberat());
            // Poți actualiza și alte câmpuri dacă este necesar
            return ghiseuRepository.save(ghiseuToUpdate);  // Salvează ghiseul actualizat
        } else {
            return null;  // Dacă nu găsim ghiseul, returnăm null
        }
    }*/

    // 5. DELETE - Șterge un ghiseu după ID
    public boolean deleteGhiseu(int id) {
        Optional<Ghiseu> ghiseu = ghiseuRepository.findById(id);  // Căutăm ghiseul
        if (ghiseu.isPresent()) {
            ghiseuRepository.delete(ghiseu.get());  // Ștergem ghiseul
            return true;
        }
        return false;
    }
}
