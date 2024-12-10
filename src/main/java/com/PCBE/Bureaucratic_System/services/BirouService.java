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


    public Birou createBirou(Birou birou) {
        return birouRepository.save(birou);
    }


    public List<Birou> getAllBirouri() {
        return birouRepository.findAll();
    }


    public Optional<Birou> getBirouById(int id) {
        return birouRepository.findById(id);
    }


    public Birou updateBirou(int id, Birou birou) {
        if (birouRepository.existsById(id)) {
            birou.setId(id);
            return birouRepository.save(birou);
        } else {
            return null;
        }
    }


    public boolean deleteBirou(int id) {
        if (birouRepository.existsById(id)) {
            birouRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
