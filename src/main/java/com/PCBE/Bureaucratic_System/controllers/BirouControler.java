package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Birou;
import com.PCBE.Bureaucratic_System.services.BirouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/birou")
@Tag(name = "Birou Management", description = "API for managing offices (birouri)")
public class BirouControler {

    private final BirouService birouService;

    @Autowired
    public BirouControler(BirouService birouService) {
        this.birouService = birouService;
    }

    @PostMapping("/post")
    @Operation(
            summary = "Create a new office (birou)",
            description = "Adds a new office to the system. The office details must be provided in the request body."
    )
    public ResponseEntity<Birou> createBirou(@RequestBody Birou birou) {
        Birou savedBirou = birouService.createBirou(birou);
        return new ResponseEntity<>(savedBirou, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    @Operation(
            summary = "Retrieve all offices (birouri)",
            description = "Fetches a list of all offices available in the system."
    )
    public ResponseEntity<List<Birou>> getAllBirouri() {
        List<Birou> birouri = birouService.getAllBirouri();
        return new ResponseEntity<>(birouri, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an office (birou)",
            description = "Updates the details of an existing office identified by its ID."
    )
    public ResponseEntity<Birou> updateBirou(@PathVariable int id, @RequestBody Birou birou) {
        Birou updatedBirou = birouService.updateBirou(id, birou);
        return updatedBirou != null ?
                new ResponseEntity<>(updatedBirou, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an office (birou)",
            description = "Deletes an office from the system based on the provided ID."
    )
    public ResponseEntity<Void> deleteBirou(@PathVariable int id) {
        boolean isDeleted = birouService.deleteBirou(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public String viewBirouri(Model model) {
        model.addAttribute("birouri", birouService.getAllBirouri());
        return "birou";
    }

    @PostMapping("/create")
    public String creazaBirou(@ModelAttribute Birou birou) {
        birouService.createBirou(birou);
        return "redirect:/birou-ui";
    }
}
