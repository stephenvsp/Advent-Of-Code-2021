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
        .map { it.substringAfter( " ")}
        .map { it.substringBefore("=") to it.substringAfter("=").toInt()}



    fun partOne() :Int {

        val fold = folds[0].first
        val value = folds[0].second

        val newPoints = points.map {
            val distanceToFold = abs(value - it.first)

            if (it.first > value) {
                (value - distanceToFold to it.second)
            }
            else {
                it
            }
        }.toSet()

        val ans = newPoints.size
        return ans
    }

    fun partTwo() : String {

        folds.forEach { (fold, value) ->
            points = points.map { (x, y) ->
                if (fold == "x") {
                    val distanceToFold = abs(value - x)
                    val distanceToMove = distanceToFold * 2
                    if (x > value) {
                            (x - distanceToMove to y)
                    }
                    else {
                        (x to y)
                    }
                }
                else {
                    val distanceToFold = abs(value - y)
                    val distanceToMove = distanceToFold * 2

                    if (y > value) {
                        (x to y - distanceToMove)
                    }
                    else {
                        (x to y)
                    }
                }
            }.toMutableSet()

        }


        for (y in 0..10) {
            for (x in 0..50) {
                if (points.contains(x to y)) print("#") else print(".")
            }
            println("")
        }
        return "ZKAUCFUC"
    }


}