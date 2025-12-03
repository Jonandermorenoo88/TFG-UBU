package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controlador para el manejo de errores personalizados.
 * <p>
 * Gestiona las redirecciones a vistas de error específicas, como la página de
 * acceso denegado (403).
 * </p>
 */
@Controller
public class ErrorController {

    /**
     * Muestra la página de error 403 (Acceso Denegado).
     *
     * @return Nombre de la vista de error 403.
     */
    @GetMapping("/error/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden() {
        return "403";
    }
}
