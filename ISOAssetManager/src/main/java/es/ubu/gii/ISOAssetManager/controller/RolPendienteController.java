package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para la gestión de usuarios sin rol asignado.
 * <p>
 * Muestra una vista informativa para aquellos usuarios que se han registrado
 * pero aún no han sido asignados a un rol o empresa por un administrador o
 * auditor.
 * </p>
 */
@Controller
public class RolPendienteController {

    /**
     * Muestra la página de espera de asignación de rol.
     *
     * @return Nombre de la vista de espera de rol.
     */
    @GetMapping("/esperarol")
    public String esperarRol() {
        return "esperarol";
    }
}
