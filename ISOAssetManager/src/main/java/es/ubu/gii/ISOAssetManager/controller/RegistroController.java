package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre,
                                @RequestParam String email,
                                @RequestParam String password,
                                Model model) {
        System.out.println("[/registro] Intento de alta: " + email);

        if (usuarioRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Ya existe un usuario con ese correo.");
            return "registro";
        }

        try {
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setEmail(email);
            u.setPassword(passwordEncoder.encode(password));


            usuarioRepository.saveAndFlush(u);
            System.out.println("[/registro] INSERT OK para " + email);
            return "redirect:/login";
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            ex.printStackTrace();
            model.addAttribute("error", "No se pudo guardar (restricción de BD). Revísalo en consola.");
            return "registro";
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("error", "Error inesperado al guardar. Mira consola.");
            return "registro";
        }
    }
}


