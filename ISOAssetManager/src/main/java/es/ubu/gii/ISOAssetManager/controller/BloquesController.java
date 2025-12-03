package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;

/**
 * Controlador para la selección de bloques de controles.
 * <p>
 * Gestiona la navegación hacia la vista de selección de bloques
 * (Organizacionales, Personas, Físicos, Tecnológicos)
 * para una empresa específica.
 * </p>
 */
@Controller
@RequestMapping("/empresas")
public class BloquesController {

    @Autowired
    private EmpresaRepository empresaRepo;

    /**
     * Muestra la vista de selección de bloques de controles para una empresa.
     *
     * @param empresaId ID de la empresa.
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista de bloques.
     * @throws java.util.NoSuchElementException Si la empresa no existe.
     */
    @GetMapping("/{empresaId}/bloques")
    public String elegirBloque(@PathVariable Long empresaId, Model model) {
        model.addAttribute("empresa", empresaRepo.findById(empresaId).orElseThrow());
        return "bloques";
    }
}