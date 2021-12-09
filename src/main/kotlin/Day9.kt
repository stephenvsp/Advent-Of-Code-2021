import java.io.File
import java.lang.Exception

class Day9 : Day {

    private fun readFile(): List<String> {

        return File("src/main/resources/input9.txt").readLines()


    }

    override fun partOne(): Int {

        val lavaTubes = readFile()

        var riskLevel = 0

        lavaTubes.forEachIndexed { y, row ->
            row.forEachIndexed { x, column ->
                var count = 0
                if (checkNeighbor(x, y, x - 1, y, lavaTubes)) count++
                if (checkNeighbor(x, y, x + 1, y, lavaTubes)) count++
                if (checkNeighbor(x, y, x, y - 1, lavaTubes)) count++
                if (checkNeighbor(x, y, x, y + 1, lavaTubes)) count++

                if (count == 4) {
                    riskLevel += (lavaTubes[y][x] + 1).toString().toInt()
                }

            }
        }

        println("Day 9 Part 1: $riskLevel")

        return riskLevel
    }

    override fun partTwo(): Int {
        val lavaTubes = readFile()

        val map = mutableSetOf<Pair<Int, Int>>()

        val basins = mutableListOf<Int>()

        lavaTubes.forEachIndexed { y, row ->
            row.forEachIndexed { x, column ->

                val currentSquare = Pair(x, y)

                if (!map.contains(currentSquare) && lavaTubes[y][x] != '9') {
                    val basinSize = scoreNeighbors(x, y, lavaTubes, map)

                    basins.add(basinSize)
                }
            }
        }

        val largestThree = basins.sorted().takeLast(3)
        val ans = largestThree[0] * largestThree[1] * largestThree[2]

        println("Day 9 Part 2: $ans")
        return ans
    }

    private fun scoreNeighbors(x: Int, y: Int, lavaTubes: List<String>, set: MutableSet<Pair<Int, Int>>): Int {
        try {
            if (lavaTubes[y][x] == '9' || set.contains(Pair(x, y))) {
                return 0
            }
        }
        catch (exception: Exception) {
            return 0
        }

        set.add(Pair(x, y))
        var score = 1

        score += scoreNeighbors(x - 1, y, lavaTubes, set)
        score += scoreNeighbors(x + 1, y, lavaTubes, set)
        score += scoreNeighbors(x, y - 1, lavaTubes, set)
        score += scoreNeighbors(x, y + 1, lavaTubes, set)

        return score
    }

    private fun checkNeighbor(x: Int, y: Int, neighborX: Int, neighborY: Int, lavaTubes: List<String>): Boolean {
        try {
            if (lavaTubes[neighborY][neighborX] > lavaTubes[y][x]) return true
        }
        catch (e: Exception) {
            return true
        }
        return false

    }
}