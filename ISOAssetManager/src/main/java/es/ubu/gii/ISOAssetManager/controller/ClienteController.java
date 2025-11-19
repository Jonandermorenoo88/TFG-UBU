package es.ubu.gii.ISOAssetManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private final UsuarioRepository usuarioRepository;

    public ClienteController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/cliente")
    public String clienteHome(Model model, Authentication auth, RedirectAttributes ra) {
        String email = auth.getName();
        Usuario u = usuarioRepository.findByEmail(email).orElse(null);

        if (u == null) {
            log.error("ClienteController: usuario no encontrado para email={}", email);
            ra.addFlashAttribute("error", "No se encontró el usuario. Vuelve a iniciar sesión.");
            return "redirect:/login";
        }

        boolean asignado = (u.getEmpresa() != null);

        if (asignado) {
            return "redirect:/cliente/empresa";
        }

        model.addAttribute("usuario", u);
        model.addAttribute("asignado", false);
        return "cliente-panel";
    }

    @GetMapping("/cliente/empresa")
    public String clienteEmpresa(Model model, Authentication auth, RedirectAttributes ra) {
        String email = auth.getName();
        Usuario u = usuarioRepository.findByEmail(email).orElse(null);

        if (u == null) {
            log.error("ClienteController: usuario no encontrado para email={}", email);
            ra.addFlashAttribute("error", "No se encontró el usuario. Vuelve a iniciar sesión.");
            return "redirect:/login";
        }
        if (u.getEmpresa() == null) {
            ra.addFlashAttribute("error", "Aún no tienes una empresa asignada. Espera a que el auditor te la asigne.");
            return "redirect:/cliente";
        }

        model.addAttribute("usuario", u);
        model.addAttribute("empresa", u.getEmpresa());
        model.addAttribute("departamento", u.getDepartamento());
        return "cliente-empresa";
    }
}