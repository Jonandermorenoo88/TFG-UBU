package es.ubu.gii.ISOAssetManager.util;

import java.util.regex.Pattern;

/**
 * Clase de utilidad para validar el formato de contraseñas
 * según una política de complejidad avanzada:
 * <ul>
 *     <li>Longitud entre 8 y 32 caracteres</li>
 *     <li>Debe cumplir al menos 3 de estos 4 criterios:
 *         <ul>
 *             <li>Mayúsculas</li>
 *             <li>Minúsculas</li>
 *             <li>Números</li>
 *             <li>Caracteres especiales</li>
 *         </ul>
 *     </li>
 *     <li>No permite más de 2 caracteres consecutivos iguales</li>
 * </ul>
 */
public final class ValidarFormatoPassword {

    private ValidarFormatoPassword() {
        // Evitar instanciación
    }

    private static final String COMPLEX_FORMAT_REGEX =
            "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))" +
            "(?!.*(.)\\1{2,})" +
            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]{8,32}$";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_FORMAT_REGEX);

    /**
     * Valida si una contraseña cumple la política de complejidad.
     *
     * @param password contraseña a validar
     * @return true si cumple el formato, false si no
     */
    public static boolean esPasswordValida(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}