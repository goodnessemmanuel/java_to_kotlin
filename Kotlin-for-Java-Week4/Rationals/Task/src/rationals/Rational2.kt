package rationals

import java.math.BigInteger
import java.math.BigInteger.ZERO

/**
 * Create a rational2 object via call its companion like
 * Rational2.create(param1, param2)
 */
@Suppress("DataClassPrivateConstructor")
data class Rational2 private constructor(val numerator: BigInteger, val denominator: BigInteger) :Comparable<Rational2> {
    companion object {
        fun create(num1: BigInteger, den1: BigInteger) :Rational2 = normalize(num1, den1)
        private fun normalize(num1: BigInteger, den1: BigInteger) :Rational2{
            require(den1 != ZERO) {"Invalid argument denominator cannot be zero"}
            val gcd = num1.gcd(den1)
            val sign = gcd.signum()
            return Rational2(sign.toBigInteger() * num1 / gcd, sign.toBigInteger() * den1 / gcd)
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

    override fun compareTo(other: Rational2): Int {
       return (this.numerator * other.denominator - this.denominator * other.numerator).signum()
    }

}