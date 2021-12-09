import java.io.File
import java.lang.Exception

class Day9 {

    private fun readFile(): List<String> {

        return File("src/main/resources/input9.txt").readLines()


    }
    fun partOne(): Int {

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

        return riskLevel
    }

    fun checkNeighbor(x: Int, y: Int, neighborX: Int, neighborY: Int, lavaTubes: List<String>): Boolean {
        try {
            if (lavaTubes[neighborY][neighborX] > lavaTubes[y][x]) return true
        }
        catch (e: Exception) {
            return true
        }
        return false

    }
}