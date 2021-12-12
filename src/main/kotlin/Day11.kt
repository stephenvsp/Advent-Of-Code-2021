import java.io.File
import java.lang.IndexOutOfBoundsException

class Day11 : Day {

    fun readFile(): MutableList<MutableList<Int>> = File("src/main/resources/input11.txt").readLines().map { line ->
            line.map { char ->
                char.toString().toInt()
            }.toMutableList()
    }.toMutableList()

    val offsets = listOf((0 to 1), (1 to 1), (1 to 0), (1 to -1), (0 to -1), (-1 to -1), (-1 to 0), (-1 to 1))

    override fun partOne(): Int {
        var octopuses = readFile()

        val steps = 100
        var flashedCount = 0

        val x_max = 10
        val y_max = 10

        repeat(steps) {
            val flashed = mutableSetOf<Pair<Int, Int>>()

            octopuses = octopuses.map { line ->
                line.map {
                    it + 1
                }.toMutableList()
            }.toMutableList()

            for (y in 0 until y_max) {
                for (x in 0 until x_max) {
                    if (!flashed.contains(x to y) && octopuses[y][x] > 9) {
                        octopuses = flash(octopuses, flashed, x, y)
                    }
                }
            }

            octopuses = octopuses.map { line ->
                line.map {
                    if (it > 9) 0 else it
                }.toMutableList()
            }.toMutableList()

            flashedCount += flashed.size
        }

        println("Day 11 Part 1: $flashedCount")

        return flashedCount
    }

    fun flash(octopuses: MutableList<MutableList<Int>>, flashed: MutableSet<Pair<Int, Int>>, x: Int, y: Int): MutableList<MutableList<Int>> {
        flashed.add((x to y))

        offsets.forEach {
            val newX = x + it.first
            val newY = y + it.second

            try {
                octopuses[newY][newX] += 1

                if (!flashed.contains(newX to newY) && octopuses[newY][newX] > 9) {
                    flash(octopuses, flashed, newX, newY)
                }
            }
            catch (exception: IndexOutOfBoundsException) {
                //nothing xD
            }
        }
        return octopuses
    }


    override fun partTwo(): Int {
        var octopuses = readFile()

        var flashedCount = 0

        val x_max = 10
        val y_max = 10
        var steps = 0

        do {
            val flashed = mutableSetOf<Pair<Int, Int>>()

            octopuses = octopuses.map { line ->
                line.map {
                    it + 1
                }.toMutableList()
            }.toMutableList()

            for (y in 0 until y_max) {
                for (x in 0 until x_max) {
                    if (!flashed.contains(x to y) && octopuses[y][x] > 9) {
                        octopuses = flash(octopuses, flashed, x, y)
                    }
                }
            }

            octopuses = octopuses.map { line ->
                line.map {
                    if (it > 9) 0 else it
                }.toMutableList()
            }.toMutableList()


            steps++

        } while (flashed.size != 100)

        println("Day 11 Part 2: $steps")

        return flashedCount
    }
}