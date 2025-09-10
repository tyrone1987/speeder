
package io.github.tyrone1987.integration;

import java.util.regex.Pattern;

public class EntityBuilderValidated 
{
    public static boolean validateEmail(String email)
    {
        String emailPattern = 
                   "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();
    }    
}
