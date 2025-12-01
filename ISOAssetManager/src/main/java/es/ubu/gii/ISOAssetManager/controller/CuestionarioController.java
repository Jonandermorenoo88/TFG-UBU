package es.ubu.gii.ISOAssetManager.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.model.Categoria;
import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.model.Pregunta;
import es.ubu.gii.ISOAssetManager.model.RespuestaEmpresa;
import es.ubu.gii.ISOAssetManager.model.RespuestaPosible;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.model.Evidencia;
import es.ubu.gii.ISOAssetManager.repository.CategoriaRepository;
import es.ubu.gii.ISOAssetManager.repository.ControlRepository;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.PreguntaRepository;
import es.ubu.gii.ISOAssetManager.repository.RespuestaEmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.RespuestaPosibleRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;
import es.ubu.gii.ISOAssetManager.repository.EvidenciaRepository;

@Controller
@RequestMapping("/empresas/{empresaId}/cuestionario")
public class CuestionarioController {

    private final CategoriaRepository categoriaRepo;
    private final ControlRepository controlRepo;
    private final EmpresaRepository empresaRepo;
    private final UsuarioRepository usuarioRepo;
    private final PreguntaRepository preguntaRepo;
    private final RespuestaEmpresaRepository respuestaEmpresaRepo;
    private final RespuestaPosibleRepository respuestaPosibleRepo;
    private final EvidenciaRepository evidenciaRepo;
    private final es.ubu.gii.ISOAssetManager.service.BlockchainService blockchainService;
    private final es.ubu.gii.ISOAssetManager.repository.BloqueRepository bloqueRepo;

    public CuestionarioController(CategoriaRepository categoriaRepo,
            ControlRepository controlRepo,
            EmpresaRepository empresaRepo,
            UsuarioRepository usuarioRepo,
            PreguntaRepository preguntaRepo,
            RespuestaEmpresaRepository respuestaEmpresaRepo,
            RespuestaPosibleRepository respuestaPosibleRepo,
            EvidenciaRepository evidenciaRepo,
            es.ubu.gii.ISOAssetManager.service.BlockchainService blockchainService,
            es.ubu.gii.ISOAssetManager.repository.BloqueRepository bloqueRepo) {
        this.categoriaRepo = categoriaRepo;
        this.controlRepo = controlRepo;
        this.empresaRepo = empresaRepo;
        this.usuarioRepo = usuarioRepo;
        this.preguntaRepo = preguntaRepo;
        this.respuestaEmpresaRepo = respuestaEmpresaRepo;
        this.respuestaPosibleRepo = respuestaPosibleRepo;
        this.evidenciaRepo = evidenciaRepo;
        this.blockchainService = blockchainService;
        this.bloqueRepo = bloqueRepo;
    }

    // Mapeo de slug -> id de categoría (A5/A6/A7/A8)
    private static final Map<String, String> SLUG_TO_CAT = Map.of(
            "organizacionales", "A5",
            "personas", "A6",
            "fisicos", "A7",
            "tecnologicos", "A8");

    /**
     * Lista los controles de un grupo (anexo) para una empresa
     * y añade el mapa de medias por control (0..100).
     */
    @GetMapping("/grupo/{grupo}")
    public String listarControlesPorGrupo(@PathVariable Long empresaId,
            @PathVariable String grupo,
            Model model) {
        String catId = SLUG_TO_CAT.get(grupo);
        if (catId == null) {
            return "errors/404";
        }

        Categoria cat = categoriaRepo.findById(catId).orElse(null);

        var controles = controlRepo.findByCategoria_IdOrderByOrdenAscIdAsc(catId);

        List<RespuestaEmpresa> respuestasCat = respuestaEmpresaRepo.findByEmpresaIdAndCategoriaId(empresaId, catId);

        Map<String, Integer> scores = respuestasCat.stream()
                .filter(re -> re.getPregunta() != null
                        && re.getPregunta().getControl() != null
                        && re.getRespuesta() != null)
                .collect(Collectors.groupingBy(
                        re -> re.getPregunta().getControl().getId(), // p.ej. "A5.1"
                        Collectors.collectingAndThen(
                                Collectors.averagingDouble(re -> re.getRespuesta().getBaremo()),
                                avg -> (int) Math.round(avg))));

        model.addAttribute("empresaId", empresaId);
        model.addAttribute("anexo", cat);
        model.addAttribute("controles", controles);
        model.addAttribute("scores", scores);

        return "controles";
    }

