import java.io.File
import java.lang.IndexOutOfBoundsException

class Day11 : Day {

    private val flashed = mutableSetOf<Pair<Int, Int>>()
    private val octopuses = readFile()


    fun readFile(): MutableList<MutableList<Int>> = File("src/main/resources/input11.txt").readLines().map { line ->
            line.map { char ->
                char.toString().toInt()
            }.toMutableList()
    }.toMutableList()


    override fun partOne(): Int {

        val steps = 100
        var flashedCount = 0

        repeat(steps) {

            flashed.clear()

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    octopuses[y][x] += 1
                    flash(x, y)
                }
            }

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    if (value > 9) octopuses[y][x] = 0
                }
            }

            flashedCount += flashed.size
        }

        println("Day 11 Part 1: $flashedCount")

        return flashedCount
    }

    override fun partTwo(): Int {
        var steps = 0

        do {

            flashed.clear()

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    octopuses[y][x] += 1
                    flash(x, y)
                }
            }

            octopuses.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    if (value > 9) octopuses[y][x] = 0
                }
            }

            steps++

        } while (flashed.size != 100)

        println("Day 11 Part 2: $steps")

        return steps
    }

    private fun flash(x: Int, y: Int): Int {
        val currentOctopus = octopuses[y][x]
        if (!flashed.contains(x to y) && currentOctopus > 9) {
            flashed.add((x to y))

            val offsets = listOf((0 to 1), (1 to 1), (1 to 0), (1 to -1), (0 to -1), (-1 to -1), (-1 to 0), (-1 to 1))

            offsets.forEach {
                val newX = x + it.first
                val newY = y + it.second

                try {
                    octopuses[newY][newX] += 1
                    flash(newX, newY)
                }
                catch (exception: IndexOutOfBoundsException) {
                    //nothing xD
                }
            }
        }
        return currentOctopus
    }
}