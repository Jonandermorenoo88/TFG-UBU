package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.ubu.gii.ISOAssetManager.repository.*;

/**
 * Controlador para la navegación y visualización de los anexos ISO 27001.
 * <p>
 * Gestiona la presentación de categorías (anexos), controles y preguntas
 * asociadas a cada control del estándar ISO/IEC 27001:2022.
 * </p>
 */
@Controller
@RequestMapping("/anexos")
public class AnexoController {

    private final CategoriaRepository categoriaRepo;
    private final ControlRepository controlRepo;
    private final PreguntaRepository preguntaRepo;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param categoriaRepo Repositorio de categorías (anexos).
     * @param controlRepo   Repositorio de controles.
     * @param preguntaRepo  Repositorio de preguntas.
     */
    public AnexoController(CategoriaRepository categoriaRepo,
            ControlRepository controlRepo,
            PreguntaRepository preguntaRepo) {
        this.categoriaRepo = categoriaRepo;
        this.controlRepo = controlRepo;
        this.preguntaRepo = preguntaRepo;
    }

    /**
     * Muestra la lista de todos los anexos (categorías) disponibles.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista de lista de anexos.
     */
    @GetMapping
    public String listarAnexos(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "lista";
    }

    /**
     * Muestra los controles de un anexo específico.
     *
     * @param anexoId ID del anexo (categoría) (ej: "A5", "A6").
     * @param model   Modelo para pasar datos a la vista.
     * @return Nombre de la vista de controles del anexo.
     */
    @GetMapping("/{anexoId}/controles")
    public String listarControlesDeAnexo(@PathVariable String anexoId, Model model) {
        model.addAttribute("anexo", categoriaRepo.findById(anexoId).orElse(null));
        model.addAttribute("controles", controlRepo.findByCategoria_IdOrderByOrdenAscIdAsc(anexoId));
        return "controles";
    }

    /**
     * Muestra las preguntas asociadas a un control específico.
     *
     * @param controlId ID del control (ej: "A5.1", "A6.2").
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista de preguntas del control.
     */
    @GetMapping("/controles/{controlId}/preguntas")
    public String preguntasDeControl(@PathVariable String controlId, Model model) {
        model.addAttribute("controlId", controlId);
        model.addAttribute("preguntas", preguntaRepo.findByControl_IdOrderByIdAsc(controlId));
        return "preguntas";
    }
}
