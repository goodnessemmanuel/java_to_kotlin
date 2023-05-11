package coursera

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import testkotlin.src.main.coursera.isValidIdentifier
import kotlin.test.assertEquals

class CheckRangeTest {

    @Test
    fun shouldValidateIdentifierCorrectly(){
        assertAll({
                assertEquals(true, isValidIdentifier("name"), "name is a valid")
                assertEquals(true, isValidIdentifier("_name"), "_name is valid")
                assertEquals(true, isValidIdentifier("_12"), "_12 is valid")
                assertEquals(false, isValidIdentifier(""), "empty string is a invalid")
                assertEquals(false, isValidIdentifier("012"), "012 is invalid")
                assertEquals(false, isValidIdentifier("no$"), "no$ is invalid")
            }
        )
    }
}