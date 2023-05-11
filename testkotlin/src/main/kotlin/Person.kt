package testkotlin.src.main

fun fooPerson(): String{
    return "Calling a top level function in file name Person"
}
class Person(val name: String, val age: Int){
    fun compute(){
        println("Calling person compute method...")
    }
    fun fooPerson(): String{
        return "Calling a member function of class person"
    }
}