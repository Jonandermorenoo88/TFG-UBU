USE isoassetmanager;

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización ha definido perímetros físicos claros para proteger áreas sensibles, como salas de servidores, centros de datos o zonas restringidas.',
    '¿Existen perímetros definidos para proteger las áreas sensibles de la organización?',
    'A7.1'
),
(
    'Comprueba si el perímetro de seguridad está protegido mediante barreras físicas adecuadas, tales como muros, vallas, cerramientos, puertas reforzadas o acceso controlado.',
    '¿El perímetro de seguridad cuenta con barreras físicas adecuadas (muros, vallas, puertas seguras)?',
    'A7.1'
),
(
    'Verifica si el acceso al perímetro de seguridad está controlado y supervisado mediante sistemas como controles de acceso, vigilancia, cámaras o personal autorizado.',
    '¿El acceso al perímetro de seguridad está controlado y supervisado?',
    'A7.1'
),
(
    'Determina si los controles perimetrales se revisan periódicamente para asegurar su efectividad frente a amenazas físicas o cambios en el entorno.',
    '¿Se revisa periódicamente la efectividad de los controles perimetrales implementados?',
    'A7.1'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el acceso físico a las instalaciones está debidamente restringido y permitido únicamente a personal autorizado, evitando accesos no controlados.',
    '¿El acceso a las instalaciones está restringido únicamente a personal autorizado?',
    'A7.2'
),
(
    'Comprueba si la organización utiliza sistemas de control de entrada, como tarjetas de acceso, registros, códigos o biometría, para identificar a las personas que acceden.',
    '¿Se utilizan sistemas de control de entrada (tarjetas, biometría, registros) para identificar a los usuarios?',
    'A7.2'
),
(
    'Verifica si los visitantes son acompañados o supervisados en todo momento dentro de las instalaciones para asegurar que no tengan acceso no autorizado.',
    '¿Los visitantes están acompañados o supervisados en todo momento dentro de las instalaciones?',
    'A7.2'
),
(
    'Determina si se mantienen registros de accesos físicos —de empleados y visitantes— con el fin de permitir auditorías y reforzar la seguridad.',
    '¿Se mantienen registros de accesos físicos para fines de auditoría y seguridad?',
    'A7.2'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si las oficinas, salas críticas y áreas donde se maneja información sensible están protegidas adecuadamente contra accesos no autorizados.',
    '¿Las oficinas y salas críticas están protegidas contra accesos no autorizados?',
    'A7.3'
),
(
    'Comprueba si las instalaciones cuentan con medidas de seguridad física apropiadas, tales como cerraduras reforzadas, alarmas, cámaras de vigilancia o detectores.',
    '¿Las instalaciones cuentan con medidas de seguridad adecuadas (cerraduras, alarmas, cámaras)?',
    'A7.3'
),
(
    'Verifica si existen procedimientos específicos y formalizados para controlar el acceso a salas y áreas donde se almacena o gestiona información sensible o crítica.',
    '¿Se aplican procedimientos específicos para el acceso a salas que contienen información sensible?',
    'A7.3'
),
(
    'Determina si la seguridad física de oficinas, salas y áreas protegidas se revisa periódicamente conforme a riesgos detectados o cambios operativos.',
    '¿Se revisa periódicamente la seguridad de oficinas y salas en función de los riesgos detectados?',
    'A7.3'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de sistemas de videovigilancia o herramientas de monitoreo físico en las áreas críticas, como salas de servidores, accesos principales o zonas restringidas.',
    '¿Existen sistemas de videovigilancia o monitoreo en áreas críticas de las instalaciones?',
    'A7.4'
),
(
    'Comprueba si el monitoreo de seguridad física se realiza de forma continua y si las grabaciones se almacenan de manera segura, cumpliendo requisitos legales y de protección de datos.',
    '¿El monitoreo físico se realiza de forma continua y con almacenamiento seguro de grabaciones?',
    'A7.4'
),
(
    'Verifica si el acceso a los registros o grabaciones generadas por los sistemas de monitoreo está restringido únicamente a personal autorizado, garantizando su confidencialidad e integridad.',
    '¿El acceso a los registros de monitoreo está restringido a personal autorizado?',
    'A7.4'
),
(
    'Determina si la organización revisa periódicamente la eficacia y el estado de los sistemas de monitoreo físico, ajustándolos a riesgos, incidentes o cambios tecnológicos.',
    '¿Se revisa periódicamente la eficacia de los sistemas de monitoreo físico?',
    'A7.4'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si las instalaciones cuentan con medidas adecuadas para protegerse frente a incendios, inundaciones, tormentas, fallos eléctricos u otras amenazas ambientales relevantes.',
    '¿Las instalaciones están protegidas contra incendios, inundaciones y otras amenazas ambientales?',
    'A7.5'
),
(
    'Comprueba si existen sistemas operativos de detección y alarma de incendios, tales como detectores de humo, rociadores automáticos, extintores y paneles de alarma.',
    '¿Existen alarmas y sistemas de detección de incendios instalados y operativos?',
    'A7.5'
),
(
    'Verifica si la organización realiza simulacros, inspecciones y pruebas periódicas de los sistemas de protección física y ambiental para asegurar su correcto funcionamiento.',
    '¿Se realizan simulacros o pruebas periódicas de los sistemas de protección física y ambiental?',
    'A7.5'
),
(
    'Determina si la planificación de seguridad de la organización contempla amenazas naturales o accidentales y establece controles preventivos para mitigarlas.',
    '¿La planificación de seguridad contempla amenazas naturales o accidentales relevantes?',
    'A7.5'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización aplica procedimientos especiales y formalizados para trabajar en áreas designadas como seguras, donde se maneja información o activos críticos.',
    '¿Se aplican procedimientos especiales para trabajar en áreas clasificadas como seguras?',
    'A7.6'
),
(
    'Comprueba si el acceso a las áreas seguras está estrictamente limitado y controlado, permitiendo únicamente la entrada a personal previamente autorizado.',
    '¿El acceso a las áreas seguras está limitado al personal estrictamente autorizado?',
    'A7.6'
),
(
    'Verifica si el personal que trabaja o accede a áreas seguras recibe formación específica sobre las medidas de seguridad aplicables dentro de dichas zonas.',
    '¿El personal recibe formación sobre las medidas de seguridad aplicables en áreas seguras?',
    'A7.6'
),
(
    'Determina si la organización supervisa de manera continua el cumplimiento de las medidas de seguridad establecidas para las áreas seguras.',
    '¿Se supervisa el cumplimiento de las medidas de seguridad en las áreas seguras?',
    'A7.6'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con una política formal de escritorio y pantalla limpios para proteger la información frente a accesos no autorizados.',
    '¿Existe una política formal de escritorio y pantalla limpios en la organización?',
    'A7.7'
),
(
    'Comprueba si el personal evita dejar documentos físicos, dispositivos o soportes con información sensible desatendidos en escritorios o áreas de trabajo.',
    '¿El personal asegura que documentos y soportes de información no se dejan desatendidos en escritorios?',
    'A7.7'
),
(
    'Verifica si los usuarios bloquean sus pantallas cuando se ausentan de su puesto, para evitar accesos no autorizados a la información.',
    '¿Se bloquean las pantallas cuando los usuarios se ausentan de sus puestos de trabajo?',
    'A7.7'
),
(
    'Determina si la organización supervisa el cumplimiento de esta política mediante auditorías internas, revisiones u otras medidas de control.',
    '¿Se supervisa el cumplimiento de esta política mediante revisiones o auditorías internas?',
    'A7.7'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el equipamiento crítico, especialmente el relacionado con servicios esenciales o información sensible, está ubicado en zonas seguras con acceso controlado.',
    '¿El equipamiento crítico está ubicado en áreas seguras y de acceso controlado?',
    'A7.8'
),
(
    'Comprueba si los equipos están protegidos frente a riesgos físicos como incendios, humedad, vibraciones, golpes, robos o accesos no autorizados.',
    '¿Los equipos están protegidos contra riesgos físicos (incendios, humedad, golpes, accesos indebidos)?',
    'A7.8'
),
(
    'Verifica si la organización mantiene registros actualizados sobre la ubicación del equipamiento relevante para asegurar su trazabilidad y adecuada gestión.',
    '¿Se mantienen registros actualizados de la ubicación de los equipos relevantes?',
    'A7.8'
),
(
    'Determina si las medidas de seguridad física aplicadas al equipamiento son revisadas periódicamente para garantizar su eficacia ante riesgos cambiantes.',
    '¿Se revisan periódicamente las medidas de protección aplicadas al equipamiento?',
    'A7.8'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los equipos, dispositivos y la información utilizados fuera de las instalaciones cuentan con medidas de seguridad claras para evitar pérdidas, robos o accesos no autorizados.',
    '¿Los equipos e información utilizados fuera de las instalaciones cuentan con medidas de seguridad definidas?',
    'A7.9'
),
(
    'Comprueba si la organización aplica procedimientos formales para el transporte, uso y almacenamiento seguro de información y activos fuera de sus instalaciones.',
    '¿Se aplican procedimientos específicos para el transporte y uso seguro de la información fuera de la organización?',
    'A7.9'
),
(
    'Verifica si los empleados conocen sus responsabilidades al utilizar activos de la organización fuera de las instalaciones, incluyendo protección física, cifrado y custodia.',
    '¿Los empleados conocen sus responsabilidades al manejar activos fuera de las instalaciones?',
    'A7.9'
),
(
    'Determina si la organización supervisa y revisa periódicamente el cumplimiento de las medidas de seguridad aplicadas al uso de activos fuera de las instalaciones.',
    '¿Se supervisa y revisa el cumplimiento de las medidas de seguridad fuera de las instalaciones?',
    'A7.9'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los medios de almacenamiento físicos, como USB, discos duros externos o cintas, están protegidos frente a accesos no autorizados mediante controles físicos o lógicos.',
    '¿Los medios de almacenamiento físicos (USB, discos, cintas) están protegidos contra accesos no autorizados?',
    'A7.10'
),
(
    'Comprueba si la organización dispone de procedimientos formales para el transporte seguro de medios de almacenamiento que contengan información sensible o crítica.',
    '¿Existen procedimientos para el transporte seguro de medios de almacenamiento?',
    'A7.10'
),
(
    'Verifica si los medios de almacenamiento que contienen información sensible están cifrados o cuentan con controles de protección equivalentes para evitar fugas de datos.',
    '¿Los medios de almacenamiento que contienen información sensible están cifrados o protegidos con controles equivalentes?',
    'A7.10'
),
(
    'Determina si la organización revisa periódicamente el estado, uso y custodia de los medios de almacenamiento para asegurar su protección continua.',
    '¿Se revisan periódicamente los medios de almacenamiento en uso y en custodia?',
    'A7.10'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica si las instalaciones críticas cuentan con infraestructuras adecuadas y redundantes como energía eléctrica, climatización y otros servicios esenciales para asegurar la continuidad operativa.',
    '¿Las instalaciones críticas cuentan con servicios de energía eléctrica y climatización adecuados y redundantes?',
    'A7.11'
),
(
    'Evalúa si los sistemas de apoyo como UPS, generadores eléctricos y sistemas de climatización son sometidos a pruebas periódicas para garantizar su funcionamiento.',
    '¿Los sistemas de apoyo (UPS, generadores, aire acondicionado) se prueban periódicamente?',
    'A7.11'
),
(
    'Comprueba si existen procedimientos documentados que permitan responder adecuadamente ante fallos en los servicios de apoyo críticos.',
    '¿Se dispone de procedimientos para responder a fallos en servicios de apoyo?',
    'A7.11'
),
(
    'Determina si la organización revisa de manera regular la capacidad y fiabilidad de los servicios de apoyo para asegurar que siguen siendo adecuados según los riesgos y necesidades.',
    '¿Se revisa regularmente la capacidad y fiabilidad de los servicios de apoyo?',
    'A7.11'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el cableado de red y energía está correctamente protegido para evitar accesos no autorizados, manipulaciones o daños accidentales que puedan comprometer la seguridad.',
    '¿El cableado de red y energía está protegido contra accesos no autorizados y daños accidentales?',
    'A7.12'
),
(
    'Comprueba si el cableado crítico está debidamente identificado, organizado y separado para minimizar riesgos de manipulación o interferencias.',
    '¿El cableado crítico está identificado y separado para reducir riesgos de manipulación?',
    'A7.12'
),
(
    'Verifica si se realizan inspecciones periódicas destinadas a asegurar la integridad física del cableado y detectar posibles alteraciones o deterioros.',
    '¿Se realizan inspecciones periódicas para verificar la integridad del cableado?',
    'A7.12'
),
(
    'Confirma si existe documentación actualizada del esquema de cableado que permita su control, mantenimiento y supervisión de manera segura.',
    '¿Se documenta y actualiza el esquema del cableado para facilitar su control y seguridad?',
    'A7.12'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el equipamiento recibe mantenimiento preventivo y correctivo siguiendo las recomendaciones del fabricante para garantizar su funcionamiento seguro.',
    '¿El equipamiento recibe mantenimiento periódico conforme a las recomendaciones del fabricante?',
    'A7.13'
),
(
    'Verifica que las tareas de mantenimiento sean realizadas exclusivamente por personal con autorización y cualificación adecuada para evitar riesgos de seguridad.',
    '¿El mantenimiento se realiza únicamente por personal autorizado y calificado?',
    'A7.13'
),
(
    'Comprueba si todas las actividades de mantenimiento son registradas adecuadamente para asegurar trazabilidad y control sobre el estado del equipamiento.',
    '¿Se registran todas las actividades de mantenimiento realizadas sobre el equipamiento?',
    'A7.13'
),
(
    'Analiza si la organización revisa periódicamente la eficacia del mantenimiento para asegurar que contribuye adecuadamente a la seguridad del equipamiento.',
    '¿Se revisa periódicamente la eficacia del mantenimiento en relación con la seguridad del equipamiento?',
    'A7.13'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos formales para garantizar la eliminación o reutilización segura de equipos que puedan contener información sensible.',
    '¿Existen procedimientos para la eliminación o reutilización segura de equipos que contienen información?',
    'A7.14'
),
(
    'Comprueba si los equipos retirados son sometidos a procesos de borrado seguro o destrucción física para evitar fugas de información.',
    '¿Los equipos retirados se someten a procesos de borrado seguro o destrucción física de la información?',
    'A7.14'
),
(
    'Verifica si el proceso de eliminación o reutilización de equipos se registra y supervisa adecuadamente para asegurar su trazabilidad y control.',
    '¿La eliminación o reutilización de equipos queda registrada y supervisada?',
    'A7.14'
),
(
    'Analiza si la organización revisa periódicamente los métodos empleados para la eliminación de equipos, garantizando su cumplimiento con requisitos legales y normativos.',
    '¿Se verifican periódicamente los métodos de eliminación para asegurar que cumplen con requisitos legales y normativos?',
    'A7.14'
);
