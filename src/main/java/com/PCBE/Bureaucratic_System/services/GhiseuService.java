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

    private final GhiseuRepository ghiseuRepository;

    public GhiseuService(GhiseuRepository ghiseuRepository) {
        this.ghiseuRepository = ghiseuRepository;
    }


    public Ghiseu createGhiseu(Ghiseu ghiseu) {
        return ghiseuRepository.save(ghiseu);
    }


    public List<Ghiseu> getAllGhisee() {
        return ghiseuRepository.findAll();
    }


    public Optional<Ghiseu> getGhiseuById(int id) {
        return ghiseuRepository.findById(id);
    }




    public boolean deleteGhiseu(int id) {
        Optional<Ghiseu> ghiseu = ghiseuRepository.findById(id);
        if (ghiseu.isPresent()) {
            ghiseuRepository.delete(ghiseu.get());
            return true;
        }
        return false;
    }


    public void saveGhiseu(Ghiseu ghiseu) {
        ghiseuRepository.save(ghiseu);
    }


}
