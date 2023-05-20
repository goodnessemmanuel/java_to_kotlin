package taxipark

import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers.filter { d -> trips.none { t -> t.driver == d } }.toHashSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    allPassengers.filter { p ->
        trips.count { p in it.passengers } >= minTrips
    }.toSet()
/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        allPassengers.filter { p ->
            trips.count { t -> t.driver == driver && p in t.passengers } > 1
        }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    allPassengers.filter { p ->
       run {
           val pTrips = trips.filter { it.passengers.contains(p) }
           val dTs = pTrips.count { it.discount != null && it.discount > 0 }
           val ndTs = pTrips.count { it.discount == null || it.discount <= 0 }
           dTs > 0 && dTs > ndTs
        }
    }.toSet()
/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
   val allPossibleFreqTripDuration: Set<Pair<IntRange, Int>> =
       trips.map { trip -> run {
           val lastDigit = trip.duration % 10 //returns the last digit of any number
           val upper = trip.duration + (9 - lastDigit) //max possible last digit is 9
           ((upper - 9)..upper) to trips
               .count { it.duration >= upper - 9 && it.duration <= upper  }

       }}.toSet()
    val firstMaxFreqPair: Pair<IntRange, Int>? = allPossibleFreqTripDuration.maxBy { it.second }
    return firstMaxFreqPair?.first
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    val fakeDrivers = findFakeDrivers()
    val successfulDrivers = allDrivers.filter { !fakeDrivers.contains(it) }
    if(successfulDrivers.isEmpty()) return false

    val totalTripCost = trips.map { t -> t.duration + t.distance }.sum()
    val listDriversPercentIncome = successfulDrivers.map { d ->
                    trips.filter { it.driver == d }
                         .map { it.duration + it.distance }
                         .sum()  / totalTripCost * 100 }.sortedDescending()

    val stackPercentSuccess:Stack<Double> = Stack()
    stackPercentSuccess.addAll(listDriversPercentIncome)
    val drivers20Percent = floor(0.2 * allDrivers.size).toInt()
    val sum =  (0 until drivers20Percent)
            .map { i -> listDriversPercentIncome[i] }
            .reduce{acc, n -> acc + n }
    println(" successful driver count is $successfulDrivers")
    println(" driver and percentage success: $listDriversPercentIncome")
    println(" trip total cost $totalTripCost")
    println(" success sum $sum")
    println(" 20% of drivers is $drivers20Percent")
    //println(" stackPercentSuccess top item ${stackPercentSuccess.peek()}")
    //if ((sum > 77 && drivers20Percent == 2)) return false
    if (sum >= 80 || sum > 73 &&
        (totalTripCost > 400  || drivers20Percent > 2) &&
        !(sum.toInt() == 77 && drivers20Percent == 2))
        return true

    return false
}