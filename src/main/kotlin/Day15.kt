import java.io.File
import java.lang.IndexOutOfBoundsException
import java.util.*

class Day15 : Day {

    private val input = File("src/main/resources/input15.txt").readLines()
    private val cave = input.map { line ->
        line.map {
            it.digitToInt()
        }
    }

    private val neighborOffsets = setOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

    override fun partOne(): Int {

        val start = Pair(0, 0)
        val goal = Pair(cave.first().lastIndex, cave.lastIndex)

        val ans = aStar(start, goal, cave) { cave[it.second][it.first] }

        println("Day 15 Part 1: $ans")

        return ans
    }

    override fun partTwo(): Int {
        val start = Pair(0, 0)

        val map = cave.expand()
        val goal = Pair(map.first().lastIndex, map.lastIndex)

        val ans = aStar(start, goal, map) {
            map[it.second][it.first]
        }

        println("Day 15 Part 2: $ans")

        return ans
    }

    private fun aStar(
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        map: List<List<Int>>,
        heuristic: (Pair<Int, Int>) -> Int
    ): Int {

        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()

        val scoreToNode = mutableMapOf((start to 0)).withDefault { Int.MAX_VALUE }

        val queue = PriorityQueue(compareBy<Pair<Int, Int>> { scoreToNode.getValue(it) + heuristic(it) })
        queue.add(start)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current == end) {
                return scorePath(cameFrom, start, current, map)
            }

            neighborOffsets.forEach {
                try {
                    val neighbor = Pair(current.first + it.first, current.second + it.second)
                    val danger = heuristic(neighbor)

                    val tentativeNodeScore = scoreToNode.getValue(current) + danger

                    if (tentativeNodeScore < scoreToNode.getValue(neighbor)) {
                        cameFrom[neighbor] = current
                        scoreToNode[neighbor] = tentativeNodeScore
                        if (neighbor !in queue) {
                            queue.add(neighbor)
                        }
                    }
                }
                catch (exception: IndexOutOfBoundsException) {

                }
            }
        }
        return 0
    }

    private fun scorePath(cameFrom: MutableMap<Pair<Int, Int>, Pair<Int, Int>>, start: Pair<Int, Int>, end: Pair<Int, Int>, map: List<List<Int>>): Int {
        var current = end
        var totalScore = 0

        while (current in cameFrom.keys && current != start) {
            totalScore += map[current.second][current.first]
            current = cameFrom[current]!!

        }

        return totalScore
    }

    private fun List<List<Int>>.expand(): List<List<Int>> {
        val expandedRight = map { row -> (1 until 5).fold(row) { acc, step -> acc + row.increasedAndCapped(step) } }
        return (1 until 5).fold(expandedRight) { acc, step -> acc + expandedRight.increased(step) }
    }

    private fun List<Int>.increasedAndCapped(by: Int) = map { level -> (level + by).let { if (it > 9) it - 9 else it } }

    private fun List<List<Int>>.increased(by: Int) = map { row -> row.increasedAndCapped(by) }


}