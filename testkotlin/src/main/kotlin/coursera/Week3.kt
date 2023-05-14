package testkotlin.src.main.coursera
enum class GENDER{
    MALE,
    FEMALE
}

data class Hero(val name :String, val age :Int, val gender :GENDER?)

/**
 * Functional coding with lamdba
 */
fun main(){
    val heroes = listOf(
        Hero("The Captain", 60, GENDER.MALE),
        Hero("Jude", 30, GENDER.MALE),
        Hero("Viva", 40, GENDER.FEMALE),
        Hero("kid", 10, null),
        Hero("Casmos", 32, GENDER.MALE),
        Hero("Lady Gate", 28, GENDER.FEMALE),
        Hero("Reeves", 28, GENDER.FEMALE))

    val lastHeroInList = heroes.last().name //Lady Gate
   println(lastHeroInList)

    //return the first element that matches the given predicate or null if the element was not found
   val lambdaToGetHeroByAge = heroes.firstOrNull { it.age == 30 }?.name
    println(lambdaToGetHeroByAge)

    //distinct means, when mapping by age, duplicate should be allowed
    val mapHeroesByAge = heroes.map { it.age }.distinct() //output [60, 30, 40, 10, 32, 28]
    println(mapHeroesByAge)
    println(mapHeroesByAge.size)

    val getHeroesOfAgeLessThan30 = heroes.filter{ it.age < 30 }
    println(getHeroesOfAgeLessThan30)
    println(" size: ${getHeroesOfAgeLessThan30.size}")
    println()

    //splits heroes into a pair of list and separate age < 30 as youngest
    val(youngest, oldest) = heroes.partition { it.age < 30 }
    println("Youngest list $youngest, size: ${youngest.size}")
    println("Older list $oldest, size: ${oldest.size}")

    val getHeroNameWithMaxAge = heroes.maxByOrNull{it.age}?.name
    println("Hero with max age is: $getHeroNameWithMaxAge")

    val allPredicate = heroes.all { it.age < 50 } //check if every hero in heroes age are younger than 50, true or false
    println("allPredicate $allPredicate") //false
    println(heroes.any{it.gender == GENDER.FEMALE}) //true

    //demonstrating groupBy, so all heroes with the same age will be together
    val mapGroupHeroesByAge: Map<Int, List<Hero>> = heroes.groupBy { it.age }
    val (age, group) = mapGroupHeroesByAge.maxByOrNull { (_, group) -> group.size }!!
    println("group size: $group")
    println("group age: $age")
    println()
    // demonstrating associatedBy
    val mapAssociateHeroesByName: Map<String, Hero> = heroes.associateBy { it.name }
    println(mapAssociateHeroesByName)
    println(mapAssociateHeroesByName["Jude"])
    println(mapAssociateHeroesByName["Jude"]?.age) //30

    val unknownHero = Hero("Unkown", 0, null)
    val queryUnknownRecordInHeroesList  = mapAssociateHeroesByName.getOrElse("unknown"){ unknownHero }.age
    println("value is unknown so newly created unkown hero returned $queryUnknownRecordInHeroesList")

    println()
    val (first, second) = heroes
        .flatMap { heroes.map { hero -> it to hero   } }
        .maxByOrNull { it.first.age - it.second.age }!!
    print(first.name) //The Captain
    println()
    // lines 68 to 71 is a badly written code. A better way to write the code is as shown:
    val allPossiblePairs = heroes
        .flatMap { first ->
            heroes.map { second -> first to second }
        }
    println("---printing all possible pair variable content---")
    println(allPossiblePairs)

    val (old, young) = allPossiblePairs
        .maxByOrNull { it.first.age - it.second.age }!!
    //println("youngest age: ${young.age}, Oldest age is: ${old.age}")

    // testing interchangeable predicates
   /* println("---Interchangeable predicates---")
    println(listOf(1, 2, 3, -1, 0).allNonZero())
    println(listOf(1, 2, 3, 7, 8, 0).allNonZero1())
    println(listOf(1, 2, 3, 7, 8).allNonZero2())

    println("**Calling contains zero function**")
    println(listOf(1, 2, 3, 0, -1).containsZero())
    println(listOf(20, 23, 70).containsZero1())
    println(listOf(1, 2, 3, 7, 8, 20).containsZero2())*/

    println()
    //lambdas as variable
    val sum: (Int, Int) -> Int = { x, y -> x + y } //takes two ints as param and returns an int

    val isEven : (Int) -> Boolean = { i: Int -> i % 2 == 0 } //takes an int and returns boolean
//   println(isEven(5))
//   println(isEven(8))

    //nullable lambdas
    //val f1: () -> Int? = null // this won't compile bcos, the type does not specify entire lambda will be null
    val f2: () -> Int? = { null }
    val f3: (() -> Int)? = null
    //val f4: (() -> Int)? = { null } //won't compile bcos, return type is of Int, only entire lambda function can be null

    val result = duplicateNonZeroList(listOf(3, 0, 5))
    println(result) // []
    val result1 = duplicateNonZeroList(listOf(3, 5)) //input is a non zero list
    println(result1) // [3, 3, 5, 5]

    val result2 = duplicateNonZeroListBetter(listOf(3, 0, 5)) //even though it has a zero element will still work as expected
    println(result2) // [3, 3, 5, 5]

    investigateLambdaCountFunction()
}

