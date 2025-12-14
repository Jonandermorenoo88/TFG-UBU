package es.ubu.gii.ISOAssetManager.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

/**
 * Controlador principal para la gestión de los cuestionarios de evaluación ISO
 * 27001.
 * <p>
 * Maneja la lógica de negocio relacionada con:
 * <ul>
 * <li>Navegación por grupos de controles (anexos).</li>
 * <li>Visualización y gestión de preguntas y respuestas.</li>
 * <li>Cálculo de puntuaciones y estadísticas.</li>
 * <li>Integración con Blockchain para la integridad de las respuestas.</li>
 * <li>Gestión de evidencias.</li>
 * <li>Exportación de resultados a Excel.</li>
 * </ul>
 * </p>
 */
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

    /**
     * Constructor con inyección de dependencias.
     *
     * @param categoriaRepo        Repositorio de categorías.
     * @param controlRepo          Repositorio de controles.
     * @param empresaRepo          Repositorio de empresas.
     * @param usuarioRepo          Repositorio de usuarios.
     * @param preguntaRepo         Repositorio de preguntas.
     * @param respuestaEmpresaRepo Repositorio de respuestas de empresa.
     * @param respuestaPosibleRepo Repositorio de opciones de respuesta.
     * @param evidenciaRepo        Repositorio de evidencias.
     * @param blockchainService    Servicio de blockchain.
     * @param bloqueRepo           Repositorio de bloques.
     */
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
     * Lista los controles de un grupo (anexo) específico para una empresa.
     * <p>
     * Calcula y muestra las puntuaciones medias obtenidas en cada control del
     * grupo.
     * </p>
     *
     * @param empresaId ID de la empresa.
     * @param grupo     Slug del grupo de controles (ej: "organizacionales",
     *                  "personas").
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista de lista de controles o página de error 404.
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
     * Muestra la pantalla de preguntas para un control específico.
     * <p>
     * Carga las preguntas del control, las respuestas existentes de la empresa,
     * las opciones de respuesta disponibles y las evidencias adjuntas.
     * </p>
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista de preguntas.
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
        // IMPORTANTE: el nombre coincide con preguntas.html
        model.addAttribute("evidenciasControl", evidenciasControl);

        // Calcular el slug del grupo (anexo) para el botón VOLVER
        // Buscamos el control para saber su categoría
        var controlOpt = controlRepo.findById(controlId);
        if (controlOpt.isPresent()) {
            String catId = controlOpt.get().getCategoria().getId();
            // Buscar la key (slug) que corresponde a este catId en SLUG_TO_CAT
            String grupoSlug = SLUG_TO_CAT.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(catId))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("organizacionales"); // fallback

            // Usamos "categorias" porque así lo ha puesto el usuario en el HTML
            model.addAttribute("categorias", grupoSlug);
        }

        return "preguntas";
    }

    /**
     * Muestra el formulario para crear una nueva pregunta personalizada en un
     * control.
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista del formulario de nueva pregunta.
     */
    @GetMapping("/control/{controlId:.+}/preguntas/nueva")
    public String nuevaPregunta(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        var control = controlRepo.findById(controlId).orElseThrow();
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("control", control);
        return "pregunta-nueva-min";
    }

    /**
     * Procesa la creación de una nueva pregunta personalizada.
     *
     * @param empresaId   ID de la empresa.
     * @param controlId   ID del control.
     * @param texto       Texto de la pregunta.
     * @param explicacion Explicación opcional de la pregunta.
     * @return Redirección a la lista de preguntas del control.
     */
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

    /**
     * Muestra la vista para seleccionar preguntas a eliminar.
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la vista de eliminación de preguntas.
     */
    @GetMapping("/control/{controlId:.+}/preguntas/eliminar")
    public String elegirPreguntaAEliminar(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("controlId", controlId);
        model.addAttribute("preguntas", preguntaRepo.findByControl_IdOrderByIdAsc(controlId));
        return "preguntas-eliminar";
    }

    /**
     * Elimina una pregunta y sus respuestas asociadas.
     *
     * @param empresaId  ID de la empresa.
     * @param controlId  ID del control.
     * @param preguntaId ID de la pregunta a eliminar.
     * @return Redirección a la lista de preguntas del control.
     */
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

    /**
     * Registra la respuesta de una empresa a una pregunta.
     * <p>
     * Si es una respuesta nueva, genera un bloque en la blockchain para garantizar
     * la integridad.
     * Si es una actualización, solo guarda el cambio en la base de datos
     * relacional.
     * </p>
     *
     * @param empresaId  ID de la empresa.
     * @param controlId  ID del control.
     * @param preguntaId ID de la pregunta.
     * @param opcionId   ID de la opción seleccionada.
     * @param auth       Información de autenticación del usuario.
     * @return Redirección a la lista de preguntas del control.
     */
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

        // Verificar si ya existe una respuesta
        Optional<RespuestaEmpresa> respuestaExistente = respuestaEmpresaRepo
                .findByEmpresa_IdAndPregunta_Id(empresaId, preguntaId);

        boolean esNueva = respuestaExistente.isEmpty();

        RespuestaEmpresa re = respuestaExistente.orElseGet(RespuestaEmpresa::new);

        re.setEmpresa(empresa);
        re.setPregunta(pregunta);
        re.setRespuesta(opcion);
        re.setUsuario(usuario);
        re.setFechaRespuesta(LocalDateTime.now());

        respuestaEmpresaRepo.save(re);

        // --- BLOCKCHAIN INTEGRATION ---
        // Solo registramos en blockchain si es una respuesta NUEVA
        // Si se modifica una respuesta existente, NO creamos un nuevo bloque
        // (esto mantiene la inmutabilidad de la cadena)
        if (esNueva) {
            blockchainService.crearBloque(re, usuario, principal);
        }
        // ------------------------------

        return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";
    }

    /**
     * Elimina una respuesta existente.
     * <p>
     * También elimina el bloque de blockchain asociado si existe, para mantener la
     * consistencia
     * (aunque esto invalida la cadena posterior, es necesario para la funcionalidad
     * de eliminar respuesta).
     * </p>
     *
     * @param empresaId  ID de la empresa.
     * @param controlId  ID del control.
     * @param preguntaId ID de la pregunta cuya respuesta se eliminará.
     * @return Redirección a la lista de preguntas del control.
     */
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

    /**
     * Muestra la vista de verificación de integridad de la blockchain global.
     *
     * @param model Modelo para pasar el resultado de la validación.
     * @return Nombre de la vista de verificación.
     */
    @GetMapping("/blockchain/verificar-vista")
    public String verificarBlockchainVista(Model model) {
        boolean valida = blockchainService.validarCadena();
        model.addAttribute("esValida", valida);
        return "verificar-blockchain";
    }

    /**
     * Muestra la vista de verificación de integridad para un control específico.
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @param model     Modelo para pasar el resultado de la validación.
     * @return Nombre de la vista de verificación.
     */
    @GetMapping("/control/{controlId}/blockchain/verificar-vista")
    public String verificarBlockchainControlVista(@PathVariable Long empresaId,
            @PathVariable String controlId,
            Model model) {
        boolean valida = blockchainService.validarIntegridadControl(controlId, empresaId);
        model.addAttribute("esValida", valida);
        model.addAttribute("controlId", controlId); // Para mostrar en la vista si se quiere
        return "verificar-blockchain";
    }

    /**
     * Endpoint API para verificar la integridad de la cadena de bloques.
     *
     * @return Mensaje de texto indicando si la cadena es válida o no.
     */
    @GetMapping("/blockchain/verificar")
    @ResponseBody
    public String verificarBlockchain() {
        boolean valida = blockchainService.validarCadena();
        return valida ? "INTEGRIDAD CORRECTA: La cadena de bloques es válida."
                : "ALERTA: La cadena de bloques ha sido manipulada.";
    }

    /**
     * Genera y descarga un archivo Excel con las respuestas de un control.
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @return ResponseEntity con el archivo Excel en formato byte array.
     * @throws java.io.IOException Si ocurre un error al generar el archivo.
     */
    @GetMapping("/control/{controlId}/exportar")
    public org.springframework.http.ResponseEntity<byte[]> exportarRespuestasExcel(@PathVariable Long empresaId,
            @PathVariable String controlId) throws java.io.IOException {

        // 1. Obtener datos
        List<Pregunta> preguntas = preguntaRepo.findByControl_IdOrderByIdAsc(controlId);
        List<RespuestaEmpresa> respuestas = respuestaEmpresaRepo.findByEmpresa_IdAndPregunta_Control_Id(empresaId,
                controlId);
        Map<Long, RespuestaEmpresa> mapaRespuestas = new HashMap<>();
        for (RespuestaEmpresa r : respuestas) {
            if (r.getPregunta() != null)
                mapaRespuestas.put(r.getPregunta().getId(), r);
        }

        // 2. Crear Excel
        try (org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Respuestas " + controlId);

            // Estilos
            org.apache.poi.ss.usermodel.CellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);

            // Cabecera
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
            String[] headers = { "ID Pregunta", "Pregunta", "Respuesta", "Puntuación", "Fecha" };
            for (int i = 0; i < headers.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowNum = 1;
            for (Pregunta p : preguntas) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getTexto());

                RespuestaEmpresa r = mapaRespuestas.get(p.getId());
                if (r != null && r.getRespuesta() != null) {
                    row.createCell(2).setCellValue(r.getRespuesta().getTextoOpcion());
                    row.createCell(3).setCellValue(r.getRespuesta().getBaremo());
                    row.createCell(4).setCellValue(r.getFechaRespuesta().toString());
                } else {
                    row.createCell(2).setCellValue("Sin responder");
                    row.createCell(3).setCellValue(0);
                    row.createCell(4).setCellValue("-");
                }
            }

            // Auto-size columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 3. Escribir a byte array
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            workbook.write(out);

            return org.springframework.http.ResponseEntity.ok()
                    .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=respuestas_" + controlId + ".xlsx")
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                    .body(out.toByteArray());
        }
    }
}
