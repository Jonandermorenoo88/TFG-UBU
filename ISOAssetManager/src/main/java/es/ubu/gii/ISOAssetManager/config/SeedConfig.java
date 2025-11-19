// SeedConfig.java
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

@Configuration
public class SeedConfig {

    /* ==========================================================
       RUNNER 1: CATEGORÍAS y CONTROLES
       ========================================================== */
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
            createCtrl(ctrlRepo, catRepo, "A5.1", "Política de seguridad de la información", "A5", 1);
            createCtrl(ctrlRepo, catRepo, "A5.2", "Roles y responsabilidades en seguridad de la información", "A5", 2);
            createCtrl(ctrlRepo, catRepo, "A5.3", "Segregación de funciones", "A5", 3);
            createCtrl(ctrlRepo, catRepo, "A5.4", "Responsabilidades de la dirección", "A5", 4);
            createCtrl(ctrlRepo, catRepo, "A5.5", "Contacto con autoridades", "A5", 5);
            createCtrl(ctrlRepo, catRepo, "A5.6", "Contacto con grupos de interés", "A5",  6 );
            createCtrl(ctrlRepo, catRepo, "A5.7", "Inteligencia sobre amenazas", "A5", 7);
            createCtrl(ctrlRepo, catRepo, "A5.8", "Seguridad de la información en gestión de proyectos", "A5", 8);
            createCtrl(ctrlRepo, catRepo, "A5.9", "Inventario de activos de información ", "A5",    9);
            createCtrl(ctrlRepo, catRepo, "A5.10", "Uso aceptable de la información y otros", "A5", 10);
            createCtrl(ctrlRepo, catRepo, "A5.11", "Devolución de activos", "A5",   11);
            createCtrl(ctrlRepo, catRepo, "A5.12", "Clasificación de la información", "A5", 12  );
            createCtrl(ctrlRepo, catRepo, "A5.13", "Etiquetado de la información", "A5", 13);
            createCtrl(ctrlRepo, catRepo, "A5.14", "Transferencia de información", "A5", 14);
            createCtrl(ctrlRepo, catRepo, "A5.15", "Control de acceso", "A5", 15);
            createCtrl(ctrlRepo, catRepo, "A5.16", "Gestión de identidad", "A5", 16);
            createCtrl(ctrlRepo, catRepo, "A5.17", "Autenticación de usuarios", "A5", 17);
            createCtrl(ctrlRepo, catRepo, "A5.18", "Derechos de acceso ", "A5", 18);
            createCtrl(ctrlRepo, catRepo, "A5.19", "Seguridad en relaciones con proveedores", "A5", 19);
            createCtrl(ctrlRepo, catRepo, "A5.20", "Seguridad en contratos con proveedores", "A5", 20);
            createCtrl(ctrlRepo, catRepo, "A5.21", "Seguridad en la cadena de suministro TIC ", "A5", 21);
            createCtrl(ctrlRepo, catRepo, "A5.22", "Monitoreo y gestión de cambios en servicios de proveedores", "A5", 22   );
            createCtrl(ctrlRepo, catRepo, "A5.23", "Seguridad en uso de servicios en la nube ", "A5", 23);
            createCtrl(ctrlRepo, catRepo, "A5.24", "Preparación para gestión de incidentes ", "A5", 24);
            createCtrl(ctrlRepo, catRepo, "A5.25", "Evaluación y decisión sobre eventos de seguridad", "A5", 25);
            createCtrl(ctrlRepo, catRepo, "A5.26", "Respuesta a incidentes", "A5", 26);
            createCtrl(ctrlRepo, catRepo, "A5.27", "Aprendizaje de incidentes", "A5", 27);
            createCtrl(ctrlRepo, catRepo, "A5.28", "Recopilación de evidencias", "A5", 28);
            createCtrl(ctrlRepo, catRepo, "A5.29", "Seguridad durante interrupciones ", "A5", 29);
            createCtrl(ctrlRepo, catRepo, "A5.30", "Preparación TIC para continuidad del negocio", "A5", 30 );
            createCtrl(ctrlRepo, catRepo, "A5.31", "Requisitos legales, reglamentarios y contractuales", "A5",  31);
            createCtrl(ctrlRepo, catRepo, "A5.32", "Derechos de propiedad intelectual", "A5", 32    );
            createCtrl(ctrlRepo, catRepo, "A5.33", "Protección de registros", "A5", 33  );
            createCtrl(ctrlRepo, catRepo, "A5.34", "Privacidad y protección de datos personales", "A5", 34);
            createCtrl(ctrlRepo, catRepo, "A5.35", "Revisión independiente de seguridad de la información", "A5", 35    );
            createCtrl(ctrlRepo, catRepo, "A5.36", "Cumplimiento de políticas y normativas de seguridad de la información", "A5", 36);
            createCtrl(ctrlRepo, catRepo, "A5.37", "Procedimientos operativos documentados", "A5", 37   );

