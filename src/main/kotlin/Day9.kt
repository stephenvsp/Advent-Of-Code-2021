import java.io.File
import java.lang.Exception
import java.lang.IndexOutOfBoundsException

class Day9 : Day {

    private val map = mutableSetOf<Pair<Int, Int>>()
    private val offsets = listOf((0 to -1), (0 to 1), (-1 to 0), (1 to 0))
    private val lavaTubes = readFile()

    private fun readFile(): List<String> {

        return File("src/main/resources/input9.txt").readLines()


    }

    override fun partOne(): Int {

        val lavaTubes = readFile()

        var riskLevel = 0

        lavaTubes.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->

                if (checkNeighbor(x, y)) {
                    riskLevel += (lavaTubes[y][x] + 1).toString().toInt()
                }


            }
        }

        println("Day 9 Part 1: $riskLevel")

        return riskLevel
    }

    override fun partTwo(): Int {

        val basins = mutableListOf<Int>()

        lavaTubes.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->

                val basinSize = scoreNeighbors(x, y)

                basins.add(basinSize)
            }
        }

        val ans = basins.sorted().takeLast(3).reduce { acc, value ->
            acc * value
        }

        println("Day 9 Part 2: $ans")
        return ans
    }

    private fun scoreNeighbors(x: Int, y: Int): Int {
        return try {
            if (lavaTubes[y][x] == '9' || map.contains(Pair(x, y))) {
                0
            } else {
                map.add(Pair(x, y))

                1 + offsets.sumOf {
                    scoreNeighbors(x + it.first, y + it.second)
                }
            }
        }
        catch (exception: IndexOutOfBoundsException) {
            0
        }


    }

    private fun checkNeighbor(x: Int, y: Int): Boolean {

        return offsets.map {
                try {
                    if (lavaTubes[y + it.second][x + it.first] > lavaTubes[y][x]) 1 else 0
                }
                catch(exception: IndexOutOfBoundsException) {
                    1
                }
            }.sum() == 4



    }
}