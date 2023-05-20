package nicestring

/**
 * isNice - satisfies the conditions stated in the readme
 */
fun String.isNice(): Boolean {
    val listVowels = listOf('a', 'e', 'i', 'o', 'u')
    val vowelCount: Int = this.map { it }.count{ listVowels.contains(it) }

    val containProhibitedString: (String) -> Boolean = { it ->
        it.contains("bu") || it.contains("ba") || it.contains("be")
    }

    val isContainSuccessiveChar: (Char, Int, String) -> Boolean = { c, i, str ->  c == str[i] }

    fun isSuccessive(str: String) :Boolean{
        for ((i, ch) in str.withIndex()){
            if (i + 1 < str.length && isContainSuccessiveChar(ch, i + 1, str))
                return true
        }
        return false
    }

    return  (vowelCount >= 3 && !containProhibitedString(this)) ||
            (vowelCount >= 3 && isSuccessive(this)) ||
            (!containProhibitedString(this) && isSuccessive(this))
}