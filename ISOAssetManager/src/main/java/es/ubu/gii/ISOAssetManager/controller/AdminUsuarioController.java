package es.ubu.gii.ISOAssetManager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.model.Rol;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.RolRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

/**
 * Controlador para la administración de usuarios del sistema.
 * <p>
 * Proporciona funcionalidades de gestión de usuarios y asignación de roles,
 * accesible únicamente para usuarios con rol de administrador.
 * </p>
 */
@Controller
@RequestMapping("/usuarios")
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param usuarioRepository Repositorio de usuarios.
     * @param rolRepository     Repositorio de roles.
     */
    public AdminUsuarioController(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    /**
     * Muestra la lista de todos los usuarios del sistema con sus roles.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista de administración de usuarios.
     */
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Rol> roles = rolRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        return "modificarusuario";
    }

    /**
     * Asigna un rol a un usuario.
     * <p>
     * Evita duplicados verificando si el usuario ya tiene el rol antes de
     * asignarlo.
     * </p>
     *
     * @param usuarioId ID del usuario al que se asignará el rol.
     * @param rolNombre Nombre del rol a asignar.
     * @return Redirección a la lista de usuarios.
     * @throws RuntimeException Si el usuario o el rol no existen.
     */
    @PostMapping("/asignar-rol")
    public String asignarRol(@RequestParam Long usuarioId, @RequestParam String rolNombre) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Set<Rol> roles = new HashSet<>(usuario.getRoles());
        if (!roles.contains(rol)) { // <-- evita duplicados
            roles.add(rol);
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
        }

        return "redirect:/usuarios";
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param id ID del usuario a eliminar.
     * @return Redirección a la lista de usuarios.
     */
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    /**
     * Elimina un rol de un usuario.
     * <p>
     * Operación idempotente: si el usuario no tiene el rol, no realiza ninguna
     * acción.
     * </p>
     *
     * @param usuarioId ID del usuario del que se eliminará el rol.
     * @param rolNombre Nombre del rol a eliminar.
     * @return Redirección a la lista de usuarios.
     * @throws RuntimeException Si el usuario o el rol no existen.
     */
    @PostMapping("/eliminar-rol")
    public String eliminarRol(@RequestParam Long usuarioId, @RequestParam String rolNombre) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (usuario.getRoles().contains(rol)) {
            usuario.getRoles().remove(rol);
            usuarioRepository.save(usuario);
        }

        return "redirect:/usuarios";
    }
}
