package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

/**
 * author: Oche
 * evaluateGuess - mastermind game implementation according to
 * requirement in readme
 * @return the number of right and wrong guess made
 */
fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPos = 0
    var wrongPos = 0
    val dupSecret = StringBuilder(secret)
    val checkedChars = mutableListOf<Char>()
    for ((i, ch) in guess.withIndex()) {
        val pos = dupSecret.indexOf(ch, i)
        if(pos != -1){
            if (pos == i) {
                rightPos++
                dupSecret.replace(pos, pos + 1, "?")
            } else {
                val charCountInSecret =  getTotalCharFreqInString(ch, secret);
                if(!checkedChars.contains(ch) || charCountInSecret == getTotalCharFreqInString(ch, guess)) {
                    val similarChar = isCharSimilar(ch, i, guess, dupSecret.toString())
                    wrongPos = if (!similarChar) ++wrongPos else wrongPos + charCountInSecret - 1
                }
            }
        } else {
            if (dupSecret.contains(ch)) {
                val targetIndex = dupSecret.indexOf(ch)
                dupSecret.replace(targetIndex, targetIndex + 1, "?")
                wrongPos++
            }
        }
        checkedChars.add(ch)
    }

    return Evaluation(rightPos, wrongPos)
}
fun isCharSimilar( ch: Char, charIndex: Int, guess: String, dupSecret: String) : Boolean{
    for ((m, el) in guess.withIndex())
        if (el == ch && dupSecret[m] == ch && m != charIndex) return true
    return false
}
fun getTotalCharFreqInString(char: Char, string: String) :Int{
    var count = 0
    for (c in string)
        count = if (c == char) count + 1 else count;
    return  count
}