package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.services.BirouService;
import com.PCBE.Bureaucratic_System.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/birou-ui")
public class BirouUIController {
    private final BirouService birouService;

    @Autowired
    public BirouUIController(BirouService birouService) {
        this.birouService = birouService;
    }

    @GetMapping("/get")
    public String viewBirou() {
        return "forward:/birou.html";
    }
}
