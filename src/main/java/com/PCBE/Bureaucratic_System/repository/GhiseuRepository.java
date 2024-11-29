package com.PCBE.Bureaucratic_System.repository;

import com.PCBE.Bureaucratic_System.Ghiseu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhiseuRepository extends JpaRepository<Ghiseu, Integer> {
}
