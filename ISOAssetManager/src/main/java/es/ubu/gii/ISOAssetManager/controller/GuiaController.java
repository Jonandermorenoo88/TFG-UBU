package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controlador para la visualización de guías de ayuda de los controles.
 * <p>
 * Redirige a las plantillas HTML específicas que contienen la documentación y
 * guía
 * de implementación para cada control ISO 27001 (ej: A5.1, A8.2).
 * </p>
 */
@Controller
public class GuiaController {

    /**
     * Muestra la guía de ayuda para un control específico.
     * <p>
     * Transforma el ID del control (ej: "A5.1") en el nombre de la plantilla
     * correspondiente
     * (ej: "guias/control-A5-1").
     * </p>
     *
     * @param controlId ID del control (ej: "A5.1").
     * @return Nombre de la vista de la guía del control.
     */
    @GetMapping("/guia-control/{controlId:.+}")
    public String verGuia(@PathVariable String controlId) {
        String normalizado = controlId.replace('.', '-').toUpperCase(); // A5.1 -> A5-1
        return "guias/control-" + normalizado;
    }
}