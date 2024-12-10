package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Ghiseu;
import com.PCBE.Bureaucratic_System.services.GhiseuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/ghiseu")
@Tag(name = "Ghiseu Management", description = "API for managing counters (ghisee)")
public class GhiseuControler {

    private final GhiseuService ghiseuService;

    @Autowired
    public GhiseuControler(GhiseuService ghiseuService) {
        this.ghiseuService = ghiseuService;
    }

    @PostMapping("/post")
    @Operation(
            summary = "Create a new counter (ghiseu)",
            description = "Adds a new counter to the system. The counter details must be provided in the request body."
    )
    public ResponseEntity<Ghiseu> createGhiseu(@RequestBody Ghiseu ghiseu) {
        if (ghiseu == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ghiseu savedGhiseu = ghiseuService.createGhiseu(ghiseu);
        return new ResponseEntity<>(savedGhiseu, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    /*@Operation(
            summary = "Retrieve all counters (ghisee)",
            description = "Fetches a list of all counters available in the system."
    )*/
    public ResponseEntity<List<Ghiseu>> getAllGhisee() {
        List<Ghiseu> ghisee = ghiseuService.getAllGhisee();
        return new ResponseEntity<>(ghisee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a counter (ghiseu)",
            description = "Deletes a counter from the system based on the provided ID."
    )
    public ResponseEntity<Void> deleteGhiseu(@PathVariable int id) {
        boolean isDeleted = ghiseuService.deleteGhiseu(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
