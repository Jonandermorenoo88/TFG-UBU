package es.ubu.gii.ISOAssetManager.controller;

import es.ubu.gii.ISOAssetManager.config.details.SuperCustomerUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class PanelController {

    /**
     * Redirección genérica desde /panel según el rol.
     */
    @GetMapping("/panel")
    public String panelRedirect(@AuthenticationPrincipal SuperCustomerUserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        boolean esAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean esAuditor = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_AUDITOR"));

        if (esAdmin) {
            return "redirect:/panel-admin";
        } else if (esAuditor) {
            return "redirect:/panel-auditor";
        }

        return "redirect:/inicio";
    }

    /**
     * Panel del administrador.
     */
    @GetMapping("/panel-admin")
    public String panelAdmin(Model model,
                             @AuthenticationPrincipal SuperCustomerUserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        boolean esAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!esAdmin) {
            return "redirect:/inicio";
        }

        model.addAttribute("usuario", userDetails.getUsuario());
        model.addAttribute("esAdmin", true);
        return "panel-admin";
    }

    /**
     * Panel del auditor.
     */
    @GetMapping("/panel-auditor")
    public String panelAuditor(Model model,
                               @AuthenticationPrincipal SuperCustomerUserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        boolean esAuditor = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_AUDITOR"));

        if (!esAuditor) {
            return "redirect:/inicio";
        }

        model.addAttribute("usuario", userDetails.getUsuario());
        model.addAttribute("esAuditor", true);
        return "panel-auditor";
    }
}
