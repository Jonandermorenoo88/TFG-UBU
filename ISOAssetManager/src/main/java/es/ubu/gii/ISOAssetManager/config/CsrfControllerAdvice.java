package es.ubu.gii.ISOAssetManager.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controlador de asesoramiento (ControllerAdvice) para gestionar el token CSRF
 * en las vistas.
 * <p>
 * Esta clase se asegura de que el token CSRF esté disponible como un atributo
 * del modelo
 * en todas las solicitudes, facilitando su inclusión en formularios y
 * peticiones AJAX
 * para cumplir con las medidas de seguridad contra Cross-Site Request Forgery.
 * </p>
 */
@ControllerAdvice
public class CsrfControllerAdvice {

    /**
     * Añade el token CSRF al modelo de todas las vistas.
     *
     * @param request La solicitud HTTP actual.
     * @return El token CSRF asociado a la solicitud.
     */
    @ModelAttribute("_csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
