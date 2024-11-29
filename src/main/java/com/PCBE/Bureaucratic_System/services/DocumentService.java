package com.PCBE.Bureaucratic_System.services;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // CREATE: Adăugăm un document nou
    public Document addDocument(Document document) {
        // Salvează documentul în baza de date
        return documentRepository.save(document);  // Folosește save din JpaRepository
    }


    public List<Document> getAllDocuments() {
        return documentRepository.findAll();  // Folosește findAll din JpaRepository
    }


    public Document updateDocument(int id, String newTip) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            document.setTip(newTip);
            documentRepository.save(document);// Salvăm documentul actualizat
            return document;
        } else {
            System.out.println("Documentul cu ID-ul specificat nu există.");
            return null;
        }
    }

    // DELETE: Ștergem un document pe baza ID-ului
    public boolean deleteDocument(int id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            documentRepository.deleteById(id);  // Folosește deleteById din JpaRepository
            System.out.println("Document șters cu succes!");
            return true;
        } else {
            System.out.println("Documentul cu ID-ul specificat nu există.");
            return false;
        }
    }
}


