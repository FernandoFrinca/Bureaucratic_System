package com.PCBE.Bureaucratic_System.services;

import com.PCBE.Bureaucratic_System.Birou;
import com.PCBE.Bureaucratic_System.repository.BirouRepository;
import com.PCBE.Bureaucratic_System.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirouService {

    private final BirouRepository birouRepository;

    public BirouService(BirouRepository birouRepository) {
        this.birouRepository = birouRepository;
    }

    // 1. Create (Creare Birou)
    public Birou createBirou(Birou birou) {
        return birouRepository.save(birou);
    }

    // 2. Read (Citire Birou) - Toate birourile
    public List<Birou> getAllBirouri() {
        return birouRepository.findAll();
    }

    // 3. Read (Citire Birou) - Birou după ID
    public Optional<Birou> getBirouById(int id) {
        return birouRepository.findById(id);
    }

    // 4. Update (Actualizare Birou)
    public Birou updateBirou(int id, Birou birou) {
        if (birouRepository.existsById(id)) {
            birou.setId(id);
            return birouRepository.save(birou);
        } else {
            return null; // Poți arunca o excepție dacă vrei
        }
    }

    // 5. Delete (Ștergere Birou)
    public boolean deleteBirou(int id) {
        if (birouRepository.existsById(id)) {
            birouRepository.deleteById(id);
            return true;
        } else {
            return false; // Poți arunca o excepție dacă vrei
        }
    }
}
