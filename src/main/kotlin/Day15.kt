import java.io.File
import java.lang.IndexOutOfBoundsException
import java.util.*

class Day15 : Day {

    private val input = File("src/main/resources/input15.txt").readLines()
    private val cave = input.map { line ->
        line.map {
            it.toString().toInt()
        }
    }

    private val offsets = setOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

    override fun partOne(): Int {

        val start = Pair(0, 0)
        val goal = Pair(cave.first().lastIndex, cave.lastIndex)

        val ans = aStar(start, goal) { cave[it.second][it.first] }

        println("Day 15 Part 1: $ans")

        return ans
    }

    override fun partTwo(): Int {
        val start = Pair(0, 0)

        val numCaves = 5
        val caveMax = cave.first().size * numCaves - 1
        val goal = Pair(caveMax, caveMax)

        val ans = aStar(start, goal) { cave[it.second][it.first] }

        return 0
    }

    private fun aStar(
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        heuristic: (Pair<Int, Int>) -> Int
    ): Int {

        val openSet = PriorityQueue(compareBy<Pair<Int, Int>> { cave[it.second][it.first] })
        openSet.add(start)

        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()

        val gScore = mutableMapOf((start to 0))

        //g score + heuristic
        val fScore = mutableMapOf((start to heuristic(start)))

        while (openSet.isNotEmpty()) {
            val current = openSet.minByOrNull { gScore.getOrDefault(it, Int.MAX_VALUE) + heuristic(it) }!!

            if (current == end) {
                return reconstructPath(cameFrom, start, current)
            }

            openSet.remove(current)

            offsets.forEach {
                try {
                    val neighbor = Pair(current.first + it.first, current.second + it.second)
                    val danger = heuristic(neighbor)

                    val tentativeGScore = gScore.getOrDefault(current, Int.MAX_VALUE) + danger

                    if (tentativeGScore < gScore.getOrDefault(neighbor, Int.MAX_VALUE)) {
                        cameFrom[neighbor] = current
                        gScore[neighbor] = tentativeGScore
                        fScore[neighbor] = tentativeGScore + danger
                        if (neighbor !in openSet) {
                            openSet.add(neighbor)
                        }
                    }
                }
                catch (exception: IndexOutOfBoundsException) {

                }
            }
        }
        return 0
    }

    private fun reconstructPath(cameFrom: MutableMap<Pair<Int, Int>, Pair<Int, Int>>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        var current = end
        var totalScore = 0

        while (current in cameFrom.keys && current != start) {
            totalScore += cave[current.second][current.first]
            current = cameFrom[current]!!

        }

        return totalScore
    }

    private fun getCaveLocation(cell: Pair<Int, Int>, numCaves: Int, caveLength: Int): Int {
        if (cell.first / caveLength > numCaves || cell.second / caveLength > numCaves) {
            throw IndexOutOfBoundsException()
        }

        val xLocation = cell.first % 25
        val yLocation = cell.second % 25

        val xCave = cell.first / numCaves
        val yCave = cell.second / numCaves

        var value = cave[xLocation][yLocation] + xCave + yCave

        if (value > 9) {
            value -= 9
        }

        return value
    }

}