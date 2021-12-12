import java.io.File
import java.lang.IndexOutOfBoundsException

class Day11 : Day {

    fun readFile(): MutableList<MutableList<Int>> = File("src/main/resources/input11.txt").readLines().map { line ->
            line.map { char ->
                char.toString().toInt()
            }.toMutableList()
    }.toMutableList()

    private val offsets = listOf((0 to 1), (1 to 1), (1 to 0), (1 to -1), (0 to -1), (-1 to -1), (-1 to 0), (-1 to 1))

    override fun partOne(): Int {
        var octopuses = readFile()

        val steps = 100
        var flashedCount = 0

        repeat(steps) {
            val flashed = mutableSetOf<Pair<Int, Int>>()

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    octopuses[y][x] += 1
                    flash(octopuses, flashed, x, y)
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

    override fun partTwo(): Int {
        var octopuses = readFile()

        val x_max = 10
        val y_max = 10
        var steps = 0

        do {
            val flashed = mutableSetOf<Pair<Int, Int>>()

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    octopuses[y][x] += 1
                    flash(octopuses, flashed, x, y)
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

        return steps
    }

    private fun flash(octopuses: MutableList<MutableList<Int>>, flashed: MutableSet<Pair<Int, Int>>, x: Int, y: Int): Int {
        val currentOctopi = octopuses[y][x]
        if (!flashed.contains(x to y) && currentOctopi > 9) {
            flashed.add((x to y))

            offsets.forEach {
                val newX = x + it.first
                val newY = y + it.second

                try {
                    octopuses[newY][newX] += 1
                    flash(octopuses, flashed, newX, newY)
                }
                catch (exception: IndexOutOfBoundsException) {
                    //nothing xD
                }
            }
        }
        return currentOctopi
    }
}