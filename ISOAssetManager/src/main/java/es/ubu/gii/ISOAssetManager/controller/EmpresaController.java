package es.ubu.gii.ISOAssetManager.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

/**
 * Controlador para la gestión de empresas.
 * <p>
 * Permite listar, crear y eliminar empresas.
 * La visibilidad y permisos dependen del rol del usuario (ADMIN/AUDITOR vs
 * Roles Operativos).
 * </p>
 */
@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private static final Set<String> ROLES_OPERATIVOS = Set.of("RRHH", "DIRECCION", "FACILITIES", "IT/TECNICO");

    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param empresaRepository Repositorio de empresas.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public EmpresaController(EmpresaRepository empresaRepository,
            UsuarioRepository usuarioRepository) {
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Muestra el listado de empresas.
     * <p>
     * El comportamiento varía según el rol:
     * <ul>
     * <li><b>ADMIN / AUDITOR:</b> Ven todas las empresas registradas.</li>
     * <li><b>Roles Operativos:</b> Ven solo su empresa asignada. Si no tienen
     * empresa, se muestra una pantalla de espera.</li>
     * </ul>
     * </p>
     *
     * @param model Modelo para pasar datos a la vista.
     * @param auth  Información de autenticación del usuario actual.
     * @return Nombre de la vista de lista de empresas o panel de cliente.
     */
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR','RRHH','DIRECCION','FACILITIES','IT/TECNICO')")
    @GetMapping
    public String listar(Model model, Authentication auth) {
        // ¿Admin/Auditor?
        boolean esAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean esAuditor = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_AUDITOR"));
        if (esAdmin || esAuditor) {
            model.addAttribute("empresas", empresaRepository.findAll());
            return "empresa";
        }

        // Roles operativos -> una sola empresa o pendiente
        Usuario u = usuarioRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        boolean esOperativo = u.getRoles().stream()
                .anyMatch(r -> r.getNombre() != null && ROLES_OPERATIVOS.contains(r.getNombre().trim().toUpperCase()));

        if (!esOperativo) {
            // No reconocido: vuelve a "pendiente"
            return "cliente-panel";
        }

        if (u.getEmpresa() == null) {
            // Sin empresa asignada por el auditor -> pantalla de espera
            return "cliente-panel";
        }

        model.addAttribute("empresas", List.of(u.getEmpresa()));
        return "empresa";
    }

    /**
     * Muestra el formulario para crear una nueva empresa.
     * <p>
     * Accesible solo para administradores y auditores.
     * </p>
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista del formulario de nueva empresa.
     */
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @GetMapping("/nueva")
    public String formNueva(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "añadirempresa";
    }

    /**
     * Guarda una nueva empresa en el sistema.
     * <p>
     * Accesible solo para administradores y auditores.
     * </p>
     *
     * @param nombre    Nombre de la empresa.
     * @param sector    Sector de actividad de la empresa.
     * @param direccion Dirección física de la empresa.
     * @param ra        Atributos para mensajes flash (éxito).
     * @return Redirección a la lista de empresas.
     */
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @PostMapping("/guardar")
    public String guardar(@RequestParam String nombre,
            @RequestParam String sector,
            @RequestParam String direccion,
            RedirectAttributes ra) {
        Empresa e = new Empresa();
        e.setNombre(nombre);
        e.setSector(sector);
        e.setDireccion(direccion);
        empresaRepository.save(e);
        ra.addFlashAttribute("ok", "Empresa creada correctamente.");
        return "redirect:/empresas";
    }

    /**
     * Elimina una empresa del sistema.
     * <p>
     * Accesible solo para administradores y auditores.
     * </p>
     *
     * @param id ID de la empresa a eliminar.
     * @param ra Atributos para mensajes flash (éxito).
     * @return Redirección a la lista de empresas.
     * @throws ResponseStatusException Si la empresa no existe (404).
     */
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        Empresa e = empresaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));
        empresaRepository.delete(e);
        ra.addFlashAttribute("ok", "Empresa eliminada.");
        return "redirect:/empresas";
    }
}
