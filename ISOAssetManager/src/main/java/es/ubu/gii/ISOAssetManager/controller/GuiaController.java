package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GuiaController {

    // Ej.: /guia-control/A5.1  ->  templates/guias/control-A5-1.html
    @GetMapping("/guia-control/{controlId:.+}")
    public String verGuia(@PathVariable String controlId) {
        String normalizado = controlId.replace('.', '-').toUpperCase(); // A5.1 -> A5-1
        return "guias/control-" + normalizado;
    }
}