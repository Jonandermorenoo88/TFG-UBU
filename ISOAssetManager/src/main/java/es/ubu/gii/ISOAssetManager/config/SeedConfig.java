package es.ubu.gii.ISOAssetManager.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.ubu.gii.ISOAssetManager.model.Categoria;
import es.ubu.gii.ISOAssetManager.model.Control;
import es.ubu.gii.ISOAssetManager.model.RespuestaPosible;
import es.ubu.gii.ISOAssetManager.repository.CategoriaRepository;
import es.ubu.gii.ISOAssetManager.repository.ControlRepository;
import es.ubu.gii.ISOAssetManager.repository.RespuestaPosibleRepository;

/**
 * Configuración de carga inicial de datos (seed data) para la aplicación.
 * <p>
 * Esta clase se encarga de poblar la base de datos con las categorías y
 * controles
 * definidos en la norma ISO/IEC 27001:2022 (Anexos A5-A8), así como las
 * respuestas
 * posibles para los cuestionarios de evaluación.
 * </p>
 * <p>
 * Los datos se cargan automáticamente al iniciar la aplicación mediante
 * {@link CommandLineRunner} beans, utilizando una estrategia de "upsert" que
 * permite actualizar registros existentes sin duplicarlos.
 * </p>
 */
@Configuration
public class SeedConfig {

