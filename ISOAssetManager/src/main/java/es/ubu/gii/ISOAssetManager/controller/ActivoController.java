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

/**
 * Controlador para la gestión de activos de información.
 * <p>
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para los
 * activos
 * asociados a las empresas del sistema.
 * </p>
 */
@Controller
@RequestMapping("/activos")
public class ActivoController {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ActivoRepository activoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Muestra el formulario para crear un nuevo activo.
     *
     * @param empresaId ID de la empresa a la que pertenecerá el activo.
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista del formulario de nuevo activo.
     * @throws IllegalArgumentException Si la empresa no existe.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoActivo(@RequestParam Long empresaId, Model model) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        model.addAttribute("empresaId", empresaId);
        model.addAttribute("empresa", empresa);
        model.addAttribute("usuariosEmpresa", usuarioRepository.findByEmpresa_Id(empresaId));
        return "activos-nuevo";
    }

    /**
     * Muestra la lista de activos de una empresa específica.
     *
     * @param id    ID de la empresa.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista de activos de la empresa.
     * @throws RuntimeException Si la empresa no existe.
     */
    @GetMapping("/empresa/{id}")
    public String verActivosDeEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        model.addAttribute("empresa", empresa);
        model.addAttribute("activos", activoRepository.findByEmpresaId(id));
        return "activosempresa";
    }

    /**
     * Guarda un nuevo activo en la base de datos.
     *
     * @param empresaId     ID de la empresa propietaria del activo.
     * @param nombre        Nombre del activo.
     * @param tipo          Tipo de activo (ej: Hardware, Software, Datos).
     * @param descripcion   Descripción detallada del activo.
     * @param valor         Valor económico del activo.
     * @param responsableId ID del usuario responsable del activo.
     * @return Redirección a la lista de activos de la empresa.
     * @throws IllegalArgumentException Si la empresa o el responsable no existen.
     */
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

    /**
     * Muestra el formulario de edición de un activo existente.
     *
     * @param id    ID del activo a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista del formulario de edición.
     * @throws RuntimeException Si el activo no existe.
     */
    @GetMapping("/editar/{id}")
    public String editarActivo(@PathVariable Long id, Model model) {
        Activo activo = activoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activo no encontrado"));
        model.addAttribute("activo", activo);
        model.addAttribute("usuariosEmpresa",
                usuarioRepository.findByEmpresa_Id(activo.getEmpresa().getId()));
        return "activo-editar";
    }

    /**
     * Elimina un activo de la base de datos.
     *
     * @param id ID del activo a eliminar.
     * @return Redirección a la lista de activos de la empresa.
     * @throws RuntimeException Si el activo no existe.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarActivo(@PathVariable Long id) {
        Activo activo = activoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activo no encontrado"));
        Long empresaId = activo.getEmpresa().getId();
        activoRepository.deleteById(id);
        return "redirect:/activos/empresa/" + empresaId;
    }

    /**
     * Actualiza los datos de un activo existente.
     *
     * @param id            ID del activo a actualizar.
     * @param empresaId     ID de la empresa (para redirección).
     * @param nombre        Nuevo nombre del activo.
     * @param tipo          Nuevo tipo del activo.
     * @param descripcion   Nueva descripción del activo.
     * @param valor         Nuevo valor económico del activo.
     * @param responsableId ID del nuevo responsable del activo.
     * @return Redirección a la lista de activos de la empresa.
     * @throws IllegalArgumentException Si el activo o el responsable no existen.
     */
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
