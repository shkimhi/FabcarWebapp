package com.example.FabcarWebapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Controller
public class MainController {


    private AuthorizationService authorizationService;
    private LedgerService ledgerService;

    @Autowired
    public MainController(AuthorizationService authorizationService , LedgerService ledgerService){
        this.authorizationService = authorizationService;
        this.ledgerService = ledgerService;
    }

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @PostMapping("/enrollAdmin")
    public String enrollAdmin(Model model) {
        try {
            model.addAttribute("resultEnrollAdmin", authorizationService.enrollAdmin());
        } catch (Exception e) {
            model.addAttribute("resultEnrollAdmin", e.getMessage());
        }
        return "index";
    }

    @PostMapping("/registerUser")
    public String registerUser (Model model) {

        try {
            model.addAttribute("resultRegisterUser", authorizationService.registerUser());
        } catch (Exception e) {
            model.addAttribute("resultRegisterUser", e.getMessage());
        }
        return "index";
    }

    @PostMapping("getAllCars")
    public String getAllCars(Model model){
        try {
            List<Result> result = ledgerService.getAllCars();
            model.addAttribute("resultCars", result);
        }catch (Exception e) {
            model.addAttribute("resultCars", Collections.emptyList());
            model.addAttribute("resultCarsError", e.getMessage());
        }
        return "index";
    }
    @PostMapping("/createCar")
    public String createCar(Model model, @ModelAttribute("result") Result result) {
        try {
            model.addAttribute("resultCreateCar", ledgerService.createCar(result));
        } catch (Exception e) {
            model.addAttribute("resultCreateCar", e.getMessage());
        }
        return "index";
    }

}