            // Controles (A6)
            createCtrl(ctrlRepo, catRepo, "A6.1", "Selección y contratación segura", "A6", 1);
            createCtrl(ctrlRepo, catRepo, "A6.2", "Concienciación y formación", "A6", 2);
            createCtrl(ctrlRepo, catRepo, "A6.3", "Concienciación, educación y formación en seguridad de la información", "A6", 3);
            createCtrl(ctrlRepo, catRepo, "A6.4", "Proceso disciplinario", "A6", 4);
            createCtrl(ctrlRepo, catRepo, "A6.5", "Responsabilidades tras terminación o cambio de empleo", "A6", 5);
            createCtrl(ctrlRepo, catRepo, "A6.6", "Acuerdos de confidencialidad", "A6", 6);
            createCtrl(ctrlRepo, catRepo, "A6.7", "Trabajo remoto", "A6", 7);
            createCtrl(ctrlRepo, catRepo, "A6.8", "Notificación de eventos de seguridad", "A6", 8);

            // Controles (A7)
            createCtrl(ctrlRepo, catRepo, "A7.1", "Seguridad física perimetral", "A7", 1);
            createCtrl(ctrlRepo, catRepo, "A7.2", "Seguridad en oficinas y salas", "A7", 2);
            createCtrl(ctrlRepo, catRepo, "A7.3", "Seguridad de oficinas, salas e instalaciones", "A7", 3);
            createCtrl(ctrlRepo, catRepo, "A7.4", "Monitoreo de seguridad física ", "A7", 4);
            createCtrl(ctrlRepo, catRepo, "A7.5", "Protección contra amenazas físicas y ambientales", "A7", 5);
            createCtrl(ctrlRepo, catRepo, "A7.6", "Trabajo en áreas seguras", "A7", 6);
            createCtrl(ctrlRepo, catRepo, "A7.7", "Política de escritorio y pantalla limpios", "A7", 7);
            createCtrl(ctrlRepo, catRepo, "A7.8", "Ubicación y protección del equipamiento", "A7", 8);
            createCtrl(ctrlRepo, catRepo, "A7.9", "Seguridad de activos fuera de las instalaciones", "A7", 9 );
            createCtrl(ctrlRepo, catRepo, "A7.10", "Medios de almacenamiento", "A7", 10);
            createCtrl(ctrlRepo, catRepo, "A7.11", "Servicios de apoyo", "A7", 11);
            createCtrl(ctrlRepo, catRepo, "A7.12", "Seguridad en cableado", "A7", 12);
            createCtrl(ctrlRepo, catRepo, "A7.13", "Mantenimiento de equipamiento", "A7", 13);
            createCtrl(ctrlRepo, catRepo, "A7.14", "Eliminación o reutilización de equipamiento", "A7", 14);

