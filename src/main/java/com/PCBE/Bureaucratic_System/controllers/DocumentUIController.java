package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document-ui")
public class DocumentUIController {

    private final DocumentService documentService;

    @Autowired
    public DocumentUIController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/get")
    public String viewDocumente() {
        return "forward:/document.html";
    }
}
