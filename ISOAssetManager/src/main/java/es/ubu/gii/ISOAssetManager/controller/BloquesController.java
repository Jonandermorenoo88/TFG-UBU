// CuestionarioController.java
package es.ubu.gii.ISOAssetManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;

@Controller
@RequestMapping("/empresas")
public class BloquesController {

    @Autowired
    private EmpresaRepository empresaRepo;

    @GetMapping("/{empresaId}/bloques")
    public String elegirBloque(@PathVariable Long empresaId, Model model) {
        model.addAttribute("empresa", empresaRepo.findById(empresaId).orElseThrow());
        return "bloques";
    }
}