
package io.github.tyrone1987.integration;

import java.util.regex.Pattern;

/**
 * Clase para validar correos electricos 
 * @author tylopez
 */
public class EntityBuilderValidated 
{
    /**
     * 
     * @param email validador de correo electronico
     * @return si el correo es valido
     */
    public static boolean validateEmail(String email)
    {
        String emailPattern = 
                   "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();
    }    
}
