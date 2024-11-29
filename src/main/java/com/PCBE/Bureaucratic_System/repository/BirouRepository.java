package com.PCBE.Bureaucratic_System.repository;
import com.PCBE.Bureaucratic_System.Birou;

import com.PCBE.Bureaucratic_System.Ghiseu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface BirouRepository extends JpaRepository<Birou, Integer> {

}