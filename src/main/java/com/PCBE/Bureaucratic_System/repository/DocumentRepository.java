package com.PCBE.Bureaucratic_System.repository;

import com.PCBE.Bureaucratic_System.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.PCBE.Bureaucratic_System.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
