import java.io.File
import java.lang.Math.abs

class Day7 {

    private fun readFile(): List<Int> {

        val lines = File("src/main/resources/input7.txt").readLines()[0].split(",")

        return lines.map { it.toInt() }

    }

    fun partOne(): Int {

        val crabs = readFile()

        val frequency = crabs.groupBy { it }


        val centerOfGravity = frequency.keys.map { location ->
            frequency.map {
                abs(location - it.key) * it.value.size
            }.sum()
        }.minOf { it }


        println("Day 7 Part 1: $centerOfGravity")

        return centerOfGravity
    }

    fun partTwo(): Int {

        val crabs = readFile()

        val frequency = crabs.groupBy { it }

        val minFuel = (crabs.minOrNull()!!..crabs.maxOrNull()!!).toList().minOf { location ->

            frequency.map {
                val distance = abs(it.key - location)
                val fuelSum = (distance * (distance + 1)) / 2
                fuelSum * it.value.size
            }.sum()
        }

        println("Day 7 Part 2: $minFuel")

        return minFuel
    }
}