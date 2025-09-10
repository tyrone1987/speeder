
package io.github.tyrone1987.data.model;

public enum ValidatedType
{
    NONE,
    ASSERT_FALSE,
    ASSERT_TRUE,
    //DECIMAL_MAX, necesitan argumentos
    //DECIMAL_MIN, necesitan argumentos
    //DIGITS, necesitan argumentos
    FUTURE,
    //MAX, necesitan argumentos
    //MIN, necesitan argumentos
    NONULL,
    NULL,
    PAST,
    //SIZE, necesitan argumentos
    //A continuacion los tipos personalizados con PATTERN
    EMAIL;
    //PHONENUMBER;
}
