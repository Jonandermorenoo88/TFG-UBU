USE isoassetmanager;

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de una política formal que regule la gestión, el uso y la protección de los dispositivos de usuario final.',
    '¿Existe una política formal para la gestión y uso seguro de los dispositivos de usuario final (ordenadores, portátiles, móviles, tablets)?',
    'A8.1'
),
(
    'Comprueba que los dispositivos utilizados por los usuarios cuentan con medidas de seguridad básicas para proteger la información que contienen.',
    '¿Los dispositivos de usuario final cuentan con medidas de seguridad básicas (antivirus, cifrado, contraseñas)?',
    'A8.1'
),
(
    'Verifica si todos los dispositivos reciben configuraciones de seguridad estandarizadas antes de ser utilizados en la organización.',
    '¿Se aplican configuraciones de seguridad estándar en todos los dispositivos antes de ponerlos en producción?',
    'A8.1'
),
(
    'Analiza si se realiza una revisión periódica del estado de seguridad de los dispositivos para asegurar su protección continua.',
    '¿Se revisa periódicamente el estado de seguridad de los dispositivos de usuario final?',
    'A8.1'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si existe un procedimiento formal y documentado para la asignación y revocación de derechos de acceso privilegiado, garantizando un control adecuado sobre cuentas con permisos especiales.',
    '¿Existe un procedimiento formal para asignar y revocar derechos de acceso privilegiado?',
    'A8.2'
),
(
    'Verifica que los accesos privilegiados solo se otorguen a personal autorizado y cuenten con una justificación debidamente documentada.',
    '¿Los accesos privilegiados se conceden solo a personal autorizado y con justificación documentada?',
    'A8.2'
),
(
    'Comprueba si el uso de cuentas con privilegios especiales está sujeto a supervisión y registro para asegurar trazabilidad y prevenir abusos.',
    '¿Se supervisa y registra el uso de las cuentas con privilegios especiales?',
    'A8.2'
),
(
    'Analiza si se revisan periódicamente los accesos privilegiados para identificar excesos de permisos, usos indebidos o cuentas innecesarias.',
    '¿Se revisan periódicamente los accesos privilegiados para detectar abusos o excesos de permisos?',
    'A8.2'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización aplica controles adecuados para restringir el acceso a la información siguiendo el principio de mínima necesidad o least privilege.',
    '¿La organización aplica controles para restringir el acceso a la información según el principio de mínima necesidad?',
    'A8.3'
),
(
    'Comprueba si los permisos de acceso están definidos, documentados y asignados en función de roles o perfiles de usuario establecidos.',
    '¿Los permisos de acceso a la información están definidos y documentados por roles o perfiles de usuario?',
    'A8.3'
),
(
    'Verifica si se revisan periódicamente los accesos a información sensible para identificar usuarios con permisos indebidos o excesivos.',
    '¿Se revisa periódicamente el acceso a información sensible para identificar accesos indebidos?',
    'A8.3'
),
(
    'Analiza si el acceso se bloquea o revoca automáticamente cuando un usuario cambia de puesto, es reasignado o abandona la organización.',
    '¿Se bloquea automáticamente el acceso cuando un usuario cambia de puesto o abandona la organización?',
    'A8.3'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el acceso al código fuente de las aplicaciones está adecuadamente restringido únicamente a desarrolladores o personal expresamente autorizado.',
    '¿El acceso al código fuente está restringido únicamente a desarrolladores autorizados?',
    'A8.4'
),
(
    'Verifica si los accesos al código fuente, especialmente de aplicaciones críticas, son supervisados y quedan registrados para asegurar trazabilidad.',
    '¿Se registran y supervisan los accesos al código fuente de las aplicaciones críticas?',
    'A8.4'
),
(
    'Comprueba si se aplican controles de seguridad como repositorios autorizados, sistemas de control de versiones y mecanismos de integridad para proteger el código fuente.',
    '¿Se aplican controles de seguridad (repositorios, control de versiones) para proteger la integridad del código fuente?',
    'A8.4'
),
(
    'Analiza si se realizan revisiones periódicas de los permisos otorgados sobre el código fuente para detectar accesos innecesarios o riesgosos.',
    '¿Se revisa periódicamente quién tiene permisos de lectura o modificación sobre el código fuente?',
    'A8.4'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los sistemas críticos utilizan mecanismos de autenticación robusta, como contraseñas seguras o autenticación multifactor (MFA), para proteger el acceso.',
    '¿Se utilizan mecanismos de autenticación robusta (ej. contraseñas seguras, MFA) en los sistemas críticos?',
    'A8.5'
),
(
    'Verifica si existen políticas formales que definen los requisitos de complejidad, longitud, caducidad y gestión de credenciales de usuario.',
    '¿Existen políticas definidas sobre la complejidad y caducidad de las credenciales de usuario?',
    'A8.5'
),
(
    'Comprueba que las credenciales de autenticación se almacenan de forma segura mediante técnicas como hash, sal y cifrado, evitando almacenamiento en texto plano.',
    '¿Las credenciales de autenticación se almacenan de forma segura (hash y cifrado)?',
    'A8.5'
),
(
    'Analiza si los métodos de autenticación implementados se revisan periódicamente para asegurar que continúan siendo eficaces frente a amenazas actuales.',
    '¿Se revisan periódicamente los métodos de autenticación para garantizar que siguen siendo efectivos?',
    'A8.5'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización supervisa de manera continua el uso de los recursos de los sistemas para evitar que se superen sus límites operativos.',
    '¿Se supervisa el uso de los recursos de los sistemas para asegurar que no se superen sus capacidades?',
    'A8.6'
),
(
    'Comprueba si la planificación de la capacidad se realiza considerando las necesidades actuales y futuras de la organización, garantizando un crecimiento sostenible.',
    '¿Se planifica la capacidad de los sistemas de acuerdo con las necesidades actuales y futuras de la organización?',
    'A8.6'
),
(
    'Verifica si existen alertas, umbrales o mecanismos automáticos que avisen cuando los recursos del sistema alcanzan niveles críticos.',
    '¿Existen alertas o mecanismos de aviso cuando los recursos alcanzan niveles críticos de utilización?',
    'A8.6'
),
(
    'Analiza si la capacidad de los sistemas es revisada periódicamente para evitar interrupciones, saturación o degradación de los servicios.',
    '¿Se revisa periódicamente la capacidad de los sistemas para prevenir interrupciones o degradación de servicios?',
    'A8.6'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de soluciones de protección contra software malicioso en todos los sistemas, garantizando detección y mitigación efectiva.',
    '¿Todos los sistemas cuentan con soluciones de protección contra software malicioso (antivirus, antimalware)?',
    'A8.7'
),
(
    'Comprueba si las firmas, bases de datos y mecanismos de detección de malware se actualizan regularmente para asegurar protección frente a amenazas nuevas.',
    '¿Se actualizan regularmente las firmas y mecanismos de detección de malware?',
    'A8.7'
),
(
    'Analiza si el personal recibe formación y concienciación sobre cómo evitar infecciones, reconocer amenazas y actuar ante posibles incidentes.',
    '¿El personal recibe formación sobre cómo prevenir infecciones por software malicioso?',
    'A8.7'
),
(
    'Verifica si los sistemas monitorizan e informan sobre intentos de infección, y si las acciones de mitigación quedan registradas para análisis posterior.',
    '¿Se monitorizan y registran los intentos de infección y las acciones de mitigación aplicadas?',
    'A8.7'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de un procedimiento formal para identificar, evaluar y gestionar vulnerabilidades técnicas en sus sistemas.',
    '¿Existe un procedimiento formal para identificar, evaluar y gestionar vulnerabilidades técnicas?',
    'A8.8'
),
(
    'Comprueba si se realizan escaneos periódicos de vulnerabilidades en sistemas y aplicaciones para detectar debilidades de seguridad.',
    '¿Se realizan escaneos periódicos de vulnerabilidades en los sistemas y aplicaciones?',
    'A8.8'
),
(
    'Verifica si las vulnerabilidades detectadas se clasifican y priorizan según su criticidad, garantizando que las más graves se tratan primero.',
    '¿Las vulnerabilidades detectadas se priorizan y corrigen según su criticidad?',
    'A8.8'
),
(
    'Analiza si se mantiene un registro actualizado de las vulnerabilidades identificadas y de las acciones de corrección aplicadas.',
    '¿Se mantiene un registro actualizado de las vulnerabilidades encontradas y tratadas?',
    'A8.8'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización aplica configuraciones estándar y seguras en todos los sistemas antes de su puesta en producción, reduciendo riesgos asociados a configuraciones débiles.',
    '¿Se aplican configuraciones estándar y seguras en todos los sistemas antes de entrar en producción?',
    'A8.9'
),
(
    'Comprueba si los cambios en las configuraciones están documentados, supervisados y aprobados formalmente para asegurar trazabilidad y control.',
    '¿Los cambios en las configuraciones están documentados y aprobados formalmente?',
    'A8.9'
),
(
    'Verifica si existe supervisión continua para detectar cambios no autorizados en la configuración de los sistemas, lo cual ayuda a prevenir brechas de seguridad.',
    '¿Se supervisa la configuración de los sistemas para detectar modificaciones no autorizadas?',
    'A8.9'
),
(
    'Analiza si se realizan revisiones periódicas de las configuraciones con el fin de garantizar que se mantienen alineadas con las políticas y estándares de seguridad establecidos.',
    '¿Se realizan revisiones periódicas de las configuraciones para asegurar su alineación con las políticas de seguridad?',
    'A8.9'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con procedimientos documentados para asegurar la eliminación segura de información en medios físicos y digitales.',
    '¿Existen procedimientos documentados para la eliminación segura de información en medios físicos y digitales?',
    'A8.10'
),
(
    'Comprueba si se aplican técnicas de borrado seguro o destrucción física al eliminar información sensible para evitar fugas o accesos indebidos.',
    '¿Se utilizan técnicas de borrado seguro o destrucción física para la información sensible?',
    'A8.10'
),
(
    'Verifica si las actividades de eliminación de información quedan registradas y son supervisadas por personal autorizado, garantizando trazabilidad y control.',
    '¿La eliminación de información queda registrada y es supervisada por personal autorizado?',
    'A8.10'
),
(
    'Analiza si la organización revisa periódicamente los métodos empleados para eliminar información, asegurando su cumplimiento con requisitos legales y normativos.',
    '¿Se verifica periódicamente que los métodos de eliminación cumplen con requisitos legales y normativos?',
    'A8.10'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización aplica técnicas de enmascaramiento de datos para proteger información sensible cuando se utiliza en entornos no productivos como pruebas o desarrollo.',
    '¿Se aplican técnicas de enmascaramiento de datos para proteger información sensible en entornos de pruebas o desarrollo?',
    'A8.11'
),
(
    'Comprueba si las técnicas y requisitos de enmascaramiento están documentados en políticas o procedimientos de seguridad aprobados.',
    '¿El enmascaramiento de datos está documentado en políticas o procedimientos de seguridad?',
    'A8.11'
),
(
    'Verifica si el personal conoce qué datos requieren enmascaramiento y en qué contextos deben aplicarse estas medidas.',
    '¿El personal conoce qué datos deben ser enmascarados y en qué circunstancias?',
    'A8.11'
),
(
    'Analiza si los métodos de enmascaramiento utilizados se revisan periódicamente para asegurar su eficacia frente a nuevas amenazas o cambios tecnológicos.',
    '¿Se revisa periódicamente la eficacia de los métodos de enmascaramiento utilizados?',
    'A8.11'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de controles técnicos, como sistemas DLP (Data Loss Prevention), destinados a prevenir la fuga no autorizada de información confidencial.',
    '¿Existen controles técnicos (DLP) para prevenir la fuga no autorizada de información confidencial?',
    'A8.12'
),
(
    'Comprueba si se supervisan los distintos canales de comunicación, como correo electrónico, navegación web o dispositivos extraíbles, para detectar posibles fugas de información.',
    '¿Se supervisan los canales de comunicación (correo, web, dispositivos extraíbles) para detectar fugas de información?',
    'A8.12'
),
(
    'Verifica si existen políticas formales que regulan la transferencia, compartición y tratamiento de información sensible dentro y fuera de la organización.',
    '¿Se han definido políticas que regulen la transferencia y compartición de información sensible?',
    'A8.12'
),
(
    'Analiza si los incidentes relacionados con fuga de información se revisan periódicamente y si se aplican medidas correctivas efectivas.',
    '¿Se revisan periódicamente los incidentes de fuga de información y las medidas correctivas aplicadas?',
    'A8.12'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización realiza copias de seguridad de la información crítica siguiendo una planificación definida que asegure la disponibilidad de los datos.',
    '¿Se realizan copias de seguridad de la información crítica siguiendo una planificación definida?',
    'A8.13'
),
(
    'Comprueba si las copias de seguridad se almacenan de forma segura, incluyendo ubicaciones protegidas, cifrado y medidas contra accesos no autorizados.',
    '¿Las copias de seguridad se almacenan de forma segura y en ubicaciones protegidas?',
    'A8.13'
),
(
    'Verifica si se realizan pruebas periódicas de restauración para asegurar que las copias de seguridad son funcionales y efectivas.',
    '¿Se prueban periódicamente las restauraciones de las copias de seguridad para verificar su eficacia?',
    'A8.13'
),
(
    'Analiza si la política de copias de seguridad cumple con los requisitos legales, normativos y contractuales aplicables, garantizando su adecuación.',
    '¿La política de copias de seguridad está alineada con requisitos legales, normativos y contractuales?',
    'A8.13'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de mecanismos de redundancia que garanticen la disponibilidad de los sistemas críticos en caso de fallos o interrupciones.',
    '¿Existen mecanismos de redundancia para garantizar la disponibilidad de los sistemas críticos?',
    'A8.14'
),
(
    'Comprueba si los sistemas redundantes son sometidos a pruebas periódicas con el fin de verificar su funcionamiento correcto ante situaciones reales o simuladas.',
    '¿Los sistemas redundantes son probados periódicamente para asegurar su correcto funcionamiento?',
    'A8.14'
),
(
    'Verifica si la infraestructura redundante cuenta con monitorización continua que permita detectar fallos o degradaciones en los sistemas de respaldo.',
    '¿Se monitoriza el estado de las infraestructuras redundantes para detectar fallos?',
    'A8.14'
),
(
    'Analiza si la planificación y diseño de la redundancia están alineados con los requisitos de continuidad del negocio establecidos por la organización.',
    '¿La planificación de redundancia está alineada con los requisitos de continuidad de negocio de la organización?',
    'A8.14'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los sistemas, aplicaciones y redes registran eventos relevantes de seguridad para permitir la detección, análisis y respuesta ante incidentes.',
    '¿Se registran los eventos relevantes de seguridad en sistemas, aplicaciones y redes?',
    'A8.15'
),
(
    'Comprueba si los registros contienen suficiente información, como usuario, fecha, hora y actividad, para permitir un análisis adecuado de los eventos registrados.',
    '¿Los registros incluyen información suficiente (usuario, fecha, hora, actividad) para su análisis?',
    'A8.15'
),
(
    'Verifica si los registros están protegidos contra accesos no autorizados, modificaciones o eliminaciones indebidas, garantizando su integridad y disponibilidad.',
    '¿Se protegen los registros contra accesos no autorizados, alteraciones o eliminaciones indebidas?',
    'A8.15'
),
(
    'Analiza si los registros de seguridad son revisados periódicamente con el fin de detectar incidentes, anomalías o comportamientos sospechosos.',
    '¿Se revisan periódicamente los registros de seguridad para detectar incidentes o anomalías?',
    'A8.15'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización supervisa las actividades de usuarios y sistemas para detectar comportamientos anómalos, sospechosos o no autorizados.',
    '¿Se supervisan las actividades de los usuarios y sistemas para detectar comportamientos anómalos o no autorizados?',
    'A8.16'
),
(
    'Comprueba si existen herramientas de monitorización configuradas para generar alertas cuando se detectan posibles incidentes de seguridad.',
    '¿Existen herramientas de monitorización configuradas para alertar sobre incidentes de seguridad?',
    'A8.16'
),
(
    'Verifica si los registros resultantes de la monitorización son revisados periódicamente por personal autorizado, garantizando una supervisión adecuada.',
    '¿Los registros de monitorización se revisan periódicamente por personal autorizado?',
    'A8.16'
),
(
    'Analiza si la organización define claramente qué actividades deben ser monitorizadas, en función de riesgos, criticidad y políticas internas.',
    '¿Se definen criterios claros para determinar qué actividades deben ser monitorizadas?',
    'A8.16'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los sistemas críticos están sincronizados con una fuente de tiempo confiable, como servidores NTP, para asegurar la coherencia en registros y operaciones.',
    '¿Los sistemas críticos están sincronizados con una fuente de tiempo confiable (ej. NTP)?',
    'A8.17'
),
(
    'Comprueba si la organización supervisa regularmente la correcta sincronización de los relojes en servidores, dispositivos de red y otros sistemas esenciales.',
    '¿Se supervisa la correcta sincronización de relojes en servidores y dispositivos de red?',
    'A8.17'
),
(
    'Verifica si la falta de sincronización se gestiona como una desviación de seguridad, ya que puede afectar registros, auditorías e investigaciones.',
    '¿La falta de sincronización de relojes se gestiona como una desviación de seguridad?',
    'A8.17'
),
(
    'Analiza si se revisa periódicamente la configuración de sincronización de tiempo para garantizar que sigue siendo adecuada y funcional.',
    '¿Se revisa periódicamente la configuración de sincronización de tiempo en los sistemas?',
    'A8.17'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica que los programas utilitarios que permiten realizar acciones privilegiadas solo pueden ser utilizados por personal autorizado, reduciendo riesgos de abuso o alteración de sistemas.',
    '¿El uso de programas utilitarios que otorgan privilegios especiales está restringido a personal autorizado?',
    'A8.18'
),
(
    'Evalúa si las acciones realizadas con herramientas utilitarias administrativas quedan registradas para asegurar trazabilidad y permitir auditorías de seguridad.',
    '¿Se registran las actividades realizadas con programas utilitarios de administración?',
    'A8.18'
),
(
    'Comprueba que los programas utilitarios con privilegios sean evaluados antes de instalarse en sistemas críticos, asegurando que no introduzcan vulnerabilidades.',
    '¿Los programas utilitarios con privilegios se evalúan antes de ser instalados en sistemas críticos?',
    'A8.18'
),
(
    'Confirma si la organización revisa periódicamente la lista de programas utilitarios permitidos para asegurar que solo existan los necesarios y autorizados.',
    '¿Se revisa periódicamente la lista de programas utilitarios permitidos?',
    'A8.18'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica que solo personal autorizado pueda instalar software en los entornos de producción, evitando cambios no controlados que puedan afectar la estabilidad o seguridad.',
    '¿La instalación de software en entornos de producción está restringida a personal autorizado?',
    'A8.19'
),
(
    'Confirma que existen procedimientos formales y documentados para aprobar nuevas instalaciones de software en sistemas operativos productivos.',
    '¿Existen procedimientos formales para aprobar nuevas instalaciones en sistemas operativos productivos?',
    'A8.19'
),
(
    'Evalúa si la organización supervisa activamente la instalación de software no autorizado para prevenir riesgos de seguridad o brechas.',
    '¿Se supervisa la instalación de software no autorizado en equipos y servidores?',
    'A8.19'
),
(
    'Comprueba que la organización revisa de manera periódica las aplicaciones instaladas en los sistemas de producción para asegurar que siguen siendo necesarias, seguras y autorizadas.',
    '¿Se revisan periódicamente las aplicaciones instaladas en los sistemas de producción?',
    'A8.19'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de controles perimetrales para proteger las redes internas frente a accesos no autorizados, reduciendo el riesgo de intrusiones.',
    '¿Existen controles de seguridad perimetral para proteger las redes internas de accesos no autorizados?',
    'A8.20'
),
(
    'Comprueba si las comunicaciones de red son monitorizadas para detectar actividad sospechosa, intrusiones o anomalías de tráfico.',
    '¿Se monitorizan las comunicaciones de red para detectar intentos de intrusión o tráfico sospechoso?',
    'A8.20'
),
(
    'Verifica si se aplican mecanismos de control de acceso a la red, tales como firewalls, sistemas NAC o filtros de conexión.',
    '¿Se aplican mecanismos de control de acceso a la red (ej. firewalls, NAC)?',
    'A8.20'
),
(
    'Confirma que las configuraciones de los equipos y dispositivos de red son revisadas periódicamente para asegurar que no existan vulnerabilidades ni configuraciones inseguras.',
    '¿Se revisan periódicamente las configuraciones de los equipos de red para garantizar su seguridad?',
    'A8.20'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica si los servicios de red, tanto internos como externos, cuentan con medidas de seguridad definidas para proteger la infraestructura y los datos transmitidos.',
    '¿Los servicios de red (internos y externos) están protegidos mediante medidas de seguridad definidas?',
    'A8.21'
),
(
    'Evalúa si los contratos establecidos con proveedores de servicios de red incluyen cláusulas específicas que garanticen la seguridad de la información.',
    '¿Los contratos con proveedores de servicios de red incluyen requisitos de seguridad específicos?',
    'A8.21'
),
(
    'Comprueba que los servicios de red son monitorizados continuamente para detectar fallos, degradación del rendimiento o incidentes de seguridad.',
    '¿Se monitoriza el rendimiento y la seguridad de los servicios de red de forma continua?',
    'A8.21'
),
(
    'Determina si las medidas de seguridad aplicadas a los servicios de red se revisan periódicamente para asegurar su eficacia ante nuevas amenazas.',
    '¿Se revisa periódicamente la eficacia de las medidas de seguridad aplicadas a los servicios de red?',
    'A8.21'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la red está segmentada correctamente para separar entornos críticos de aquellos no críticos, reduciendo riesgos de propagación de amenazas.',
    '¿La red está segmentada para separar entornos críticos de los no críticos?',
    'A8.22'
),
(
    'Verifica si existen medidas de seguridad que limiten el tráfico entre segmentos según las políticas establecidas por la organización.',
    '¿Existen medidas para limitar el tráfico entre segmentos de red según políticas de seguridad?',
    'A8.22'
),
(
    'Comprueba si la configuración de segmentación se revisa periódicamente para detectar desviaciones o configuraciones inseguras.',
    '¿Se revisa periódicamente la configuración de segmentación de la red para detectar desviaciones?',
    'A8.22'
),
(
    'Revisa si se utilizan mecanismos como firewalls, VLANs o listas de control de acceso para reforzar la segmentación y controlar el tráfico.',
    '¿Se aplican mecanismos de control (firewalls, VLANs) para reforzar la segmentación de la red?',
    'A8.22'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización utiliza controles de filtrado web para prevenir el acceso a sitios maliciosos o no autorizados, reduciendo riesgos de malware y fuga de información.',
    '¿Existen controles de filtrado web para limitar el acceso a sitios no autorizados o maliciosos?',
    'A8.23'
),
(
    'Verifica si el filtrado web se aplica de manera uniforme, tanto a usuarios internos como a aquellos que acceden remotamente.',
    '¿El filtrado web se aplica tanto a usuarios internos como a accesos remotos?',
    'A8.23'
),
(
    'Comprueba si los registros de acceso web son supervisados para detectar actividad anómala o potencialmente dañina.',
    '¿Se supervisan los registros de acceso web para detectar actividades sospechosas?',
    'A8.23'
),
(
    'Revisa si las reglas, listas blancas y listas negras utilizadas en el filtrado web se actualizan periódicamente para mantener su eficacia.',
    '¿Se revisan y actualizan periódicamente las reglas y listas del filtrado web?',
    'A8.23'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica si la organización ha definido una política formal que regula el uso de controles criptográficos para proteger la información.',
    '¿Existe una política formal para el uso de controles criptográficos en la organización?',
    'A8.24'
),
(
    'Comprueba si se utiliza cifrado adecuado para proteger la información tanto en tránsito como en reposo.',
    '¿Se emplea cifrado para proteger la información en tránsito y en reposo?',
    'A8.24'
),
(
    'Evalúa si existen procedimientos documentados para la generación, almacenamiento, distribución, renovación y destrucción de claves criptográficas.',
    '¿La gestión de claves criptográficas está definida y documentada en procedimientos?',
    'A8.24'
),
(
    'Revisa si los algoritmos y protocolos criptográficos utilizados se evalúan periódicamente para asegurar que siguen siendo seguros y actualizados.',
    '¿Se revisa periódicamente la eficacia de los algoritmos y protocolos criptográficos utilizados?',
    'A8.24'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica si la organización ha establecido políticas y procedimientos formales para integrar prácticas de seguridad durante todo el ciclo de vida del desarrollo de software.',
    '¿Se han definido políticas y procedimientos para integrar la seguridad en el ciclo de vida de desarrollo de software?',
    'A8.25'
),
(
    'Comprueba si la seguridad se considera en todas las fases del desarrollo, incluyendo análisis, diseño, programación, pruebas y despliegue.',
    '¿Se incluyen revisiones de seguridad en cada fase del ciclo de desarrollo (diseño, pruebas, despliegue)?',
    'A8.25'
),
(
    'Evalúa si los desarrolladores reciben formación continua sobre prácticas seguras de codificación, diseño seguro y prevención de vulnerabilidades.',
    '¿Los desarrolladores reciben formación sobre prácticas de codificación y diseño seguro?',
    'A8.25'
),
(
    'Revisa si los proyectos de desarrollo se supervisan y auditán periódicamente para asegurar que se aplican de forma constante las prácticas de desarrollo seguro.',
    '¿Se revisan periódicamente los proyectos de desarrollo para verificar la aplicación de prácticas seguras?',
    'A8.25'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Comprueba si la organización define y documenta los requisitos de seguridad antes de desarrollar, adquirir o modificar aplicaciones.',
    '¿Se definen y documentan los requisitos de seguridad antes de desarrollar o adquirir aplicaciones?',
    'A8.26'
),
(
    'Verifica si los requisitos de seguridad se revisan, validan y alinean con los requisitos funcionales para garantizar su integración desde el inicio.',
    '¿Los requisitos de seguridad se revisan y validan junto con los requisitos funcionales?',
    'A8.26'
),
(
    'Evalúa si se realizan pruebas específicas para confirmar que las aplicaciones cumplen los requisitos de seguridad definidos.',
    '¿Se aplican pruebas específicas para verificar el cumplimiento de los requisitos de seguridad?',
    'A8.26'
),
(
    'Garantiza que los requisitos de seguridad se actualizan cuando cambian los riesgos, amenazas, requisitos legales o la normativa aplicable.',
    '¿Se actualizan los requisitos de seguridad de las aplicaciones cuando cambian los riesgos o la normativa?',
    'A8.26'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica que la arquitectura de los sistemas integra principios de seguridad desde la fase inicial de diseño.',
    '¿La arquitectura de los sistemas incorpora principios de seguridad desde la fase de diseño?',
    'A8.27'
),
(
    'Comprueba que se aplican modelos de referencia, buenas prácticas o marcos reconocidos de arquitectura segura como defensa en profundidad.',
    '¿Se aplican modelos de referencia o buenas prácticas de arquitectura segura (ej. defensa en profundidad)?',
    'A8.27'
),
(
    'Analiza si las decisiones de diseño que afectan a la seguridad están formalmente documentadas y aprobadas.',
    '¿Se documentan y aprueban formalmente las decisiones de diseño que afectan a la seguridad?',
    'A8.27'
),
(
    'Evalúa si la arquitectura se revisa periódicamente para adaptarse a nuevas amenazas, tecnologías o requisitos.',
    '¿Se revisa periódicamente la arquitectura de los sistemas para adaptarla a nuevas amenazas?',
    'A8.27'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Comprueba si la organización define y aplica guías o estándares formales de codificación segura para los desarrolladores.',
    '¿Los desarrolladores siguen guías o estándares de codificación segura definidos por la organización?',
    'A8.28'
),
(
    'Evalúa si se utilizan herramientas automatizadas, como análisis estático o SAST, para detectar vulnerabilidades durante el desarrollo.',
    '¿Se utilizan herramientas automatizadas (ej. análisis estático) para detectar vulnerabilidades en el código?',
    'A8.28'
),
(
    'Verifica si el código es revisado por otros desarrolladores mediante revisiones de pares o procesos similares para asegurar su calidad y seguridad.',
    '¿El código desarrollado es revisado por pares para garantizar su seguridad?',
    'A8.28'
),
(
    'Confirma que las prácticas de codificación segura se actualizan regularmente en función de nuevas amenazas, vulnerabilidades y buenas prácticas.',
    '¿Se actualizan las prácticas de codificación segura en función de nuevas vulnerabilidades o amenazas?',
    'A8.28'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica si la organización realiza pruebas de seguridad antes de poner en producción aplicaciones o sistemas nuevos o modificados.',
    '¿Se realizan pruebas de seguridad antes de poner en producción sistemas o aplicaciones?',
    'A8.29'
),
(
    'Comprueba que los criterios de seguridad están incluidos dentro de las pruebas de aceptación y no solo los criterios funcionales.',
    '¿Las pruebas de aceptación incluyen criterios de seguridad además de los funcionales?',
    'A8.29'
),
(
    'Evalúa si se utilizan tanto pruebas automatizadas como manuales, incluyendo análisis dinámico y pruebas de penetración, para validar la seguridad.',
    '¿Se utilizan pruebas automatizadas y manuales (ej. análisis dinámico, pentesting) para validar la seguridad?',
    'A8.29'
),
(
    'Confirma que las vulnerabilidades descubiertas durante las pruebas se documentan, priorizan y corrigen antes de desplegar el sistema en producción.',
    '¿Se documentan y corrigen las vulnerabilidades detectadas durante las pruebas antes del despliegue?',
    'A8.29'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Comprueba si los contratos con proveedores de desarrollo incluyen requisitos formales de seguridad de la información.',
    '¿Los contratos con proveedores de desarrollo incluyen requisitos de seguridad de la información?',
    'A8.30'
),
(
    'Evalúa si el trabajo realizado por proveedores externos es revisado regularmente para garantizar el cumplimiento de las políticas de seguridad de la organización.',
    '¿Se revisa el trabajo de desarrollo externalizado para asegurar el cumplimiento de las políticas de seguridad?',
    'A8.30'
),
(
    'Verifica si los accesos de proveedores a código, sistemas o datos están controlados, supervisados y restringidos conforme a principios de seguridad.',
    '¿Se supervisa el acceso de los proveedores al código, sistemas y datos durante el desarrollo?',
    'A8.30'
),
(
    'Analiza si la organización evalúa previamente los riesgos de seguridad antes de externalizar desarrollos críticos a terceros.',
    '¿Se evalúan los riesgos de seguridad antes de delegar desarrollos críticos a terceros?',
    'A8.30'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica que los entornos de desarrollo, pruebas y producción están separados física o lógicamente para reducir riesgos.',
    '¿Los entornos de desarrollo, pruebas y producción están separados físicamente o lógicamente?',
    'A8.31'
),
(
    'Confirma que el acceso a cada entorno está limitado únicamente al personal estrictamente autorizado según su función.',
    '¿El acceso a cada entorno está restringido únicamente al personal autorizado?',
    'A8.31'
),
(
    'Evalúa si los datos sensibles no se utilizan en entornos no productivos sin medidas de protección como anonimización o enmascaramiento.',
    '¿Los datos sensibles no se utilizan en entornos de desarrollo o pruebas sin medidas de protección adecuadas?',
    'A8.31'
),
(
    'Comprueba que la organización revisa periódicamente la separación entre entornos para evitar accesos indebidos o fugas de información.',
    '¿Se revisa periódicamente la separación de entornos para evitar accesos indebidos o filtraciones de información?',
    'A8.31'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Confirma que la organización cuenta con un procedimiento formal y documentado para gestionar cambios en sistemas y aplicaciones.',
    '¿Existe un procedimiento formal para la gestión de cambios en sistemas y aplicaciones?',
    'A8.32'
),
(
    'Verifica que todos los cambios son registrados, evaluados y aprobados antes de ser implementados para evitar riesgos no controlados.',
    '¿Todos los cambios se registran, evalúan y aprueban antes de su implementación?',
    'A8.32'
),
(
    'Evalúa si se realizan pruebas de seguridad cuando un cambio puede afectar a sistemas críticos o introducir nuevas vulnerabilidades.',
    '¿Se realizan pruebas de seguridad antes de aplicar cambios significativos en producción?',
    'A8.32'
),
(
    'Comprueba que los registros de cambios se revisan periódicamente para asegurar que se han aplicado correctamente y que no existen desviaciones.',
    '¿Se revisan periódicamente los registros de cambios para verificar su correcta aplicación?',
    'A8.32'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Verifica que la información usada en los entornos de prueba cuenta con medidas de protección contra accesos no autorizados.',
    '¿La información utilizada en pruebas está protegida frente a accesos no autorizados?',
    'A8.33'
),
(
    'Comprueba que no se usan datos reales sensibles durante las pruebas, a menos que sea imprescindible y existan medidas adecuadas de protección.',
    '¿Se evita el uso de datos reales sensibles en pruebas, salvo cuando sea estrictamente necesario y con medidas de protección?',
    'A8.33'
),
(
    'Evalúa si el uso de información en los procesos de prueba está documentado y controlado para garantizar trazabilidad y seguridad.',
    '¿Se documenta y controla el uso de la información durante las actividades de prueba?',
    'A8.33'
),
(
    'Confirma que la organización elimina de forma segura los datos de prueba al finalizar las pruebas para evitar fugas o accesos indebidos.',
    '¿Se eliminan de forma segura los datos de prueba una vez finalizadas las actividades correspondientes?',
    'A8.33'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Confirma que la organización dispone de procedimientos formales para proteger los sistemas de información cuando se realizan auditorías o evaluaciones técnicas.',
    '¿Existen procedimientos para proteger los sistemas de información durante la realización de pruebas de auditoría o evaluación técnica?',
    'A8.34'
),
(
    'Verifica que las pruebas de auditoría son autorizadas previamente por la dirección y realizadas exclusivamente por personal cualificado.',
    '¿Las pruebas de auditoría son autorizadas previamente por la dirección y realizadas por personal cualificado?',
    'A8.34'
),
(
    'Evalúa si los sistemas son monitorizados durante las auditorías para identificar posibles impactos, interrupciones o riesgos asociados a las pruebas.',
    '¿Se monitorizan los sistemas durante las pruebas para detectar posibles impactos en la operación normal?',
    'A8.34'
),
(
    'Comprueba que los resultados de las pruebas son documentados y revisados, y que se aplican medidas correctivas cuando se detectan problemas.',
    '¿Se documentan y revisan los resultados de las pruebas, aplicando medidas correctivas si es necesario?',
    'A8.34'
);
