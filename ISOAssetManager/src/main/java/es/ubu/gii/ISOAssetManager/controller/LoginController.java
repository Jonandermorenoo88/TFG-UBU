package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión del inicio de sesión.
 * <p>
 * Maneja la visualización de la página de login personalizada.
 * </p>
 */
@Controller
public class LoginController {

    /**
     * Muestra el formulario de inicio de sesión.
     *
     * @return Nombre de la vista de login.
     */
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}