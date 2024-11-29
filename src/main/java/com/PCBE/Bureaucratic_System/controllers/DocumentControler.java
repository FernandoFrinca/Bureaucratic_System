package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.services.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/document")
@Tag(name = "Bureaucratic_System", description = "API for managing documents")
public class DocumentControler {

    private final DocumentService documentService;

    @Autowired
    public DocumentControler(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/getAll")
    @Operation(
            summary = "Retrieve all documents",
            description = "Fetches a list of all documents stored in the system, along with their details."
    )
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/post")
    @Operation(
            summary = "Create a new document",
            description = "Adds a new document to the system. The document's details must be provided in the request body."
    )
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        Document createdDocument = documentService.addDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing document",
            description = "Updates the type (`tip`) of a specific document identified by its ID."
    )
    public ResponseEntity<Document> updateDocument(@PathVariable("id") int id, @RequestBody String tip) {
        Document document = documentService.updateDocument(id, tip);
        return document != null ? ResponseEntity.ok(document) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a document",
            description = "Deletes a document from the system based on the provided ID."
    )
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") int id) {
        boolean deleted = documentService.deleteDocument(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
