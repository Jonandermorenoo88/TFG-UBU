package es.ubu.gii.ISOAssetManager.controller;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;
import es.ubu.gii.ISOAssetManager.util.ValidarFormatoPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

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

        // 1) Validar formato de contraseña según la política definida
        if (!ValidarFormatoPassword.esPasswordValida(password)) {
            model.addAttribute("error",
                    "La contraseña no cumple los requisitos de seguridad. " +
                    "Debe tener entre 8 y 32 caracteres y cumplir al menos 3 de los 4 criterios: " +
                    "mayúsculas, minúsculas, números y caracteres especiales. " +
                    "Además, no puede contener más de dos caracteres consecutivos iguales.");
            return "registro";
        }

        // 2) Comprobar si ya existe un usuario con ese email
        if (usuarioRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Ya existe un usuario con ese correo.");
            return "registro";
        }

        try {
            // 3) Crear el nuevo usuario
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setEmail(email);
            u.setPassword(passwordEncoder.encode(password));

            // 4) Generar par de claves RSA SOLO en el registro
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();

                // Guardamos SOLO la clave pública en BD
                u.setPublicKey(keyPair.getPublic().getEncoded());

            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("Error generando claves RSA para el usuario", e);
            }

            // 5) Guardar el usuario
            usuarioRepository.saveAndFlush(u);
            System.out.println("[/registro] INSERT OK para " + email);
            return "redirect:/login";

        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            ex.printStackTrace();
            model.addAttribute("error",
                    "No se pudo guardar (restricción de BD). Revísalo en consola.");
            return "registro";

        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("error",
                    "Error inesperado al guardar. Mira consola.");
            return "registro";
        }
    }
}
