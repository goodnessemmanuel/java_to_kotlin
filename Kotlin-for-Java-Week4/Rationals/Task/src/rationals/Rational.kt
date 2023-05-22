package rationals

import java.math.BigInteger
import java.math.BigInteger.*

class Rational (
    n: BigInteger,
    d: BigInteger
) :Comparable<Rational>{
    val denominator: BigInteger
    val numerator: BigInteger

    init {
        require(d != ZERO) { "Invalid denominator $d argument can not be zero" }

        val gcd = n.gcd(d) // greatest common denominator
        val sign = d.signum()
        if (sign < 1) {
            numerator = n.div(gcd) * sign.toBigInteger()
            denominator = d.div(gcd) * sign.toBigInteger()
        } else {
            numerator = n.div(gcd)
            denominator = d.div(gcd)
        }
    }
    operator fun div(anotherRational: Rational): Rational{
        return Rational(this.numerator * anotherRational.denominator,
            this.denominator * anotherRational.numerator)
    }

    operator fun times(anotherRational: Rational): Rational{
        return Rational(this.numerator * anotherRational.numerator,
            this.denominator * anotherRational.denominator)
    }

    operator fun plus(anotherRational: Rational): Rational{
        val timesDen = this.denominator * anotherRational.denominator
        return Rational(this.numerator * anotherRational.denominator + this.denominator * anotherRational.numerator, timesDen )
    }

    operator fun minus(anotherRational: Rational): Rational{
       val timesDen = this.denominator * anotherRational.denominator
        return Rational(this.numerator * anotherRational.denominator - this.denominator * anotherRational.numerator, timesDen )
    }

    operator fun unaryMinus(): Rational{
        return Rational(numerator - denominator, denominator)
    }

    operator fun unaryPlus(): Rational{
        return Rational(numerator + denominator, denominator)
    }

    override fun compareTo(other: Rational): Int {
         return (this.numerator * other.denominator - this.denominator * other.numerator).signum()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (denominator != other.denominator) return false
        if (numerator != other.numerator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = denominator.hashCode()
        result = 31 * result + numerator.hashCode()
        return result
    }

    override fun toString(): String {
        return if (denominator > ONE) "$numerator/$denominator" else "$numerator"
    }


}

fun String.toRational() :Rational{
    if(this.contains("/")) {
        val splitStr = split("/")
        return Rational(splitStr[0].toBigInteger(), splitStr[1].toBigInteger())
    }
    return Rational(this.toBigInteger(), (1).toBigInteger())
}

infix fun Int.divBy(other: Int) :Rational {
    return Rational(this.toBigInteger(), other.toBigInteger())
}

infix fun BigInteger.divBy(other: BigInteger) :Rational{
    return Rational(this, other)
}

infix fun Long.divBy(other: Long) :Rational{
    return Rational(this.toBigInteger(), other.toBigInteger())
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    /*val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)*/

    println(Rational((1).toBigInteger(), (2).toBigInteger()) == Rational((2).toBigInteger(), (4).toBigInteger())) // true

    val shortNames = mapOf(
        Rational((1).toBigInteger(), (2).toBigInteger()) to "one half",
        Rational((1).toBigInteger(), (3).toBigInteger()) to "one third"
    )
    println(shortNames[Rational((2).toBigInteger(), (4).toBigInteger())])
}