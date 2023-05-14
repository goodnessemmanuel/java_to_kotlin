package testkotlin.src.main.coursera

class Name (val Value: String)
fun workingWithNull(){
    val a: Int? = null //=> a = null
    val b: Int? = 1 //=> b = 1
    val c: Int = 2

    val s1 = (a ?: 0) + c
    val s2 = (b ?: 0) + c
    print("$s1$s2") //23

}
fun isFoo1(n: Name) = n.Value == "foo" //this function will not accept a null as input
//fun isFoo2(n: Name?) = n.Value == "foo1" //you must use safe operator to access value
fun isFoo3(n: Name?) = n != null && n.Value == "foo3"
fun isFoo4(n: Name?) = n?.Value == "foo4"

fun main(){
//    println(isFoo1(null))
//    println(isFoo2(null))
//    println(isFoo3(null))
//    println(isFoo4(null))
//
//    val x: Int? = 1
//    val y :Int = 2
//    val sum = x?: y + 0
//    println(sum)
//
//    val s1: String? = null
//    val s2: String? = ""
//    s1.isEmptyOrNull() eq true
//    s2.isEmptyOrNull() eq true
//
//    val s3 = "   "
//    s3.isEmptyOrNull() eq false
}

//fun foo(list1: List<Int?>, list2: List<Int>?) {
//      list1.size
//     list2.size
//
//      val i: Int =
//        list1.get(0)
//       val j: Int =
//        list2.get(0)
//}

fun String?.isEmptyOrNull() :Boolean? {
    if(this == null || this.isEmpty())
        return true
    return false
}

fun String?.isEmptyOrNullTest() :Boolean {
    if(this == null || this.isEmpty())
        return true
    return false
}
fun typeCastingInKotlin() {
    val s =  "9"
    println(s as? Int)    // null
    println(s as Int?)    // exception
}
