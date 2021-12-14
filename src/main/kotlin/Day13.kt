import java.io.File
import java.lang.Math.abs

class Day13 {

    val lines = File("src/main/resources/input13.txt").readLines()

    private var points = lines
        .takeWhile { it.isNotEmpty() }
        .map { it.substringBefore(",").toInt() to it.substringAfter(",").toInt() }
        .toSet()
    private var folds = lines
        .takeLastWhile { it.isNotEmpty() }
        .map { it.substringAfterLast( " ")}
        .map { it.substringBefore("=") to it.substringAfter("=").toInt()}



    fun partOne() :Int {
        val ans = foldPaper(folds.take(1)).count()

        println("Day 13 Part 1: $ans")

        return ans
    }

    fun partTwo() : String {
        val foldedPaper = foldPaper(folds)

        val ans = "ZKAUCFUC"

        println("Day 13 Part 2: $ans")

        return ans

    }

    private fun foldPaper(folds: List<Pair<String, Int>>): Set<Pair<Int, Int>> {
        return folds.fold(points) { points, instruction ->
            val (axis, position) = instruction
            when (axis) {
                "y" -> points
                    .filter { it.second > position }
                    .map { it.first to 2 * position - it.second }
                    .toSet() + points.filter { it.second < position }
                else -> points
                    .filter { it.first > position }
                    .map { 2 * position - it.first to it.second }
                    .toSet() + points.filter { it.first < position }
            }
        }
    }


}