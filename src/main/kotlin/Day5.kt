import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Math.abs

class Day5 : Day {

    private class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

    private fun readFile(): List<Line> {

        val lineList = mutableListOf<Line>()
        val lines = File("src/main/resources/input5.txt").readLines()

        lines.forEach {
            val (x1, y1, x2, y2) = Regex("^(\\d+),(\\d+)\\s+->\\s+(\\d+),(\\d+)$").find(it)!!.destructured
            lineList.add(Line(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt()))
        }


        return lineList

    }

    override fun partOne(): Int {
        val lines = readFile()

        var grid = MutableList(1000) { MutableList(1000) { 0 } }

        lines.forEach { line ->
            if (line.x1 == line.x2) {
                val bigger = max(line.y1, line.y2)
                val smaller = min(line.y1, line.y2)

                for (i in smaller..bigger) {
                    grid[i][line.x1]++
                }
            }
            else if (line.y1 == line.y2) {
                val bigger = max(line.x1, line.x2)
                val smaller = min(line.x1, line.x2)

                for (i in smaller..bigger) {
                    grid[line.y1][i]++
                }
            }
        }

        var ans = 0

        grid.forEach { row ->
            row.forEach { cell ->
                if (cell > 1) {
                    ans++
                }
            }
        }

        println("Day 5 Part 1: $ans")
        return ans
    }

    override fun partTwo(): Int {
        val lines = readFile()

        var grid = MutableList(1000) { MutableList(1000) { 0 } }

        lines.forEach { line ->
            if (line.x1 == line.x2) {
                val bigger = max(line.y1, line.y2)
                val smaller = min(line.y1, line.y2)

                for (i in smaller..bigger) {
                    grid[i][line.x1]++
                }
            }
            else if (line.y1 == line.y2) {
                val bigger = max(line.x1, line.x2)
                val smaller = min(line.x1, line.x2)

                for (i in smaller..bigger) {
                    grid[line.y1][i]++
                }
            }
            else {

                val distance = abs(line.y1 - line.y2)
                val smallerY = min(line.y2, line.y1)

                val matchingX = if (smallerY == line.y1) line.x1 else line.x2
                val notMatchingX = if (smallerY != line.y1) line.x1 else line.x2

                val direction = if (matchingX < notMatchingX) 1 else -1

                for (i in 0..distance) {
                    grid[smallerY + i][matchingX + (i * direction)]++
                }

            }
        }

        var ans = 0

        grid.forEach { row ->
            row.forEach { cell ->
                if (cell > 1) {
                    ans++
                }
            }
        }

        println("Day 5 Part 1: $ans")
        return ans
    }
}