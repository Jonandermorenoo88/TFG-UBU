package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para las páginas públicas de la aplicación.
 * <p>
 * Gestiona el acceso a la página de inicio y el formulario de registro.
 * </p>
 */
@Controller
public class UsuarioController {

    /**
     * Muestra la página de inicio de la aplicación.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista de inicio.
     */
    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        return "inicio";
    }

    /**
     * Muestra el formulario de registro de nuevos usuarios.
     *
     * @return Nombre de la vista de registro.
     */
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
}
