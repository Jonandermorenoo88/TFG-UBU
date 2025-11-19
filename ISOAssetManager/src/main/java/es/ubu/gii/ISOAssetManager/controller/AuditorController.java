package es.ubu.gii.ISOAssetManager.controller;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.ubu.gii.ISOAssetManager.model.Departamento;
import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.DepartamentoRepository;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
@RequestMapping("/auditor")
@PreAuthorize("hasAnyRole('AUDITOR','ADMIN')") // opcional: permite también ADMIN
public class AuditorController {

    private static final Set<String> ROLES_OPERATIVOS = Set.of("RRHH","DIRECCION","FACILITIES","IT/TECNICO");

    private final UsuarioRepository usuarioRepo;
    private final EmpresaRepository empresaRepo;
    private final DepartamentoRepository deptoRepo;

    public AuditorController(UsuarioRepository usuarioRepo,
                             EmpresaRepository empresaRepo,
                             DepartamentoRepository deptoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.empresaRepo = empresaRepo;
        this.deptoRepo   = deptoRepo;
    }

    @GetMapping
    public String homeAuditor() {
        // Antes devolvías "panel" (que no existe como vista). Llévale al panel real del auditor:
        return "redirect:/panel-auditor";
    }

    /**
     * Listado de usuarios "operativos" (RRHH, DIRECCION, FACILITIES, IT/TECNICO)
     * para que el auditor les asigne empresa y, si aplica, departamento.
     */
    @GetMapping("/usuarios")
    public String gestionarUsuariosOperativos(Model model) {
        List<Usuario> usuariosOperativos = usuarioRepo.findAll().stream()
            .filter(u -> u.getRoles().stream()
                .anyMatch(r -> {
                    String nombre = r.getNombre() != null ? r.getNombre().trim().toUpperCase() : "";
                    return ROLES_OPERATIVOS.contains(nombre);
                })
            )
            .toList();

        List<Empresa> empresas = empresaRepo.findAll();

        model.addAttribute("usuarios", usuariosOperativos);
        model.addAttribute("empresas", empresas);
        return "auditor-usuarios";
    }

    /**
     * Asignar EMPRESA a un usuario operativo.
     */
    @PostMapping("/usuarios/{id}/asignar-empresa")
    public String asignarEmpresa(@PathVariable Long id,
                                 @RequestParam Long empresaId,
                                 RedirectAttributes ra) {
        Usuario u = usuarioRepo.findById(id).orElse(null);
        Empresa e = empresaRepo.findById(empresaId).orElse(null);

        if (u == null || e == null) {
            ra.addFlashAttribute("error", "Usuario o empresa no encontrados.");
            return "redirect:/auditor/usuarios";
        }

        // Asegura que el usuario es de uno de los roles operativos
        boolean esOperativo = u.getRoles().stream().anyMatch(r -> {
            String nombre = r.getNombre() != null ? r.getNombre().trim().toUpperCase() : "";
            return ROLES_OPERATIVOS.contains(nombre);
        });

        if (!esOperativo) {
            ra.addFlashAttribute("error", "Solo se puede asignar empresa a usuarios de RRHH/DIRECCION/FACILITIES/IT/TECNICO.");
            return "redirect:/auditor/usuarios";
        }

        u.setEmpresa(e);
        // (Opcional) si cambias de empresa, limpia el departamento previo
        u.setDepartamento(null);
        usuarioRepo.save(u);

        ra.addFlashAttribute("ok", "Empresa asignada a " + u.getNombre() + ".");
        return "redirect:/auditor/usuarios";
    }

    /**
     * Asignar DEPARTAMENTO (creándolo si no existe en esa empresa).
     */
    @PostMapping("/usuarios/{id}/asignar-departamento")
    public String asignarDepartamento(@PathVariable Long id,
                                      @RequestParam String departamentoNombre,
                                      RedirectAttributes ra) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario == null) {
            ra.addFlashAttribute("error", "Usuario no encontrado.");
            return "redirect:/auditor/usuarios";
        }

        if (usuario.getEmpresa() == null) {
            ra.addFlashAttribute("error", "Asigna primero una empresa al usuario.");
            return "redirect:/auditor/usuarios";
        }

        String nombreDep = departamentoNombre != null ? departamentoNombre.trim() : "";
        if (nombreDep.isEmpty()) {
            ra.addFlashAttribute("error", "El nombre del departamento no puede estar vacío.");
            return "redirect:/auditor/usuarios";
        }

        // Usa tu método actual del repo; si quieres hacerlo case-insensitive, cambia el repo a IgnoreCase
        Departamento dep = deptoRepo
                .findByDescripcionAndEmpresaId(nombreDep, usuario.getEmpresa().getId())
                .orElseGet(() -> {
                    Departamento d = new Departamento();
                    d.setDescripcion(nombreDep);
                    d.setEmpresa(usuario.getEmpresa());
                    return deptoRepo.save(d);
                });

        usuario.setDepartamento(dep);
        usuarioRepo.save(usuario);

        ra.addFlashAttribute("ok", "Departamento asignado correctamente.");
        return "redirect:/auditor/usuarios";
    }
}