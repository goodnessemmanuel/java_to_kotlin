package testkotlin.src.main
open class Parent
class Child: Parent()
fun Parent.foo() = "parent"
fun Child.foo() = "child"
fun Child.bar() = "child bar"

fun foo(): String{
    println("Calculating...")
    return "foo"
}
fun displaySeparator(character: Char = '*', size: Int){
    repeat(size, fun (a: Int){
        print(character)
    })
}

/**
 * printCharacterCodeInString - C
 * Kotlin JVM increments a character by 1 and print it as a character
 * not the ascii code.
 */
fun incrementAndPrintCharacterInString(){
    for (c in "abc"){
        // when you add a number to a character, it returns the character with the increased code
        print(c + 1)
    }
}
fun streamInt(){
    for (c in '0'..'9')
        print(c)
}

fun streamInt2(){
    for (c in '0' until  '9')
        print(c)
}

fun streamInt3(){
    print(0 to  9)
}

fun String.get(index: Int) = "*"

fun main(args: Array<String>) {
   /* println("Hello, ${args.getOrNull(0)}!")
    println("First ${foo()}, Second ${foo()}")
    val person = Person("john", 19)
    println("printing john's details....")
    println("${person.name}'s age is ${person.age} ${person.compute()}")
    person.compute()
    val names = mutableListOf("James", "Joans")
    names.add("Johnson")
    val names2 = listOf(9, 10, 11) //this an immutable list
    println("static call ${fooPerson()}, member call ${person.fooPerson()}")
    val list = listOf('a', 'b', 'c')
    println(list.joinToString(separator = "", prefix = "(", postfix = ")"))
    //displaySeparator('3', 5)
    displaySeparator(size = 3, character = '5')
    println("printing character code eqv in string...")
    incrementAndPrintCharacterInString()*/
    /*println()
    streamInt()
    println()
    streamInt2()
    println()
    streamInt3()
    println("Using in to check range")
    println("Kotlin" in "Java".."Scala")//true
    println("Kotlin" in setOf("Java", "Scala"))//false
    */

    println("""To code,
        |or not to code with trim margin?..""".trimMargin()
    )
    //there is the same indent for all the margin in my string
    //hence trim indent works fine here
    println("""
        To code
        or not to code?..""".trimIndent()

    )
    val listTest = listOf(1, 3, 4, 9)
    println(listTest.sum())

    //testing class inheritance hirarchy
    val parent: Parent = Child()

    println("Calling parent type instantiated with child => ${parent.foo()}")
    val parent1 = Child()
    println("Calling parent type instantiated with parent => ${parent1.foo()}")

    println("abc".get(1))


    val testStringNullable: String? = null

   val len = testStringNullable?.length

}