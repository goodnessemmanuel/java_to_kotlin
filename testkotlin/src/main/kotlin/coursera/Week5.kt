package testkotlin.src.main.coursera

data class Person(val name:String, val age: Int)
fun main(){
   val item = mySequence()
               .map { it * it }
               .filter { it > 10 }
               .take(1)

    println(item)

    val people = listOf( Person("John", 15),
        Person( "Joseph", 19),
        Person("Prince", 22))

   val sortedP1 = people.sortedBy { it.age }.reversed()
    println("sorted reversed: $sortedP1") // [Person(name=Prince, age=22), Person(name=Joseph, age=19), Person(name=John, age=15)]
    val sortedP2 = people.sortedByDescending { it.age }
    println("sorted descending: $sortedP2") // [Person(name=Prince, age=22), Person(name=Joseph, age=19), Person(name=John, age=15)]

    val mapPerson = mutableMapOf<Int, Person>()
    //mapPerson.
}
/**
 * demonstrating sequence
 */
fun mySequence() = sequence {
    println("yield one element")
    yield(1)
    println("yield a range")
    yieldAll(3..5)
    println("yield a list")
    yieldAll(listOf(7, 9))
}