USE isoassetmanager;

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Esta pregunta verifica si la organización cuenta con una política formal de seguridad aprobada por la alta dirección, tal como exige ISO 27001.',
    '¿Existe una política de seguridad de la información formalmente aprobada por la dirección?',
    'A5.1'
),
(
    'Evalúa si la política ha sido comunicada a las partes interesadas relevantes, garantizando su conocimiento y disponibilidad.',
    '¿La política está publicada y comunicada a todas las partes interesadas?',
    'A5.1'
),
(
    'Comprueba si los empleados han recibido formación o información adecuada sobre la política, asegurando su correcta aplicación.',
    '¿Todos los empleados han recibido formación o comunicación sobre esta política?',
    'A5.1'
),
(
    'Determina si la política se revisa periódicamente o tras cambios significativos para asegurar su vigencia y eficacia.',
    '¿La política se revisa periódicamente o cuando ocurren cambios significativos?',
    'A5.1'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Esta pregunta evalúa si la organización ha definido formalmente los roles y responsabilidades relacionadas con la seguridad de la información, tal como exige el control A.5.2.',
    '¿Están definidos los roles y responsabilidades relacionados con la seguridad de la información?',
    'A5.2'
),
(
    'Permite verificar si los roles definidos han sido comunicados a todas las personas que tienen funciones asignadas en el ámbito de la seguridad de la información.',
    '¿Se han comunicado a todas las personas con funciones relevantes?',
    'A5.2'
),
(
    'Comprueba si la organización dispone de una matriz de responsabilidades (como RACI) o un organigrama actualizado que respalde la asignación de responsabilidades del SGSI.',
    '¿Existe un organigrama o matriz de responsabilidades (RACI, por ejemplo) para el SGSI?',
    'A5.2'
),
(
    'Evalúa si las responsabilidades en materia de seguridad se encuentran reflejadas en las descripciones de puestos de trabajo, contratos o documentos equivalentes.',
    '¿Se incluyen las responsabilidades de seguridad en las descripciones de puesto de trabajo o contratos?',
    'A5.2'
),
(
    'Determina si los roles y responsabilidades se revisan periódicamente o tras cambios organizativos para asegurar que siguen siendo adecuados y actualizados.',
    '¿Se revisan los roles y responsabilidades periódicamente o cuando hay cambios organizativos?',
    'A5.2'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización ha identificado funciones críticas que requieren segregación para evitar conflictos de intereses, fraude o abuso de privilegios.',
    '¿Se han identificado las funciones críticas que requieren segregación para prevenir conflictos de intereses o abuso?',
    'A5.3'
),
(
    'Comprueba si existen controles para impedir que una misma persona pueda ejecutar todas las fases de un proceso, reduciendo riesgos como manipulación o introducción de errores.',
    '¿Hay controles para asegurar que ninguna persona tenga acceso total a todas las etapas de un proceso (por ejemplo, desarrollo, pruebas y producción)?',
    'A5.3'
),
(
    'Verifica si la segregación de funciones está formalmente documentada en políticas, procedimientos o normativa interna, tal como recomienda ISO 27001.',
    '¿Está documentada la segregación de funciones en políticas o procedimientos?',
    'A5.3'
),
(
    'Evalúa si la segregación de funciones se revisa periódicamente, especialmente tras cambios organizativos o tecnológicos que puedan impactar los riesgos.',
    '¿Se revisa periódicamente la segregación de funciones para adaptarse a cambios organizativos o tecnológicos?',
    'A5.3'
),
(
    'Determina si existe supervisión, revisión o controles adicionales cuando no es posible aplicar una segregación completa, aplicando controles compensatorios.',
    '¿Existe una supervisión o control cruzado cuando no es posible la segregación completa (principio de “compensación de controles”)?',
    'A5.3'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la alta dirección ha aprobado formalmente la política de seguridad de la información, demostrando liderazgo y alineación con el SGSI.',
    '¿Ha aprobado la alta dirección la política de seguridad de la información?',
    'A5.4'
),
(
    'Verifica si la dirección participa activamente en la definición de los objetivos de seguridad, tal como requiere ISO 27001 para asegurar la orientación estratégica del SGSI.',
    '¿Participa la alta dirección en la definición de objetivos de seguridad?',
    'A5.4'
),
(
    'Comprueba si los roles de liderazgo incluyen responsabilidades formales relacionadas con la seguridad de la información dentro del SGSI.',
    '¿Se evidencian responsabilidades de seguridad asignadas en roles de liderazgo?',
    'A5.4'
),
(
    'Evalúa si la alta dirección respalda el SGSI asignando recursos humanos, financieros y tecnológicos suficientes para su funcionamiento.',
    '¿La dirección demuestra compromiso proporcionando recursos adecuados para el SGSI?',
    'A5.4'
),
(
    'Determina si la alta dirección revisa periódicamente el desempeño del SGSI, asegurando su mejora continua y cumplimiento con los requisitos normativos.',
    '¿Revisa periódicamente la alta dirección el desempeño del SGSI?',
    'A5.4'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización ha identificado correctamente las autoridades competentes en materia de ciberseguridad, privacidad o gestión de incidentes (como CCN-CERT, INCIBE o AEPD).',
    '¿Se han identificado las autoridades competentes en materia de seguridad de la información (p. ej., CCN-CERT, AEPD, INCIBE)?',
    'A5.5'
),
(
    'Comprueba si existe un procedimiento documentado que describa cuándo y cómo contactar a las autoridades correspondientes en caso necesario.',
    '¿Existe un procedimiento documentado para contactar con las autoridades cuando sea necesario?',
    'A5.5'
),
(
    'Verifica si se ha asignado un responsable encargado de gestionar las comunicaciones formales con las autoridades para asegurar rapidez y coherencia.',
    '¿Se ha designado un responsable para gestionar las comunicaciones con autoridades?',
    'A5.5'
),
(
    'Determina si la lista de contactos oficiales y los requisitos regulatorios se revisan periódicamente para mantenerlos actualizados.',
    '¿Se revisa periódicamente la lista de contactos y requisitos regulatorios asociados?',
    'A5.5'
),
(
    'Evalúa si la organización ha realizado contactos previos, ejercicios o simulacros con autoridades para verificar la eficacia del procedimiento.',
    '¿Se han realizado contactos previos o simulacros para verificar el procedimiento?',
    'A5.5'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización mantiene relación o participa activamente en grupos de interés, foros o asociaciones relevantes de ciberseguridad, lo cual mejora la capacidad de respuesta ante amenazas.',
    '¿Está la organización afiliada o en contacto con grupos de interés o foros de seguridad (p. ej., ISMS Forum, FIRST, CCN-CERT)?',
    'A5.6'
),
(
    'Comprueba si existe un procedimiento o política interna que regule la participación en grupos externos de seguridad o intercambio de información.',
    '¿Existe un procedimiento o política para participar en grupos de interés relevantes?',
    'A5.6'
),
(
    'Verifica si la organización ha asignado formalmente un responsable encargado de gestionar las relaciones con grupos externos de seguridad.',
    '¿Se ha designado a alguien como responsable de estas relaciones externas?',
    'A5.6'
),
(
    'Determina si la organización revisa periódicamente los grupos, asociaciones o foros a los que pertenece, para confirmar que siguen siendo relevantes y útiles.',
    '¿Se revisan periódicamente los grupos o asociaciones para asegurar su relevancia?',
    'A5.6'
),
(
    'Evalúa si la organización utiliza el contacto con estos grupos para intercambiar información sobre amenazas, incidentes, buenas prácticas o tendencias de seguridad.',
    '¿Se utilizan estos contactos para intercambiar información sobre amenazas o buenas prácticas?',
    'A5.6'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización recopila información sobre amenazas relevantes, como vulnerabilidades, ciberataques o nuevas modalidades de malware, con el fin de anticiparse a posibles riesgos.',
    '¿La organización recopila información sobre amenazas relevantes (p. ej., vulnerabilidades, ciberataques, malware)?',
    'A5.7'
),
(
    'Comprueba si la organización utiliza fuentes externas confiables como CERTs, proveedores de seguridad o foros especializados para obtener inteligencia actualizada sobre amenazas.',
    '¿Se utilizan fuentes externas confiables (CERTs, proveedores, foros de seguridad) para obtener inteligencia sobre amenazas?',
    'A5.7'
),
(
    'Verifica si existen procesos internos para analizar, evaluar y aplicar la inteligencia sobre amenazas al contexto de la organización, mejorando su postura de seguridad.',
    '¿Hay procesos para analizar y evaluar la información sobre amenazas y aplicarla a los sistemas de la organización?',
    'A5.7'
),
(
    'Evalúa si las amenazas identificadas se documentan y comunican adecuadamente a las partes interesadas internas, como TI, dirección o responsables de seguridad.',
    '¿Se documentan y comunican las amenazas relevantes a las partes interesadas internas?',
    'A5.7'
),
(
    'Determina si la organización revisa periódicamente la inteligencia sobre amenazas para ajustar los controles, mejorar defensas y proteger sus activos de forma proactiva.',
    '¿Se revisa periódicamente la inteligencia sobre amenazas para actualizar controles de seguridad y proteger mejor los activos?',
    'A5.7'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización integra la seguridad de la información en todas las fases de los proyectos, asegurando que los riesgos se controlen desde la planificación hasta el cierre.',
    '¿Se tiene en cuenta la seguridad de la información en todas las fases de los proyectos (planificación, ejecución, cierre)?',
    'A5.8'
),
(
    'Comprueba si dentro de los equipos de proyecto existen roles definidos con responsabilidades específicas en materia de seguridad de la información.',
    '¿Se han definido roles y responsabilidades relacionados con la seguridad dentro de los equipos de proyecto?',
    'A5.8'
),
(
    'Verifica si se realiza una evaluación de riesgos de seguridad antes de iniciar un proyecto, asegurando que se identifiquen vulnerabilidades y amenazas asociadas.',
    '¿Se evalúan los riesgos de seguridad antes de iniciar un proyecto?',
    'A5.8'
),
(
    'Determina si existen políticas o procedimientos que integren controles de seguridad dentro del proceso de gestión de proyectos, tal como recomienda ISO 27001.',
    '¿Existen políticas o procedimientos para integrar controles de seguridad en la gestión de proyectos?',
    'A5.8'
),
(
    'Evalúa si durante el ciclo de vida del proyecto se revisa periódicamente el cumplimiento de los requisitos de seguridad establecidos.',
    '¿Se revisa el cumplimiento de requisitos de seguridad durante el ciclo de vida del proyecto?',
    'A5.8'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización mantiene un inventario actualizado de activos de información, incluyendo hardware, software, datos y servicios, tal como requiere ISO 27001.',
    '¿Existe un inventario actualizado de todos los activos de información (hardware, software, datos, servicios)?',
    'A5.9'
),
(
    'Comprueba si cada activo de información tiene asignado un propietario responsable de su protección y correcto uso.',
    '¿Se han asignado propietarios responsables de cada activo?',
    'A5.9'
),
(
    'Verifica si los activos cuentan con niveles de protección definidos según su criticidad, sensibilidad o valor.',
    '¿Se han definido y aplicado niveles de protección para cada tipo de activo según su criticidad?',
    'A5.9'
),
(
    'Determina si el inventario de activos se revisa y actualiza regularmente para mantener su vigencia.',
    '¿El inventario se revisa y actualiza periódicamente?',
    'A5.9'
),
(
    'Evalúa si el inventario incluye activos no físicos, como servicios en la nube, contratos, proveedores y licencias.',
    '¿Incluye el inventario de activos no físicos (servicios en la nube, contratos, proveedores)?',
    'A5.9'
),
(
    'Comprueba si existen normas o políticas documentadas sobre el uso aceptable de los activos de información.',
    '¿Existen normas o políticas documentadas sobre el uso aceptable de activos (equipos, software, redes, datos, correo, etc.)?',
    'A5.9'
),
(
    'Verifica si las normas de uso aceptable han sido comunicadas al personal y a terceros con acceso a los activos.',
    '¿Se han comunicado estas normas al personal y terceros con acceso a los activos?',
    'A5.9'
),
(
    'Determina si el uso aceptable está incorporado en contratos, políticas de TI o programas de formación en concienciación.',
    '¿Se incluye el uso aceptable en contratos, políticas de TI o formaciones de concienciación?',
    'A5.9'
),
(
    'Evalúa si la organización define claramente las actividades no permitidas relacionadas con los activos, como instalación de software no autorizado o uso indebido.',
    '¿Se definen claramente las actividades no permitidas (uso personal indebido, instalación de software no autorizado, etc.)?',
    'A5.9'
),
(
    'Comprueba si las normas de uso aceptable se revisan periódicamente para adaptarse a cambios tecnológicos u organizativos.',
    '¿Se revisan y actualizan periódicamente las normas de uso aceptable?',
    'A5.9'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos documentados que describen cómo eliminar la información de forma segura y conforme a los requisitos de la norma.',
    '¿Existe un procedimiento documentado para la eliminación segura de información en todos los soportes?',
    'A5.10'
),
(
    'Comprueba si la organización elimina la información cuando deja de ser necesaria para fines operativos, legales o contractuales, minimizando riesgos de retención innecesaria.',
    '¿Se eliminan los datos de forma segura cuando ya no son necesarios para fines operativos, legales o contractuales?',
    'A5.10'
),
(
    'Verifica si se aplican métodos adecuados de eliminación según el tipo de soporte, garantizando que la información no pueda ser recuperada.',
    '¿Se utilizan métodos de destrucción adecuados según el tipo de soporte (borrado seguro, desmagnetización, trituración, etc.)?',
    'A5.10'
),
(
    'Evalúa si el proceso de eliminación genera evidencias o registros que permitan demostrar su correcta ejecución durante auditorías.',
    '¿Existe un registro o evidencia del proceso de eliminación de información sensible?',
    'A5.10'
),
(
    'Comprueba si los proveedores que realizan destrucción o borrado de información cumplen con requisitos de seguridad y están supervisados adecuadamente.',
    '¿Se asegura que los proveedores que eliminan información cumplan con los requisitos de seguridad?',
    'A5.10'
),
(
    'Determina si el proceso de eliminación de información es revisado periódicamente para verificar su eficacia y adaptarlo a nuevas amenazas o requisitos.',
    '¿Se revisa periódicamente el proceso de eliminación para asegurar que sigue siendo eficaz?',
    'A5.10'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de un procedimiento formal y documentado para asegurar la devolución de activos cuando termina una relación laboral o contractual.',
    '¿Existe un procedimiento documentado para la devolución de activos al finalizar la relación laboral o contractual?',
    'A5.11'
),
(
    'Comprueba si los contratos con empleados o terceros incluyen cláusulas que obligan explícitamente a la devolución de activos corporativos.',
    '¿Se incluyen cláusulas de devolución de activos en los contratos?',
    'A5.11'
),
(
    'Verifica si se mantiene un inventario actualizado de los activos asignados a cada empleado o tercero, permitiendo controlar su devolución.',
    '¿Se hace un inventario de los activos entregados a cada empleado?',
    'A5.11'
),
(
    'Determina si la organización verifica y registra la devolución de todos los activos asignados al finalizar la relación laboral o contractual.',
    '¿Se verifica que todos los activos se hayan devuelto correctamente?',
    'A5.11'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con un procedimiento formal para clasificar la información en función de su sensibilidad, criticidad y requisitos de protección.',
    '¿Existe un procedimiento formal para clasificar la información según su sensibilidad y criticidad?',
    'A5.12'
),
(
    'Comprueba si la organización ha definido niveles de clasificación claros, como pública, interna, confidencial o restringida, de acuerdo con sus necesidades de seguridad.',
    '¿Se han definido niveles de clasificación (ej. pública, interna, confidencial, restringida)?',
    'A5.12'
),
(
    'Verifica si la información se etiqueta o identifica adecuadamente para reflejar su nivel de clasificación y facilitar su manejo seguro.',
    '¿La información clasificada está debidamente etiquetada o identificada según el nivel asignado?',
    'A5.12'
),
(
    'Determina si los empleados reciben formación o directrices para manejar correctamente la información según su nivel de clasificación.',
    '¿Los empleados reciben formación sobre cómo manejar la información en función de su clasificación?',
    'A5.12'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con un procedimiento formalizado que defina cómo debe etiquetarse la información en función de su nivel de clasificación.',
    '¿Existe un procedimiento formal para etiquetar la información según su nivel de clasificación?',
    'A5.13'
),
(
    'Comprueba si los formatos, tipos y criterios de etiquetado —tanto físicos como digitales— están claramente definidos, documentados y son conocidos por el personal.',
    '¿Los formatos de etiquetas (físicas o digitales) están definidos y son conocidos por el personal?',
    'A5.13'
),
(
    'Verifica si la información clasificada está correctamente etiquetada en todos los soportes de acuerdo con los procedimientos establecidos, garantizando su identificación y manejo adecuado.',
    '¿La información clasificada se encuentra correctamente etiquetada de acuerdo con los procedimientos establecidos?',
    'A5.13'
),
(
    'Determina si se realizan revisiones periódicas para asegurar que el etiquetado se mantiene actualizado, coherente y acorde al nivel de clasificación asignado.',
    '¿Se realizan revisiones periódicas para verificar que el etiquetado de la información se mantiene actualizado y correcto?',
    'A5.13'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de una política o procedimiento formal que regule cómo debe transferirse la información, tanto interna como externamente, garantizando su protección.',
    '¿Existe una política o procedimiento formal que regule la transferencia de información interna y externa?',
    'A5.14'
),
(
    'Comprueba si se emplean mecanismos seguros como cifrado, canales protegidos o protocolos seguros para transferir información sensible o confidencial.',
    '¿Se utilizan mecanismos seguros (cifrado, canales protegidos) al transferir información confidencial o sensible?',
    'A5.14'
),
(
    'Verifica si existen responsabilidades claramente definidas para autorizar, supervisar y controlar la transferencia de información entre partes internas o externas.',
    '¿Se han definido responsabilidades claras para autorizar y supervisar la transferencia de información?',
    'A5.14'
),
(
    'Determina si los métodos y mecanismos de transferencia se revisan periódicamente para asegurar que siguen siendo adecuados ante nuevas amenazas o requisitos técnicos.',
    '¿Se revisan periódicamente los métodos de transferencia para garantizar que siguen siendo adecuados y seguros?',
    'A5.14'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con una política formal de control de acceso aprobada por la dirección, que defina criterios, responsabilidades y procedimientos.',
    '¿Existe una política formal de control de acceso aprobada por la dirección?',
    'A5.15'
),
(
    'Comprueba si los accesos a sistemas, datos y recursos se otorgan siguiendo el principio de mínimo privilegio, asegurando que los usuarios solo acceden a lo estrictamente necesario.',
    '¿Los accesos a sistemas y datos están basados en el principio de necesidad de saber (least privilege)?',
    'A5.15'
),
(
    'Verifica si la organización revisa periódicamente los permisos otorgados a usuarios y cuentas privilegiadas, detectando accesos innecesarios o excesivos.',
    '¿Se revisan periódicamente los permisos de acceso de usuarios y cuentas privilegiadas?',
    'A5.15'
),
(
    'Determina si existen controles técnicos y procedimientos que permitan detectar, registrar y gestionar accesos no autorizados o intentos de intrusión.',
    '¿Los accesos no autorizados son detectados y gestionados mediante controles técnicos y procedimientos definidos?',
    'A5.15'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de un procedimiento formal que regule la creación, modificación y eliminación de identidades de usuario para garantizar un control adecuado.',
    '¿Existe un procedimiento formal para la creación, modificación y eliminación de identidades de usuario?',
    'A5.16'
),
(
    'Comprueba si cada usuario dispone de una identidad única que permita la trazabilidad de las acciones realizadas en los sistemas de información.',
    '¿Cada usuario dispone de una identidad única para el acceso a los sistemas de información?',
    'A5.16'
),
(
    'Verifica si se realizan revisiones periódicas de las identidades activas con el fin de identificar y eliminar cuentas innecesarias, obsoletas o inactivas.',
    '¿Se revisan periódicamente las identidades activas para detectar cuentas innecesarias o inactivas?',
    'A5.16'
),
(
    'Determina si la gestión de identidades se realiza de forma centralizada y bajo el control de personal autorizado, reduciendo riesgos de accesos indebidos.',
    '¿Las identidades de usuario se gestionan de manera centralizada y bajo control de personal autorizado?',
    'A5.16'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de una política formal que establezca los requisitos de autenticación necesarios para acceder a sistemas, servicios y datos.',
    '¿Existe una política formal que defina los requisitos de autenticación para el acceso a sistemas y datos?',
    'A5.17'
),
(
    'Comprueba si se emplean mecanismos de autenticación robusta, como contraseñas complejas o autenticación multifactor (MFA), especialmente en sistemas críticos o sensibles.',
    '¿Se utilizan mecanismos de autenticación robusta (ej. contraseñas fuertes, MFA) en los sistemas críticos?',
    'A5.17'
),
(
    'Verifica si la organización revisa periódicamente los métodos de autenticación para asegurar que continúan siendo eficaces frente a nuevas amenazas o requisitos técnicos.',
    '¿Se revisan periódicamente los métodos de autenticación para garantizar que siguen siendo adecuados?',
    'A5.17'
),
(
    'Determina si las credenciales de acceso se gestionan y almacenan de forma segura, evitando su uso compartido y aplicando medidas como cifrado o controles de protección.',
    '¿Las credenciales de acceso se gestionan de manera segura (almacenamiento cifrado, no compartidas entre usuarios)?',
    'A5.17'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de un procedimiento formal que regule la concesión, modificación y revocación de derechos de acceso, garantizando un control adecuado del ciclo de vida de los permisos.',
    '¿Existe un procedimiento formal para otorgar, modificar y revocar los derechos de acceso de los usuarios?',
    'A5.18'
),
(
    'Comprueba si los derechos de acceso se asignan siguiendo criterios basados en el rol funcional del usuario y bajo el principio de necesidad de saber.',
    '¿Los derechos de acceso se conceden únicamente en función del rol y la necesidad de cada usuario?',
    'A5.18'
),
(
    'Verifica si se realizan revisiones periódicas de los derechos de acceso con el objetivo de detectar permisos innecesarios, excesivos o que representen riesgos.',
    '¿Se revisan periódicamente los derechos de acceso para detectar y corregir excesos de privilegios?',
    'A5.18'
),
(
    'Determina si la organización revoca o deshabilita oportunamente los accesos cuando un usuario cambia de rol, de puesto o abandona la organización, evitando accesos indebidos.',
    '¿Se eliminan o deshabilitan oportunamente los accesos cuando un usuario cambia de puesto o deja la organización?',
    'A5.18'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización analiza los riesgos de seguridad asociados a un proveedor antes de iniciar la relación contractual, garantizando que no introduzca vulnerabilidades.',
    '¿Se evalúan los riesgos de seguridad de la información antes de establecer una relación con un proveedor?',
    'A5.19'
),
(
    'Comprueba si los contratos con proveedores incluyen cláusulas específicas de seguridad de la información, como confidencialidad, protección de datos, controles técnicos o auditorías.',
    '¿Los contratos con proveedores incluyen cláusulas específicas sobre seguridad de la información?',
    'A5.19'
),
(
    'Verifica si la organización supervisa periódicamente a los proveedores para asegurar que cumplen los requisitos de seguridad acordados en el contrato o política interna.',
    '¿Se supervisa periódicamente el cumplimiento de los requisitos de seguridad por parte de los proveedores?',
    'A5.19'
),
(
    'Determina si existen procedimientos definidos para gestionar incidentes de seguridad que afecten a proveedores o sean causados por ellos, garantizando una respuesta adecuada.',
    '¿Existen procedimientos para gestionar incidentes de seguridad relacionados con proveedores?',
    'A5.19'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los contratos firmados con proveedores incluyen requisitos de seguridad alineados con las políticas internas de la organización y las necesidades del SGSI.',
    '¿Los contratos con proveedores incluyen requisitos específicos de seguridad de la información acordes a la política de la organización?',
    'A5.20'
),
(
    'Comprueba si los contratos establecen claramente las responsabilidades de cada parte en cuanto a la protección de la información, continuidad del servicio y cumplimiento de controles.',
    '¿Se establecen responsabilidades claras entre la organización y el proveedor respecto a la protección de la información?',
    'A5.20'
),
(
    'Verifica si los contratos contemplan cláusulas relacionadas con confidencialidad, protección de datos personales, cumplimiento normativo y obligaciones de reporte.',
    '¿Los contratos contemplan cláusulas sobre confidencialidad, protección de datos y cumplimiento normativo?',
    'A5.20'
),
(
    'Determina si los contratos con proveedores se revisan y actualizan cuando cambian los riesgos, los servicios contratados o los requisitos del SGSI.',
    '¿Se revisan y actualizan los contratos de proveedores cuando cambian los riesgos o los servicios contratados?',
    'A5.20'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización analiza los riesgos de seguridad en toda la cadena de suministro TIC antes de establecer acuerdos, garantizando que no se introduzcan vulnerabilidades a través de terceros.',
    '¿Se evalúan los riesgos de seguridad de la información en toda la cadena de suministro de TIC antes de establecer acuerdos?',
    'A5.21'
),
(
    'Comprueba si los contratos con proveedores TIC incluyen medidas específicas para gestionar la seguridad en todos los eslabones de la cadena de suministro, incluyendo subcontratistas.',
    '¿Los contratos con proveedores de TIC incluyen medidas específicas para gestionar la seguridad en la cadena de suministro?',
    'A5.21'
),
(
    'Verifica si la organización supervisa regularmente que todos los proveedores implicados en la cadena TIC cumplen con los requisitos de seguridad establecidos.',
    '¿Se supervisa periódicamente el cumplimiento de los requisitos de seguridad por todos los eslabones de la cadena de suministro TIC?',
    'A5.21'
),
(
    'Determina si existen procedimientos formales para gestionar incidentes de seguridad que impacten o se originen en la cadena de suministro TIC, garantizando una respuesta eficaz.',
    '¿Existen procedimientos definidos para gestionar incidentes de seguridad que afecten a la cadena de suministro de TIC?',
    'A5.21'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización realiza supervisiones periódicas de los servicios prestados por los proveedores para verificar que cumplen los requisitos de seguridad acordados.',
    '¿Se supervisan de forma periódica los servicios prestados por los proveedores para verificar el cumplimiento de los requisitos de seguridad?',
    'A5.22'
),
(
    'Comprueba si existen procedimientos formales para gestionar cualquier cambio en los servicios proporcionados por los proveedores que pueda impactar en la seguridad de la información.',
    '¿Existen procedimientos formales para gestionar los cambios en los servicios de los proveedores que puedan afectar a la seguridad de la información?',
    'A5.22'
),
(
    'Verifica si la organización realiza una evaluación de riesgos antes de aprobar cambios significativos en los servicios de los proveedores, con el fin de evitar vulnerabilidades nuevas.',
    '¿Se evalúan los riesgos de seguridad antes de aprobar cambios significativos en los servicios de los proveedores?',
    'A5.22'
),
(
    'Determina si se mantienen registros documentados de todas las revisiones realizadas a los proveedores, así como de los cambios aplicados y aprobados.',
    '¿Se mantienen registros documentados de las revisiones y cambios realizados en los servicios de los proveedores?',
    'A5.22'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización ha definido y documentado requisitos de seguridad específicos para el uso de servicios en la nube, garantizando su correcta protección.',
    '¿Se han definido y documentado requisitos de seguridad específicos para el uso de servicios en la nube?',
    'A5.23'
),
(
    'Comprueba si los contratos con proveedores de servicios en la nube incluyen cláusulas relacionadas con seguridad de la información, privacidad y protección de datos.',
    '¿Los contratos con proveedores de servicios en la nube incluyen cláusulas de seguridad de la información y protección de datos?',
    'A5.23'
),
(
    'Verifica si se supervisa periódicamente al proveedor de servicios en la nube para asegurar que cumple los requisitos de seguridad establecidos en el contrato o normativa.',
    '¿Se supervisa periódicamente el cumplimiento de los requisitos de seguridad por parte del proveedor de servicios en la nube?',
    'A5.23'
),
(
    'Determina si los riesgos asociados a la migración, almacenamiento, tratamiento y disponibilidad de datos en la nube se gestionan de forma formal y documentada.',
    '¿Se gestionan los riesgos asociados a la migración, almacenamiento y procesamiento de datos en la nube de manera formal y documentada?',
    'A5.23'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de un procedimiento formal y documentado para gestionar incidentes de seguridad de la información, incluyendo detección, registro, respuesta y cierre.',
    '¿Existe un procedimiento formal y documentado para la gestión de incidentes de seguridad de la información?',
    'A5.24'
),
(
    'Comprueba si el personal conoce cómo identificar posibles incidentes de seguridad y los canales establecidos para reportarlos de forma rápida y adecuada.',
    '¿El personal conoce cómo identificar y reportar incidentes de seguridad de la información?',
    'A5.24'
),
(
    'Verifica si se realizan simulacros, ejercicios o pruebas periódicas para evaluar el grado de preparación de la organización frente a incidentes de seguridad.',
    '¿Se realizan simulacros o pruebas periódicas para evaluar la preparación frente a incidentes de seguridad?',
    'A5.24'
),
(
    'Determina si los procedimientos de gestión de incidentes se revisan y actualizan tras incidentes reales o cambios significativos en el entorno o en los sistemas.',
    '¿Se revisan y actualizan los procedimientos de gestión de incidentes tras producirse incidentes reales o cambios significativos?',
    'A5.24'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos que permitan analizar los eventos de seguridad y decidir cuándo deben escalarse y tratarse como incidentes.',
    '¿Existen procedimientos para evaluar los eventos de seguridad de la información y determinar si deben tratarse como incidentes?',
    'A5.25'
),
(
    'Comprueba si se han definido criterios claros y documentados para clasificar los eventos de seguridad según su criticidad, probabilidad y posible impacto en la organización.',
    '¿Se dispone de criterios claros y documentados para clasificar eventos según su criticidad y posible impacto?',
    'A5.25'
),
(
    'Verifica si el personal responsable de la gestión de eventos está adecuadamente formado para tomar decisiones oportunas sobre su tratamiento y escalado.',
    '¿El personal encargado está capacitado para tomar decisiones oportunas respecto al tratamiento de los eventos de seguridad?',
    'A5.25'
),
(
    'Determina si las evaluaciones realizadas sobre eventos de seguridad se registran y revisan periódicamente para mejorar el proceso de detección y respuesta.',
    '¿Se registran y revisan periódicamente las evaluaciones realizadas sobre los eventos de seguridad?',
    'A5.25'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con un plan formal de respuesta a incidentes, aprobado por la dirección, que establezca los pasos a seguir para gestionar incidentes de seguridad.',
    '¿Existe un plan formal para responder a incidentes de seguridad de la información aprobado por la dirección?',
    'A5.26'
),
(
    'Comprueba si el personal responsable conoce claramente sus funciones y responsabilidades durante el proceso de respuesta a incidentes.',
    '¿El personal responsable conoce sus funciones y responsabilidades durante la respuesta a incidentes?',
    'A5.26'
),
(
    'Verifica si se aplican procedimientos definidos para contener, erradicar y recuperar los sistemas afectados, asegurando una gestión eficaz del incidente.',
    '¿Se utilizan procedimientos definidos para contener, erradicar y recuperar sistemas afectados por incidentes?',
    'A5.26'
),
(
    'Determina si todas las acciones realizadas durante la respuesta a incidentes se registran de forma completa y precisa para permitir análisis posteriores y la mejora continua.',
    '¿Se registran todas las acciones realizadas durante la respuesta a incidentes para su posterior análisis y mejora?',
    'A5.26'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización analiza los incidentes una vez gestionados para identificar sus causas raíz y comprender qué falló o qué controles no funcionaron.',
    '¿Se analizan los incidentes de seguridad una vez gestionados para identificar sus causas raíz?',
    'A5.27'
),
(
    'Comprueba si los resultados del análisis post-incidente se documentan y se comunican a las partes relevantes para mejorar la concienciación y la prevención futura.',
    '¿Los resultados del análisis se documentan y se comparten con las partes relevantes de la organización?',
    'A5.27'
),
(
    'Verifica si las lecciones aprendidas se utilizan para actualizar políticas, procedimientos o controles de seguridad, fomentando la mejora continua del SGSI.',
    '¿Las lecciones aprendidas se utilizan para actualizar políticas, procedimientos o controles de seguridad?',
    'A5.27'
),
(
    'Determina si se realiza un seguimiento posterior para verificar que las mejoras identificadas tras incidentes anteriores se han implementado correctamente.',
    '¿Se realiza un seguimiento para verificar que las mejoras derivadas de incidentes anteriores se han implementado?',
    'A5.27'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos formales para recopilar evidencias en caso de incidentes de seguridad, garantizando que el proceso sea controlado y consistente.',
    '¿Existen procedimientos formales para la recopilación de evidencias relacionadas con incidentes de seguridad de la información?',
    'A5.28'
),
(
    'Comprueba si el personal responsable está capacitado en técnicas apropiadas de recopilación de evidencias para asegurar su validez, integridad y aceptabilidad en auditorías o procesos legales.',
    '¿El personal encargado de recopilar evidencias está capacitado en técnicas que garanticen su validez y admisibilidad?',
    'A5.28'
),
(
    'Verifica si las evidencias recopiladas se almacenan de forma segura, con controles que garanticen su integridad, cadena de custodia y trazabilidad.',
    '¿Las evidencias recopiladas se almacenan y protegen de manera que se asegure su integridad y trazabilidad?',
    'A5.28'
),
(
    'Determina si todo el proceso de recopilación, custodia y almacenamiento de evidencias se documenta y revisa para asegurar el cumplimiento con requisitos legales, normativos o internos.',
    '¿Se documenta y revisa todo el proceso de recopilación de evidencias para cumplir con requisitos legales o regulatorios?',
    'A5.28'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con procedimientos documentados para garantizar la seguridad de la información durante interrupciones planificadas o imprevistas.',
    '¿Existen procedimientos documentados para mantener la seguridad de la información durante interrupciones planificadas o no planificadas?',
    'A5.29'
),
(
    'Comprueba si el personal conoce las medidas necesarias para proteger la información cuando se producen interrupciones en servicios críticos.',
    '¿El personal conoce las medidas de seguridad a aplicar en caso de interrupción de servicios críticos?',
    'A5.29'
),
(
    'Verifica si se realizan pruebas periódicas para asegurar que los controles de seguridad siguen funcionando adecuadamente durante interrupciones.',
    '¿Se realizan pruebas periódicas para asegurar que los controles de seguridad funcionan durante interrupciones?',
    'A5.29'
),
(
    'Determina si los procedimientos se revisan y actualizan tras interrupciones significativas o fallos en los servicios para mejorar la resiliencia.',
    '¿Se revisan y actualizan los procedimientos de seguridad después de interrupciones significativas o fallos en los servicios?',
    'A5.29'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con planes formales y documentados para asegurar la continuidad de los servicios TIC ante interrupciones, fallos o desastres.',
    '¿Existen planes documentados para garantizar la continuidad de los servicios TIC en caso de interrupciones o desastres?',
    'A5.30'
),
(
    'Comprueba si existen mecanismos de respaldo, recuperación y restauración de sistemas y datos críticos que hayan sido definidos, implementados y probados regularmente.',
    '¿Se han definido y probado mecanismos de respaldo y recuperación para sistemas y datos críticos?',
    'A5.30'
),
(
    'Verifica si el personal responsable está adecuadamente formado y conoce los procedimientos de continuidad TIC que debe aplicar ante una interrupción.',
    '¿El personal responsable está capacitado en los procedimientos de continuidad de los servicios TIC?',
    'A5.30'
),
(
    'Determina si los planes de continuidad de los servicios TIC se revisan y actualizan periódicamente para asegurar su eficacia frente a nuevos riesgos o cambios tecnológicos.',
    '¿Se revisan y actualizan periódicamente los planes de continuidad TIC para asegurar su eficacia ante nuevos riesgos?',
    'A5.30'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización identifica y mantiene actualizados todos los requisitos legales, reglamentarios y contractuales relacionados con la seguridad de la información y protección de datos.',
    '¿La organización identifica y mantiene actualizados los requisitos legales, reglamentarios y contractuales aplicables a la seguridad de la información?',
    'A5.31'
),
(
    'Comprueba si se han asignado responsabilidades claras dentro de la organización para asegurar y supervisar el cumplimiento de dichos requisitos.',
    '¿Se han asignado responsabilidades claras para asegurar el cumplimiento de estos requisitos?',
    'A5.31'
),
(
    'Verifica si la organización realiza revisiones periódicas para comprobar el cumplimiento de los requisitos legales, normativos y contractuales aplicables.',
    '¿Se realizan revisiones periódicas para verificar que la organización cumple con los requisitos aplicables?',
    'A5.31'
),
(
    'Determina si existen registros actualizados y accesibles que evidencien el cumplimiento de los requisitos legales, reglamentarios y contractuales.',
    '¿Se conservan registros que evidencien el cumplimiento de los requisitos legales, reglamentarios y contractuales?',
    'A5.31'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de políticas o procedimientos para proteger los derechos de propiedad intelectual, incluyendo el uso legal de software, contenido y materiales.',
    '¿La organización cuenta con políticas o procedimientos para proteger los derechos de propiedad intelectual (DPI)?',
    'A5.32'
),
(
    'Comprueba si la organización verifica que el software, licencias, contenidos y materiales utilizados cumplen con las normativas de propiedad intelectual y uso autorizado.',
    '¿Se verifica que el software, licencias y contenidos utilizados cumplen con la normativa de DPI?',
    'A5.32'
),
(
    'Verifica si el personal recibe formación o comunicación sobre la importancia del respeto y cumplimiento de los derechos de propiedad intelectual.',
    '¿El personal recibe formación o comunicación sobre el respeto a los derechos de propiedad intelectual?',
    'A5.32'
),
(
    'Determina si se realizan auditorías o revisiones periódicas para asegurar que la organización cumple con los requisitos legales y contractuales relacionados con la propiedad intelectual.',
    '¿Se realizan auditorías o revisiones periódicas para asegurar el cumplimiento en materia de propiedad intelectual?',
    'A5.32'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos formales para proteger la integridad, confidencialidad y disponibilidad de los registros durante todo su ciclo de vida.',
    '¿Existen procedimientos formales para garantizar la integridad, confidencialidad y disponibilidad de los registros?',
    'A5.33'
),
(
    'Comprueba si se han definido plazos de retención y mecanismos de eliminación segura de registros conforme a requisitos legales, reglamentarios o contractuales.',
    '¿Se definen plazos de retención y eliminación segura de registros conforme a requisitos legales o contractuales?',
    'A5.33'
),
(
    'Verifica si los registros están protegidos contra accesos no autorizados, modificaciones indebidas o eliminaciones no controladas mediante controles técnicos y organizativos.',
    '¿Los registros se protegen contra accesos no autorizados, modificaciones o eliminaciones indebidas?',
    'A5.33'
),
(
    'Determina si los controles implementados para la protección de registros se revisan periódicamente para asegurar su eficacia y cumplimiento de los requisitos aplicables.',
    '¿Se revisan periódicamente los controles implementados para asegurar la correcta protección de los registros?',
    'A5.33'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de políticas y procedimientos formalizados para garantizar la privacidad y la protección adecuada de los datos personales en todos los procesos donde se tratan.',
    '¿La organización cuenta con políticas y procedimientos para garantizar la privacidad y la protección de los datos personales?',
    'A5.34'
),
(
    'Comprueba si la organización cumple los requisitos legales y reglamentarios aplicables en materia de protección de datos personales, como el RGPD u otras normativas sectoriales.',
    '¿Se cumplen los requisitos legales y reglamentarios aplicables en materia de protección de datos personales (ej. RGPD)?',
    'A5.34'
),
(
    'Verifica si el personal recibe formación periódica sobre el tratamiento seguro y adecuado de los datos personales, tal como exige la normativa de protección de datos.',
    '¿El personal recibe formación periódica sobre el tratamiento adecuado y seguro de los datos personales?',
    'A5.34'
),
(
    'Determina si los controles implementados para proteger los datos personales se revisan y actualizan regularmente para asegurar su eficacia frente a nuevos riesgos o cambios normativos.',
    '¿Se revisan y actualizan regularmente los controles implementados para proteger los datos personales?',
    'A5.34'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización realiza revisiones independientes de la seguridad de la información en intervalos planificados, garantizando una visión objetiva del desempeño del SGSI.',
    '¿La organización realiza revisiones independientes de la seguridad de la información a intervalos planificados?',
    'A5.35'
),
(
    'Comprueba si las revisiones independientes son realizadas por personal interno que no participe en los procesos revisados o por auditores externos, asegurando imparcialidad.',
    '¿Las revisiones independientes son llevadas a cabo por personal interno ajeno a las áreas revisadas o por terceros externos?',
    'A5.35'
),
(
    'Verifica si los resultados de las revisiones independientes se documentan adecuadamente y se comunican a la alta dirección para la toma de decisiones.',
    '¿Los resultados de las revisiones independientes se documentan y comunican a la dirección?',
    'A5.35'
),
(
    'Determina si las observaciones detectadas en las revisiones independientes generan acciones correctivas que son implementadas y seguidas para mejorar el SGSI.',
    '¿Se implementan acciones correctivas derivadas de las observaciones detectadas en las revisiones?',
    'A5.35'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización supervisa periódicamente el cumplimiento de las políticas y estándares de seguridad de la información para garantizar su aplicación efectiva.',
    '¿La organización supervisa regularmente el cumplimiento de las políticas y estándares de seguridad de la información?',
    'A5.36'
),
(
    'Comprueba si el personal es evaluado de manera periódica para asegurar que sus prácticas y comportamientos se ajustan a las políticas y estándares definidos.',
    '¿El personal es evaluado periódicamente para verificar que sus prácticas se ajustan a las políticas y estándares establecidos?',
    'A5.36'
),
(
    'Verifica si se registran, reportan y gestionan las desviaciones detectadas respecto a las políticas y estándares de seguridad, facilitando la mejora continua.',
    '¿Se registran y reportan las desviaciones detectadas respecto a las políticas y estándares de seguridad?',
    'A5.36'
),
(
    'Determina si la organización aplica acciones correctivas en casos de incumplimiento de políticas o estándares, asegurando que se toman medidas para evitar recurrencias.',
    '¿Se aplican acciones correctivas en caso de incumplimientos de políticas o estándares de seguridad de la información?',
    'A5.36'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de procedimientos operativos documentados que respalden la gestión de la seguridad de la información y garanticen la ejecución consistente de las actividades.',
    '¿La organización dispone de procedimientos operativos documentados para la gestión de la seguridad de la información?',
    'A5.37'
),
(
    'Comprueba si los procedimientos documentados están aprobados formalmente y se mantienen actualizados conforme a cambios en los procesos, servicios o tecnologías.',
    '¿Los procedimientos documentados están aprobados y actualizados conforme a cambios en los procesos o tecnología?',
    'A5.37'
),
(
    'Verifica si el personal relevante tiene acceso a los procedimientos, los conoce y los aplica correctamente en su trabajo diario.',
    '¿El personal relevante tiene acceso a los procedimientos documentados y conoce su aplicación?',
    'A5.37'
),
(
    'Determina si los procedimientos operativos se revisan periódicamente para asegurar que siguen siendo vigentes, eficaces y alineados con los requisitos del SGSI.',
    '¿Se revisan periódicamente los procedimientos operativos para asegurar su vigencia y eficacia?',
    'A5.37'
);
