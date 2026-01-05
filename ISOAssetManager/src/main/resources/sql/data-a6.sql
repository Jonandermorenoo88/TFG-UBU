USE isoassetmanager;

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización realiza verificaciones de antecedentes del personal antes de formalizar su contratación, cumpliendo requisitos legales y de seguridad.',
    '¿Se realizan verificaciones de antecedentes del personal antes de su contratación, de acuerdo con requisitos legales?',
    'A6.1'
),
(
    'Comprueba si el proceso de selección evalúa la idoneidad del candidato en relación con los requisitos de seguridad de la información del puesto.',
    '¿El proceso de selección incluye la evaluación de la idoneidad del candidato en relación con la seguridad de la información?',
    'A6.1'
),
(
    'Verifica si los resultados de las verificaciones de antecedentes se registran, almacenan y protegen adecuadamente para asegurar confidencialidad e integridad.',
    '¿Los resultados de las verificaciones de antecedentes se registran y se protegen adecuadamente?',
    'A6.1'
),
(
    'Determina si los procedimientos de selección del personal se revisan y actualizan cuando cambian los riesgos relevantes o los requisitos legales aplicables.',
    '¿Se revisan y actualizan los procedimientos de selección del personal cuando cambian los riesgos o requisitos legales?',
    'A6.1'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si los contratos laborales incluyen cláusulas formales que establecen obligaciones de seguridad de la información por parte del empleado.',
    '¿Los contratos de empleo incluyen obligaciones relacionadas con la seguridad de la información?',
    'A6.2'
),
(
    'Comprueba si el personal recibe, entiende y firma términos y condiciones que cubren la confidencialidad, el uso adecuado de la información y otros requerimientos de seguridad.',
    '¿El personal recibe y firma términos y condiciones que abordan la confidencialidad y el uso de la información?',
    'A6.2'
),
(
    'Verifica si los términos de empleo establecen sanciones o medidas disciplinarias aplicables en caso de incumplimientos relacionados con la seguridad de la información.',
    '¿Los términos de empleo contemplan sanciones por incumplimientos de seguridad de la información?',
    'A6.2'
),
(
    'Determina si los términos y condiciones de empleo se revisan regularmente para asegurar que se mantienen actualizados y alineados con los riesgos y requisitos legales.',
    '¿Se revisan periódicamente los términos y condiciones de empleo para asegurar su vigencia y adecuación?',
    'A6.2'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el personal recibe formación inicial en seguridad de la información cuando se incorpora, garantizando que conoce las normas básicas desde el primer día.',
    '¿El personal recibe formación inicial en seguridad de la información al incorporarse a la organización?',
    'A6.3'
),
(
    'Comprueba si la organización imparte programas regulares de concienciación y formación en seguridad para mantener actualizado al personal frente a nuevas amenazas y políticas.',
    '¿Se imparten programas de concienciación y formación en seguridad de forma periódica?',
    'A6.3'
),
(
    'Verifica si el personal es debidamente informado sobre sus responsabilidades en materia de seguridad de la información, incluyendo obligaciones legales y normativas.',
    '¿El personal es informado de sus responsabilidades en materia de seguridad de la información?',
    'A6.3'
),
(
    'Determina si la organización evalúa la eficacia de la formación mediante pruebas, métricas o indicadores que permitan identificar mejoras necesarias.',
    '¿Se evalúa la eficacia de la formación mediante pruebas o indicadores de desempeño?',
    'A6.3'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización cuenta con un proceso disciplinario formal específicamente definido para gestionar incumplimientos relacionados con la seguridad de la información.',
    '¿Existe un proceso disciplinario formal para tratar incumplimientos relacionados con la seguridad de la información?',
    'A6.4'
),
(
    'Comprueba si el personal conoce claramente las consecuencias de incumplir las políticas y normas de seguridad de la información.',
    '¿El personal conoce las consecuencias de incumplir las políticas de seguridad?',
    'A6.4'
),
(
    'Verifica si el proceso disciplinario se aplica de forma justa, coherente, documentada y conforme a normativa laboral.',
    '¿El proceso disciplinario es aplicado de forma justa, documentada y coherente?',
    'A6.4'
),
(
    'Determina si la organización revisa periódicamente la eficacia del proceso disciplinario, especialmente a raíz de incidentes o comportamientos inseguros.',
    '¿Se revisa periódicamente la efectividad del proceso disciplinario en relación con incidentes de seguridad?',
    'A6.4'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización revoca de forma inmediata, controlada y documentada todos los accesos del personal que deja la organización para evitar accesos no autorizados.',
    '¿Los accesos del personal que deja la organización son revocados de manera inmediata y documentada?',
    'A6.5'
),
(
    'Comprueba si, cuando una persona cambia de puesto, se revisan sus responsabilidades y permisos para asegurar que solo mantiene aquellos necesarios para su nueva función.',
    '¿El personal que cambia de puesto recibe una revisión de sus responsabilidades en seguridad de la información?',
    'A6.5'
),
(
    'Verifica si existen procedimientos formales para garantizar que los empleados devuelven todos los activos e información al finalizar la relación laboral o contractual.',
    '¿Existen procedimientos para garantizar la devolución de activos e información al finalizar la relación laboral?',
    'A6.5'
),
(
    'Determina si la organización supervisa y refuerza el cumplimiento de las obligaciones de confidencialidad que continúan tras la finalización del empleo.',
    '¿Se supervisa el cumplimiento de las obligaciones de confidencialidad tras la finalización del empleo?',
    'A6.5'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización requiere que el personal firme acuerdos de confidencialidad antes de permitir el acceso a información sensible o crítica.',
    '¿El personal firma acuerdos de confidencialidad antes de acceder a información sensible?',
    'A6.6'
),
(
    'Comprueba si los acuerdos de confidencialidad aplican no solo a empleados, sino también a contratistas, proveedores y terceros con acceso a información.',
    '¿Los acuerdos de confidencialidad cubren empleados, contratistas y terceros con acceso a información?',
    'A6.6'
),
(
    'Verifica si los acuerdos incluyen obligaciones de confidencialidad que continúan incluso después de la finalización del contrato o relación laboral.',
    '¿Los acuerdos de confidencialidad incluyen obligaciones que se mantienen tras la terminación del empleo o contrato?',
    'A6.6'
),
(
    'Determina si los acuerdos de confidencialidad se revisan y actualizan de forma periódica para asegurar su cumplimiento con leyes, regulaciones y requisitos contractuales.',
    '¿Se revisan periódicamente los acuerdos de confidencialidad para asegurar que cumplen con requisitos legales y contractuales?',
    'A6.6'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si la organización dispone de políticas y procedimientos específicos que regulen el trabajo remoto, garantizando la protección de la información fuera de las instalaciones.',
    '¿Existen políticas y procedimientos específicos para el trabajo remoto que incluyan seguridad de la información?',
    'A6.7'
),
(
    'Comprueba si el acceso remoto a los sistemas corporativos se realiza mediante conexiones seguras, como VPN, canales cifrados o mecanismos equivalentes.',
    '¿Se utilizan conexiones seguras (VPN, cifrado) para acceder a los sistemas corporativos desde ubicaciones remotas?',
    'A6.7'
),
(
    'Verifica si el personal que trabaja en remoto recibe formación sobre los riesgos asociados y sobre las prácticas seguras que deben aplicar.',
    '¿El personal que trabaja en remoto recibe formación sobre riesgos y buenas prácticas de seguridad?',
    'A6.7'
),
(
    'Determina si los controles aplicados al trabajo remoto son supervisados, evaluados y revisados periódicamente para garantizar su eficacia.',
    '¿Se supervisan y revisan periódicamente los controles aplicados al trabajo remoto?',
    'A6.7'
);

INSERT IGNORE INTO pregunta (explicacion, texto, control_id) VALUES
(
    'Evalúa si el personal conoce y entiende el procedimiento formal establecido por la organización para notificar eventos e incidentes de seguridad de la información.',
    '¿El personal conoce el procedimiento para notificar eventos e incidentes de seguridad de la información?',
    'A6.8'
),
(
    'Comprueba si existe un canal formal, accesible y claramente definido para reportar eventos de seguridad, como correos designados, plataformas internas o herramientas de tickets.',
    '¿Existe un canal formal y accesible para reportar eventos de seguridad (ej. correo, sistema interno)?',
    'A6.8'
),
(
    'Verifica si las notificaciones de eventos de seguridad son registradas, gestionadas y tratadas de manera oportuna por personal autorizado y competente.',
    '¿Las notificaciones de eventos de seguridad son registradas y tratadas oportunamente por personal autorizado?',
    'A6.8'
),
(
    'Determina si la organización realiza campañas formativas o de concienciación destinadas a fomentar que el personal informe sobre eventos o comportamientos sospechosos.',
    '¿Se realizan campañas de concienciación para fomentar la notificación de eventos de seguridad?',
    'A6.8'
);
