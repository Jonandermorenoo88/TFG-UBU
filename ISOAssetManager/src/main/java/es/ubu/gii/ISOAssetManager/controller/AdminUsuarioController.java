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

@Controller
@RequestMapping("/usuarios")
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public AdminUsuarioController(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    // Mostrar todos los usuarios + lista de roles para el selector
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Rol> roles = rolRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        return "modificarusuario";
    }

    // Asignar rol a usuario (evita duplicados)
    @PostMapping("/asignar-rol")
    public String asignarRol(@RequestParam Long usuarioId, @RequestParam String rolNombre) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(rolNombre)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Set<Rol> roles = new HashSet<>(usuario.getRoles());
        if (!roles.contains(rol)) {          // <-- evita duplicados
            roles.add(rol);
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
        }

        return "redirect:/usuarios";
    }

    // Eliminar usuario
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    // Eliminar un rol del usuario (idempotente)
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