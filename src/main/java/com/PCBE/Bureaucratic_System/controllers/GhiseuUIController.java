package com.PCBE.Bureaucratic_System.controllers;

import org.springframework.ui.Model;
import com.PCBE.Bureaucratic_System.Ghiseu;
import com.PCBE.Bureaucratic_System.services.GhiseuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ghiseu-ui")
public class GhiseuUIController {

    private final GhiseuService ghiseuService;

    @Autowired
    public GhiseuUIController(GhiseuService ghiseuService) {
        this.ghiseuService = ghiseuService;
    }

    @GetMapping("/get")
    public String viewGhisee() {
        return "forward:/ghiseu.html";
    }
}
