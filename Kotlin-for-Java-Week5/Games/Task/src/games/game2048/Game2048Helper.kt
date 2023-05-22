package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> = run {
    val notNullCells = this.filterNotNull().toMutableList()
    with(notNullCells) {
        var counter = 1
        while (counter < this.size) {
            if (this[counter] == this[counter - 1]) {
                this[counter - 1] = merge(this[counter])
                this.removeAt(counter)
            }
            counter++
        }
    }
    return@run notNullCells
}

fun <T : Any> List<T?>.moveAndMergeEqual1(merge: (T) -> T): List<T> = run {
        val notNullCells = filterNotNull()
        val tfList: MutableList<T> = mutableListOf()
        notNullCells.forEach{ t ->
             run  {
                     if(!tfList.contains(t)) {
                             val current = notNullCells.filter { it == t }
                             var tf: T
                             if (current.size > 1)
                                current.map(merge)
                             /*current.indices.forEach { i ->
                                     run {
                                             tf = if (i > 0 && i % 2 == 0) merge(t) else t
                                             tfList.add(tf)
                                             if (i > 0 && i % 2 == 0){

                                             }
                                     }
                             }*/
                     }
                }
        }
        return tfList
}