            // Controles (A8)
            createCtrl(ctrlRepo, catRepo, "A8.1", "Gestión de vulnerabilidades técnicas", "A8", 1);
            createCtrl(ctrlRepo, catRepo, "A8.2", "Cifrado", "A8", 2);
            createCtrl(ctrlRepo, catRepo, "A8.3", "Restricción de acceso a la información", "A8", 3);
            createCtrl(ctrlRepo, catRepo, "A8.4", "Acceso al código fuente", "A8", 4);
            createCtrl(ctrlRepo, catRepo, "A8.5", "Autenticación segura", "A8", 5);
            createCtrl(ctrlRepo, catRepo, "A8.6", "Gestión de la capacidad", "A8", 6);
            createCtrl(ctrlRepo, catRepo, "A8.7", "Protección contra software malicioso", "A8", 7);
            createCtrl(ctrlRepo, catRepo, "A8.8", "Gestión de vulnerabilidades técnicas", "A8", 8);
            createCtrl(ctrlRepo, catRepo, "A8.9", "Gestión de la configuración", "A8", 9    );
            createCtrl(ctrlRepo, catRepo, "A8.10", "Eliminación de información", "A8", 10);
            createCtrl(ctrlRepo, catRepo, "A8.11", "Enmascaramiento de datos", "A8", 11);
            createCtrl(ctrlRepo, catRepo, "A8.12", "Prevención de fuga de información (DLP)", "A8", 12);
            createCtrl(ctrlRepo, catRepo, "A8.13", "Copias de seguridad de la información", "A8",   13);
            createCtrl(ctrlRepo, catRepo, "A8.14", "Redundancia de las instalaciones de procesamiento de la información", "A8", 14);
            createCtrl(ctrlRepo, catRepo, "A8.15", "Registro de eventos ", "A8", 15 );
            createCtrl(ctrlRepo, catRepo, "A8.16", "Monitorización de actividades", "A8", 16  );
            createCtrl(ctrlRepo, catRepo, "A8.17", "Sincronización de relojes", "A8", 17  );
            createCtrl(ctrlRepo, catRepo, "A8.18", "Uso de programas utilitarios con privilegios", "A8", 18);
            createCtrl(ctrlRepo, catRepo, "A8.19", "Instalación de software en sistemas operativos en producción", "A8", 19 );
            createCtrl(ctrlRepo, catRepo, "A8.20", "Controles de red", "A8", 20);
            createCtrl(ctrlRepo, catRepo, "A8.21", "Seguridad de los servicios de red", "A8", 21);
            createCtrl(ctrlRepo, catRepo, "A8.22", "Segmentación en redes", "A8", 22);
            createCtrl(ctrlRepo, catRepo, "A8.23", "SFiltrado web", "A8", 23);
            createCtrl(ctrlRepo, catRepo, "A8.24", "Uso de criptografía", "A8", 24);
            createCtrl(ctrlRepo, catRepo, "A8.25", "Ciclo de vida de desarrollo seguro", "A8", 25);
            createCtrl(ctrlRepo, catRepo, "A8.26", "Requisitos de seguridad en aplicaciones", "A8", 26);
            createCtrl(ctrlRepo, catRepo, "A8.27", "Principios de arquitectura y diseño seguro de sistemas", "A8", 27);
            createCtrl(ctrlRepo, catRepo, "A8.28", "Codificación segura", "A8", 28);
            createCtrl(ctrlRepo, catRepo, "A8.29", "Pruebas de seguridad en el desarrollo y aceptación", "A8", 29);
            createCtrl(ctrlRepo, catRepo, "A8.30", "Desarrollo externalizado", "A8", 30);
            createCtrl(ctrlRepo, catRepo, "A8.31", "Separación de entornos de desarrollo, pruebas y producción", "A8", 31);
            createCtrl(ctrlRepo, catRepo, "A8.32", "Gestión de cambios", "A8", 32);
            createCtrl(ctrlRepo, catRepo, "A8.33", "Información utilizada en pruebas", "A8", 33);
            createCtrl(ctrlRepo, catRepo, "A8.34", "Protección de los sistemas de información durante pruebas de auditoría", "A8", 34);
        };
    }

    private void createCat(CategoriaRepository repo, String id, String nombre) {
        repo.findById(id).orElseGet(() -> repo.save(new Categoria(id, nombre)));
    }

private void createCtrl(ControlRepository ctrlRepo, CategoriaRepository catRepo,
                        String id, String nombre, String catId, Integer orden) {
    var cat = catRepo.findById(catId).orElseThrow();

    ctrlRepo.findById(id).ifPresentOrElse(ctrl -> {
        boolean changed = false;
        if (!nombre.equals(ctrl.getNombre())) { ctrl.setNombre(nombre); changed = true; }
        if (!catId.equals(ctrl.getCategoria().getId())) { ctrl.setCategoria(cat); changed = true; }
        if (ctrl.getOrden() == null || !ctrl.getOrden().equals(orden)) { ctrl.setOrden(orden); changed = true; }
        if (changed) ctrlRepo.save(ctrl);
    }, () -> {
        ctrlRepo.save(new Control(id, nombre, cat, orden));
    });
}


    /* ==========================================================
       RUNNER 2: RESPUESTAS POSIBLES GLOBALES (upsert + orden)
       ========================================================== */
    @Bean
    @Transactional
    CommandLineRunner seedRespuestasPosibles(RespuestaPosibleRepository opcionesRepo) {
        return args -> {
            upsertOpcion(opcionesRepo, "SI",         100, 1);
            upsertOpcion(opcionesRepo, "NO APLICA",    0, 2);
            upsertOpcion(opcionesRepo, "NO",        -100, 3);

            // Ejemplo de extra:
            // upsertOpcion(opcionesRepo, "PARCIAL", 50, 4);
        };
    }

    private void upsertOpcion(RespuestaPosibleRepository repo, String texto, int baremo, Integer orden) {
        repo.findByTextoOpcionIgnoreCase(texto).ifPresentOrElse(opt -> {
            boolean changed = false;
            if (opt.getBaremo() != baremo) { opt.setBaremo(baremo); changed = true; }
            if (orden != null && (opt.getOrden() == null || !opt.getOrden().equals(orden))) {
                opt.setOrden(orden); changed = true;
            }
            if (changed) repo.save(opt);
        }, () -> {
            RespuestaPosible opt = new RespuestaPosible();
            opt.setTextoOpcion(texto);
            opt.setBaremo(baremo);
            opt.setOrden(orden);
            repo.save(opt);
        });
    }
}