fun List<Int>.allNonZero() =  all { none { it == 0 } }
fun List<Int>.allNonZero1() =  none { it == 0 }
fun List<Int>.allNonZero2() =  !any { it == 0  }

fun List<Int>.containsZero() =  any { it == 0 }
fun List<Int>.containsZero1() =  all { any{ it == 0} }
fun List<Int>.containsZero2() =  !none { it == 0 }

/**
 * working with lambda returns
 */
fun duplicateNonZeroList(list: List<Int>) : List<Int>{
    return list.flatMap {
        it -> if(it == 0) return listOf() //this will return from the entire function not the lambda
        listOf(it, it)
    }
}

/**
 * function now does not return until entire loop
 * is complete and operation captured
 */
fun duplicateNonZeroListBetter(list: List<Int>) : List<Int>{
    return list.flatMap {
        if(it == 0)
            listOf()
        else
        listOf(it, it)
    }
}

/**
 * Controlling return boundary with custom labels. if custom name
 * is not provided, default is name of lambda express being
 * invoked (e.g @flatMap List<Int>() as in this case.
 * function now does not return until entire loop
 * is complete and operation captured
 * note: labelled return corresponds to 'continue' inside forloop.
 * remember forloop 'break' stops the loop completely, while 'continue' stops
 * for the current item/element been processed by the forloop operation
 */
fun duplicateNonZeroListUsingLabel(list: List<Int>) : List<Int>{
    return list.flatMap lCustomName@{
            it -> if(it == 0) return@lCustomName listOf() //this will return from the entire function not the lambda
        listOf(it, it)
    }
}

fun investigateLambdaCountFunction(){
    val testSecret = "ABCD"
    val testGuess = "BFAD"
    val commonLetters = "ABCDEF"

    //note: zip lambda pairs these two secrets according to their index
    val pairSecretAndGuess : List<Pair<Char, Char>> = testSecret.zip(testGuess)
    val rightPosition = testSecret.zip(testGuess).count { (secret, guess) -> secret == guess }
    println("right position/s: $rightPosition") // 1 (i.e. A-B, B-F, C-A, D-D) only D is paired rightly
    println("***Zip pairing output***")
    println(pairSecretAndGuess) //[(A, B), (B, F), (C, A), (D, D)]

    val wrongPosition = pairSecretAndGuess.count { (sect, gues) -> sect != gues }
    println("wrong position/s: $wrongPosition") // 3 (i.e. A-B, B-F, C-A, D-D) 3 are wrongly paired

    val countCommonLetters = commonLetters.sumOf { c ->
        Math.min(testSecret.count { it == c }, testGuess.count { it == c })
    }

    println("Common letters count in $testSecret and $testGuess is: $countCommonLetters")
}