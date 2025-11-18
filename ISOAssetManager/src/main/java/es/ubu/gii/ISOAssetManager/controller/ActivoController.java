package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.model.Activo;
import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.ActivoRepository;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
@RequestMapping("/activos")
public class ActivoController {

    @Autowired private EmpresaRepository empresaRepository;
    @Autowired private ActivoRepository  activoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoActivo(@RequestParam Long empresaId, Model model) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        model.addAttribute("empresaId", empresaId);
        model.addAttribute("empresa", empresa);
        model.addAttribute("usuariosEmpresa", usuarioRepository.findByEmpresa_Id(empresaId));
        return "activos-nuevo";
    }

    @GetMapping("/empresa/{id}")
    public String verActivosDeEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        model.addAttribute("empresa", empresa);
        model.addAttribute("activos", activoRepository.findByEmpresaId(id));
        return "activosempresa";
    }

    @PostMapping("/guardar")
    public String guardarActivo(@RequestParam Long empresaId,
                                @RequestParam String nombre,
                                @RequestParam String tipo,
                                @RequestParam String descripcion,
                                @RequestParam double valor,
                                @RequestParam Long responsableId) {

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));
        Usuario responsable = usuarioRepository.findById(responsableId)
                .orElseThrow(() -> new IllegalArgumentException("Responsable no encontrado"));

        Activo activo = new Activo();
        activo.setNombre(nombre);
        activo.setTipo(tipo);
        activo.setDescripcion(descripcion);
        activo.setValor(valor);
        activo.setEmpresa(empresa);
        activo.setResponsable(responsable);

        activoRepository.save(activo);

        return "redirect:/activos/empresa/" + empresaId;
    }

    @GetMapping("/editar/{id}")
    public String editarActivo(@PathVariable Long id, Model model) {
        Activo activo = activoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Activo no encontrado"));
        model.addAttribute("activo", activo);
        model.addAttribute("usuariosEmpresa",
                usuarioRepository.findByEmpresa_Id(activo.getEmpresa().getId()));
        return "activo-editar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarActivo(@PathVariable Long id) {
        Activo activo = activoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Activo no encontrado"));
        Long empresaId = activo.getEmpresa().getId();
        activoRepository.deleteById(id);
        return "redirect:/activos/empresa/" + empresaId;
    }

    @PostMapping("/actualizar")
    public String actualizarActivo(@RequestParam Long id,
                                   @RequestParam Long empresaId,
                                   @RequestParam String nombre,
                                   @RequestParam String tipo,
                                   @RequestParam String descripcion,
                                   @RequestParam double valor,
                                   @RequestParam Long responsableId) {

        Activo activo = activoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Activo no encontrado"));
        Usuario responsable = usuarioRepository.findById(responsableId)
            .orElseThrow(() -> new IllegalArgumentException("Responsable no encontrado"));

        activo.setNombre(nombre);
        activo.setTipo(tipo);
        activo.setDescripcion(descripcion);
        activo.setValor(valor);
        activo.setResponsable(responsable);

        activoRepository.save(activo);

        return "redirect:/activos/empresa/" + empresaId;
    }
}