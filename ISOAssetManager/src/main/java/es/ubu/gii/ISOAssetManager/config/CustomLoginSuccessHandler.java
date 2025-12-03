package es.ubu.gii.ISOAssetManager.config;

import java.io.IOException;
import java.util.function.Predicate;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manejador personalizado para el éxito de la autenticación.
 * <p>
 * Esta clase determina la redirección del usuario después de un inicio de
 * sesión exitoso
 * basándose en los roles asignados.
 * </p>
 */
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Método invocado cuando la autenticación es exitosa.
     * <p>
     * Redirige al usuario a diferentes rutas según su rol:
     * <ul>
     * <li>ADMIN -> /panel-admin</li>
     * <li>AUDITOR -> /panel-auditor</li>
     * <li>Roles operativos (RRHH, DIRECCION, FACILITIES, IT/TECNICO) ->
     * /empresas</li>
     * <li>Sin rol reconocido -> /esperarol</li>
     * </ul>
     * </p>
     *
     * @param request        La solicitud HTTP.
     * @param response       La respuesta HTTP.
     * @param authentication La información de autenticación del usuario.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        // Log para depurar qué authorities llegan realmente
        System.out.println("Authorities tras login -> " + authentication.getAuthorities());

        Predicate<String> has = role -> authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ROLE_" + role.trim()));

        // Admin/Auditor a sus paneles
        if (has.test("ADMIN")) {
            response.sendRedirect("/panel-admin");
            return;
        }
        if (has.test("AUDITOR")) {
            response.sendRedirect("/panel-auditor");
            return;
        }

        // Roles operativos a /empresas
        if (has.test("RRHH")) {
            response.sendRedirect("/empresas");
            return;
        }
        if (has.test("DIRECCION")) {
            response.sendRedirect("/empresas");
            return;
        }
        if (has.test("FACILITIES")) {
            response.sendRedirect("/empresas");
            return;
        }
        if (has.test("IT/TECNICO")) {
            response.sendRedirect("/empresas");
            return;
        }

        // Sin rol reconocido → pendiente
        response.sendRedirect("/esperarol");
    }
}
