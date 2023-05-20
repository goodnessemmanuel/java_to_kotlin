package testkotlin.src.main.coursera
import testkotlin.src.main.enums.State
import testkotlin.src.main.enums.State.*

/**
 * demonstrating OOP and properties
 */
fun foo1Copy3() :Int {return 3}
val foo1Copy:(Int) -> Int = { 4 }

/**
 * foo1 - runs the code block once and stores
 * value in a variable called foo1
 */
val foo1:Int = run {
        println("Calculating foo!...")
        42
    }
/**
 * foo2 - runs code afresh
 * every time foo2 is called
 *
 */
val foo2: Int
    get() {
        println("Calculating foo!")
       return 42
    }
class StateLogger {
    private var booleanState: Boolean = false
    var state: State
        get() = if (booleanState) ON else OFF
        set(value: State) {
            booleanState = value == ON
        }
}

val unstableVariable:Int
    get() {
        return (100 * Math.random()).toInt()
    }
fun main(){
    /**
     *
     *output is:
     *Calculating foo!...
     *Calculating foo!
     *Calculating foo!
     *42 42 42 42
     *
     */
    println("$foo1 $foo1 $foo2 $foo2")
    val logState = StateLogger()
    println("------ current log state ------")
    println(logState.state)
    println("------ changing state ------")
    logState.state = ON
    println(logState.state)

    println("------ Unstable variable ------")
    println(unstableVariable)
    println(unstableVariable)
    println(unstableVariable)
    val randomStr = "getmiddlechar"
   println(randomStr.medianChar)
   println(randomStr.medianChar)
   println("Demo lazy....")
//   println(lazyValue)
//   println(lazyValue)
//   println(lazyValue)
    val list1 = listOf(1, 2, 3)
    var list2 = list1 //without casting to a mutable list creates a new list under the hood
    list2 += 4

    println("list 1 is: $list1") //[1,2,3]
    println("list 2 is: $list2")// [1,2,3,4]
}

val String.medianChar:Char?
    get(){
        println("Calculating...")
        return getOrNull(length / 2)
    }

 val lazyValue: String by lazy {
     println("Computed...")
      "Hello from lazy!"
 }

val List<Int>.average: () -> Int
    get() = {
        this.sum() / this.size
    }

    @get:JvmName("averageOfDouble")
 val List<Double>.average: () -> Double
    get() = {
        this.sum() / this.size
    }