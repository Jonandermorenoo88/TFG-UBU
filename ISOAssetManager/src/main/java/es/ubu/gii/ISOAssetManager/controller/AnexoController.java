// AnexoController.java
package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.ubu.gii.ISOAssetManager.repository.*;

@Controller
@RequestMapping("/anexos")
public class AnexoController {

    private final CategoriaRepository categoriaRepo;
    private final ControlRepository controlRepo;
    private final PreguntaRepository preguntaRepo;

    public AnexoController(CategoriaRepository categoriaRepo,
                           ControlRepository controlRepo,
                           PreguntaRepository preguntaRepo) {
        this.categoriaRepo = categoriaRepo;
        this.controlRepo = controlRepo;
        this.preguntaRepo = preguntaRepo;
    }

    // Página de anexos
    @GetMapping
    public String listarAnexos(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "lista";
    }

    @GetMapping("/{anexoId}/controles")
    public String listarControlesDeAnexo(@PathVariable String anexoId, Model model) {
        model.addAttribute("anexo", categoriaRepo.findById(anexoId).orElse(null));
        model.addAttribute("controles", controlRepo.findByCategoria_IdOrderByOrdenAscIdAsc(anexoId));
        return "controles";
    }


    @GetMapping("/controles/{controlId}/preguntas")
    public String preguntasDeControl(@PathVariable String controlId, Model model) {
        model.addAttribute("controlId", controlId);
        model.addAttribute("preguntas", preguntaRepo.findByControl_IdOrderByIdAsc(controlId));
        return "preguntas";
    }
}