    /**
     * Pantalla de preguntas de un control.
     * Vista: templates/preguntas.html
     */
    @GetMapping("/control/{controlId:.+}/preguntas")
    public String preguntasDeControl(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {

        // Preguntas del control
        List<Pregunta> preguntas = preguntaRepo.findByControl_IdOrderByIdAsc(controlId);

        // Respuestas ya dadas por esta empresa para este control
        List<RespuestaEmpresa> yaRespondidas = respuestaEmpresaRepo.findByEmpresa_IdAndPregunta_Control_Id(empresaId,
                controlId);

        Map<Long, Long> seleccion = new HashMap<>();
        Map<Long, RespuestaEmpresa> respuestas = new HashMap<>();
        for (RespuestaEmpresa re : yaRespondidas) {
            if (re.getPregunta() != null && re.getRespuesta() != null) {
                Long pid = re.getPregunta().getId();
                seleccion.put(pid, re.getRespuesta().getId());
                respuestas.put(pid, re);
            }
        }

        // Opciones globales de respuesta (SI/NO/N.A., etc.)
        var opcionesGlobales = respuestaPosibleRepo.findAllByOrderByOrdenAscIdAsc();

        // 🔎 Evidencias asociadas a ESTE control y ESTA empresa (no por pregunta)
        List<Evidencia> evidenciasControl = evidenciaRepo.findByEmpresa_IdAndControl_IdOrderByFechaSubidaDesc(empresaId,
                controlId);

        model.addAttribute("empresaId", empresaId);
        model.addAttribute("controlId", controlId);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("seleccion", seleccion);
        model.addAttribute("respuestas", respuestas);
        model.addAttribute("opciones", opcionesGlobales);
        // IMPORTANTE: el nombre coincide con preguntas.html
        model.addAttribute("evidenciasControl", evidenciasControl);

        return "preguntas";
    }

    @GetMapping("/control/{controlId:.+}/preguntas/nueva")
    public String nuevaPregunta(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        var control = controlRepo.findById(controlId).orElseThrow();
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("control", control);
        return "pregunta-nueva-min";
    }

    @PostMapping("/control/{controlId:.+}/preguntas")
    public String crearPregunta(@PathVariable Long empresaId,
            @PathVariable String controlId,
            @RequestParam String texto,
            @RequestParam(required = false) String explicacion) {
        var control = controlRepo.findById(controlId).orElseThrow();
        var q = new Pregunta();
        q.setTexto(texto);
        if (explicacion != null && !explicacion.isBlank()) {
            q.setExplicacion(explicacion.trim());
        }
        q.setControl(control);
        preguntaRepo.save(q);

        return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";
    }

    @GetMapping("/control/{controlId:.+}/preguntas/eliminar")
    public String elegirPreguntaAEliminar(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("controlId", controlId);
        model.addAttribute("preguntas", preguntaRepo.findByControl_IdOrderByIdAsc(controlId));
        return "preguntas-eliminar";
    }

    @PostMapping("/control/{controlId:.+}/preguntas/eliminar")
    public String eliminarPregunta(@PathVariable Long empresaId,
            @PathVariable String controlId,
            @RequestParam Long preguntaId) {
        // limpia respuestas de esa pregunta
        respuestaEmpresaRepo.deleteByPreguntaId(preguntaId);
        // borra la pregunta
        preguntaRepo.deleteById(preguntaId);
        return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";
    }

    @PostMapping("/control/{controlId:.+}/responder")
    public String responder(@PathVariable Long empresaId,
            @PathVariable String controlId,
            @RequestParam Long preguntaId,
            @RequestParam Long opcionId,
            Authentication auth) {

        Empresa empresa = empresaRepo.findById(empresaId).orElseThrow();

        Usuario usuario = null;
        Object principal = null;
        if (auth != null) {
            usuario = usuarioRepo.findByEmail(auth.getName()).orElse(null);
            principal = auth.getPrincipal();
        }

        Pregunta pregunta = preguntaRepo.findById(preguntaId).orElseThrow();
        RespuestaPosible opcion = respuestaPosibleRepo.findById(opcionId).orElseThrow();

        RespuestaEmpresa re = respuestaEmpresaRepo
                .findByEmpresa_IdAndPregunta_Id(empresaId, preguntaId)
                .orElseGet(RespuestaEmpresa::new);

        re.setEmpresa(empresa);
        re.setPregunta(pregunta);
        re.setRespuesta(opcion);
        re.setUsuario(usuario);
        re.setFechaRespuesta(LocalDateTime.now());

        respuestaEmpresaRepo.save(re);

        // --- BLOCKCHAIN INTEGRATION ---
        // Registramos la respuesta en la cadena de bloques
        blockchainService.crearBloque(re, usuario, principal);
        // ------------------------------

        return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";
    }

    @PostMapping("/control/{controlId:.+}/preguntas/{preguntaId}/respuesta/eliminar")
    public String eliminarRespuesta(@PathVariable Long empresaId,
            @PathVariable String controlId,
            @PathVariable Long preguntaId) {

        // Buscar la respuesta primero
        RespuestaEmpresa re = respuestaEmpresaRepo.findByEmpresa_IdAndPregunta_Id(empresaId, preguntaId).orElse(null);

        if (re != null) {
            // Eliminar el bloque asociado si existe (esto romperá la cadena, pero evita el
            // error 500)
            bloqueRepo.findByRespuestaId(re.getId()).ifPresent(bloqueRepo::delete);

            // Eliminar la respuesta
            respuestaEmpresaRepo.delete(re);
        }

        return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";
    }

    // --- Endpoint para verificar la cadena (Vista) ---
    @GetMapping("/blockchain/verificar-vista")
    public String verificarBlockchainVista(Model model) {
        boolean valida = blockchainService.validarCadena();
        model.addAttribute("esValida", valida);
        return "verificar-blockchain";
    }

    // --- Endpoint para verificar la cadena POR CONTROL (Vista) ---
    @GetMapping("/control/{controlId}/blockchain/verificar-vista")
    public String verificarBlockchainControlVista(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        boolean valida = blockchainService.validarIntegridadControl(controlId, empresaId);
        model.addAttribute("esValida", valida);
        model.addAttribute("controlId", controlId); // Para mostrar en la vista si se quiere
        return "verificar-blockchain";
    }

    // --- Endpoint para verificar la cadena (API/Demo) ---
    @GetMapping("/blockchain/verificar")
    @ResponseBody
    public String verificarBlockchain() {
        boolean valida = blockchainService.validarCadena();
        return valida ? "INTEGRIDAD CORRECTA: La cadena de bloques es válida."
                : "ALERTA: La cadena de bloques ha sido manipulada.";
    }
}
