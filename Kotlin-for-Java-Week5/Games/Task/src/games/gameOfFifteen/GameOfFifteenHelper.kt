package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    fun isInverse(permutation: List<Int>, i: Int, j: Int) :Boolean { return i < j && permutation[i] > permutation[j] }

    var inverseCounter = 0
    for (i in permutation.indices) {
        for (j in i + 1 until permutation.size) {
            if (isInverse(permutation, i, j)) {
                inverseCounter++
            }
        }
    }
    return inverseCounter % 2 == 0
}