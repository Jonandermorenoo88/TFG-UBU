package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RolPendienteController {

    @GetMapping("/esperarol")
    public String esperarRol() {
        return "esperarol";
    }

    
}