    /**
     * Bean que ejecuta la carga inicial de categorías y controles ISO.
     * <p>
     * Crea las 4 categorías principales del Anexo A de ISO 27001:2022:
     * <ul>
     * <li>A5 - Controles organizacionales (37 controles)</li>
     * <li>A6 - Controles de personas (8 controles)</li>
     * <li>A7 - Controles físicos (14 controles)</li>
     * <li>A8 - Controles tecnológicos (34 controles)</li>
     * </ul>
     * </p>
     *
     * @param catRepo  Repositorio de categorías.
     * @param ctrlRepo Repositorio de controles.
     * @return CommandLineRunner que ejecuta la lógica de seed.
     */
    @Bean
    @Transactional
    CommandLineRunner seedCategoriasYControles(CategoriaRepository catRepo,
            ControlRepository ctrlRepo) {
        return args -> {
            // Categorías
            createCat(catRepo, "A5", "Controles organizacionales");
            createCat(catRepo, "A6", "Controles de personas");
            createCat(catRepo, "A7", "Controles físicos");
            createCat(catRepo, "A8", "Controles tecnológicos");

            // Controles (A5)
            createCtrl(ctrlRepo, catRepo, "A5.1", "Política de seguridad de la información", 1, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.2", "Roles y responsabilidades en seguridad de la información", 2, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.3", "Segregación de funciones", 3, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.4", "Responsabilidades de la dirección", 4, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.5", "Contacto con autoridades", 5, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.6", "Contacto con grupos de interés", 6, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.7", "Inteligencia sobre amenazas", 7, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.8", "Seguridad de la información en gestión de proyectos", 8, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.9", "Inventario de activos de información ", 9, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.10", "Uso aceptable de la información y otros", 10, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.11", "Devolución de activos", 11, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.12", "Clasificación de la información", 12, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.13", "Etiquetado de la información", 13, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.14", "Transferencia de información", 14, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.15", "Control de acceso", 15, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.16", "Gestión de identidad", 16, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.17", "Autenticación de usuarios", 17, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.18", "Derechos de acceso ", 18, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.19", "Seguridad en relaciones con proveedores", 19, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.20", "Seguridad en contratos con proveedores", 20, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.21", "Seguridad en la cadena de suministro TIC ", 21, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.22", "Monitoreo y gestión de cambios en servicios de proveedores", 22,
                    "A5");
            createCtrl(ctrlRepo, catRepo, "A5.23", "Seguridad en uso de servicios en la nube ", 23, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.24", "Preparación para gestión de incidentes ", 24, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.25", "Evaluación y decisión sobre eventos de seguridad", 25, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.26", "Respuesta a incidentes", 26, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.27", "Aprendizaje de incidentes", 27, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.28", "Recopilación de evidencias", 28, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.29", "Seguridad durante interrupciones ", 29, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.30", "Preparación TIC para continuidad del negocio", 30, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.31", "Requisitos legales, reglamentarios y contractuales", 31, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.32", "Derechos de propiedad intelectual", 32, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.33", "Protección de registros", 33, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.34", "Privacidad y protección de datos personales", 34, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.35", "Revisión independiente de seguridad de la información", 35, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.36",
                    "Cumplimiento de políticas y normativas de seguridad de la información", 36, "A5");
            createCtrl(ctrlRepo, catRepo, "A5.37", "Procedimientos operativos documentados", 37, "A5");

            // Controles (A6)
            createCtrl(ctrlRepo, catRepo, "A6.1", "Selección y contratación segura", 1, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.2", "Concienciación y formación", 2, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.3",
                    "Concienciación, educación y formación en seguridad de la información", 3, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.4", "Proceso disciplinario", 4, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.5", "Responsabilidades tras terminación o cambio de empleo", 5, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.6", "Acuerdos de confidencialidad", 6, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.7", "Trabajo remoto", 7, "A6");
            createCtrl(ctrlRepo, catRepo, "A6.8", "Notificación de eventos de seguridad", 8, "A6");

            // Controles (A7)
            createCtrl(ctrlRepo, catRepo, "A7.1", "Seguridad física perimetral", 1, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.2", "Seguridad en oficinas y salas", 2, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.3", "Seguridad de oficinas, salas e instalaciones", 3, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.4", "Monitoreo de seguridad física ", 4, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.5", "Protección contra amenazas físicas y ambientales", 5, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.6", "Trabajo en áreas seguras", 6, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.7", "Política de escritorio y pantalla limpios", 7, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.8", "Ubicación y protección del equipamiento", 8, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.9", "Seguridad de activos fuera de las instalaciones", 9, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.10", "Medios de almacenamiento", 10, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.11", "Servicios de apoyo", 11, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.12", "Seguridad en cableado", 12, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.13", "Mantenimiento de equipamiento", 13, "A7");
            createCtrl(ctrlRepo, catRepo, "A7.14", "Eliminación o reutilización de equipamiento", 14, "A7");

            // Controles (A8)
            createCtrl(ctrlRepo, catRepo, "A8.1", "Gestión de vulnerabilidades técnicas", 1, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.2", "Cifrado", 2, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.3", "Restricción de acceso a la información", 3, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.4", "Acceso al código fuente", 4, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.5", "Autenticación segura", 5, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.6", "Gestión de la capacidad", 6, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.7", "Protección contra software malicioso", 7, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.8", "Gestión de vulnerabilidades técnicas", 8, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.9", "Gestión de la configuración", 9, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.10", "Eliminación de información", 10, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.11", "Enmascaramiento de datos", 11, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.12", "Prevención de fuga de información (DLP)", 12, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.13", "Copias de seguridad de la información", 13, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.14",
                    "Redundancia de las instalaciones de procesamiento de la información", 14, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.15", "Registro de eventos ", 15, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.16", "Monitorización de actividades", 16, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.17", "Sincronización de relojes", 17, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.18", "Uso de programas utilitarios con privilegios", 18, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.19", "Instalación de software en sistemas operativos en producción", 19,
                    "A8");
            createCtrl(ctrlRepo, catRepo, "A8.20", "Controles de red", 20, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.21", "Seguridad de los servicios de red", 21, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.22", "Segmentación en redes", 22, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.23", "SFiltrado web", 23, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.24", "Uso de criptografía", 24, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.25", "Ciclo de vida de desarrollo seguro", 25, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.26", "Requisitos de seguridad en aplicaciones", 26, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.27", "Principios de arquitectura y diseño seguro de sistemas", 27, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.28", "Codificación segura", 28, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.29", "Pruebas de seguridad en el desarrollo y aceptación", 29, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.30", "Desarrollo externalizado", 30, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.31", "Separación de entornos de desarrollo, pruebas y producción", 31,
                    "A8");
            createCtrl(ctrlRepo, catRepo, "A8.32", "Gestión de cambios", 32, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.33", "Información utilizada en pruebas", 33, "A8");
            createCtrl(ctrlRepo, catRepo, "A8.34",
                    "Protección de los sistemas de información durante pruebas de auditoría", 34, "A8");
        };
    }

    /**
     * Crea una categoría si no existe en la base de datos.
     *
     * @param repo   Repositorio de categorías.
     * @param id     Identificador de la categoría (ej: "A5").
     * @param nombre Nombre descriptivo de la categoría.
     */
    private void createCat(CategoriaRepository repo, String id, String nombre) {
        repo.findById(id).orElseGet(() -> repo.save(new Categoria(id, nombre)));
    }

    /**
     * Crea o actualiza un control ISO en la base de datos.
     * <p>
     * Si el control ya existe, actualiza su nombre, categoría u orden si han
     * cambiado.
     * Si no existe, lo crea nuevo. Esta estrategia permite re-ejecutar el seed sin
     * duplicar datos.
     * </p>
     *
     * @param ctrlRepo Repositorio de controles.
     * @param catRepo  Repositorio de categorías.
     * @param id       Identificador del control (ej: "A5.1").
     * @param nombre   Nombre descriptivo del control.
     * @param orden    Orden de visualización dentro de su categoría.
     * @param catId    ID de la categoría a la que pertenece.
     */
    private void createCtrl(ControlRepository ctrlRepo, CategoriaRepository catRepo,
            String id, String nombre, Integer orden, String catId) {
        var cat = catRepo.findById(catId).orElseThrow();

        ctrlRepo.findById(id).ifPresentOrElse(ctrl -> {
            boolean changed = false;
            if (!nombre.equals(ctrl.getNombre())) {
                ctrl.setNombre(nombre);
                changed = true;
            }
            if (!catId.equals(ctrl.getCategoria().getId())) {
                ctrl.setCategoria(cat);
                changed = true;
            }
            if (ctrl.getOrden() == null || !ctrl.getOrden().equals(orden)) {
                ctrl.setOrden(orden);
                changed = true;
            }
            if (changed)
                ctrlRepo.save(ctrl);
        }, () -> {
            ctrlRepo.save(new Control(id, nombre, cat, orden));
        });
    }

    /**
     * Bean que ejecuta la carga inicial de respuestas posibles para los
     * cuestionarios.
     * <p>
     * Define las opciones de respuesta estándar con sus baremos:
     * <ul>
     * <li>SI - Baremo: 100 (cumplimiento total)</li>
     * <li>NO APLICA - Baremo: 0 (control no aplicable)</li>
     * <li>NO - Baremo: -100 (incumplimiento)</li>
     * </ul>
     * </p>
     *
     * @param opcionesRepo Repositorio de respuestas posibles.
     * @return CommandLineRunner que ejecuta la lógica de seed.
     */
    @Bean
    @Transactional
    CommandLineRunner seedRespuestasPosibles(RespuestaPosibleRepository opcionesRepo) {
        return args -> {
            upsertOpcion(opcionesRepo, "SI", 100, 1);
            upsertOpcion(opcionesRepo, "NO APLICA", 0, 2);
            upsertOpcion(opcionesRepo, "NO", -100, 3);

            // Ejemplo de extra:
            // upsertOpcion(opcionesRepo, "PARCIAL", 50, 4);
        };
    }

    /**
     * Crea o actualiza una respuesta posible en la base de datos.
     * <p>
     * Si la opción ya existe (búsqueda case-insensitive), actualiza su baremo u
     * orden si han cambiado.
     * Si no existe, la crea nueva.
     * </p>
     *
     * @param repo   Repositorio de respuestas posibles.
     * @param texto  Texto de la opción (ej: "SI", "NO", "NO APLICA").
     * @param baremo Valor numérico asociado para cálculos de cumplimiento.
     * @param orden  Orden de visualización en formularios.
     */
    private void upsertOpcion(RespuestaPosibleRepository repo, String texto, int baremo, Integer orden) {
        repo.findByTextoOpcionIgnoreCase(texto).ifPresentOrElse(opt -> {
            boolean changed = false;
            if (opt.getBaremo() != baremo) {
                opt.setBaremo(baremo);
                changed = true;
            }
            if (orden != null && (opt.getOrden() == null || !opt.getOrden().equals(orden))) {
                opt.setOrden(orden);
                changed = true;
            }
            if (changed)
                repo.save(opt);
        }, () -> {
            RespuestaPosible opt = new RespuestaPosible();
            opt.setTextoOpcion(texto);
            opt.setBaremo(baremo);
            opt.setOrden(orden);
            repo.save(opt);
        });
    }
}
