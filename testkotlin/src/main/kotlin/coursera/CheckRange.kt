package testkotlin.src.main.coursera

import java.lang.StringBuilder

/**
 * isValidIdentifier - checks whether a string is a valid identifier.
 * A valid identifier is a non-empty string that starts with a letter
 * or underscore and consists of only letters, digits and underscores
 */
fun isValidIdentifier(s: String): Boolean {
    val result = s.getOrNull(0) ?: return false;
    when{
        result != '_' && result !in 'a'..'z' && result !in 'A'..'Z' -> return false
    }
    val suffix = s.substring(1)
    for (c:Char in suffix){
        if (c != '_' && c !in 'a'..'z' && c !in 'A'..'Z' && c !in '0'..'9')
            return false
    }
    return true
}

fun isValidIdentifierV2(s: String): Boolean {
    val result = s.getOrNull(0) ?: return false;
    fun isValidCharacter(c:Char):Boolean =  c.isLetterOrDigit() || c == '_'
    if(s.isEmpty() || s[0].isDigit()) return false
    for (ch in s)
        if(!isValidCharacter(ch)) return false
    return true
}

/**
 * repeat - repeats the given number of string
 * by the specified number of times
 */
fun String.repeat(n: Int): String{
    var result = StringBuilder(n * length)
    for (i in 0 until n) result.append(this);
    return result.toString();
}

/**
 * it is not possible to call a private member of a String
 * inside an extension function to String
 * Demo - call static method (i.e get, length e.t.c) of an extension String
 */
fun String.lastChar():Char{
    return get(this.length - 1)
}

fun String.lastChar2() = get(this.length - 1)