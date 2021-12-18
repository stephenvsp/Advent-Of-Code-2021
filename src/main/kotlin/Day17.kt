import java.io.File
import java.lang.Integer.max

class Day17 : Day {

    val input = File("src/main/resources/input17.txt").readLines().first()

    val targetArea = Regex("""target area: x=(\d+)\.\.(\d+), y=(-\d+)\.\.(-\d+)""")
        .matchEntire(input)!!
        .destructured.let { (x1, x2, y1, y2) -> TargetArea(x1.toInt(), x2.toInt(), y1.toInt(), y2.toInt()) }

    data class TargetArea(val x1: Int, val x2: Int, val y1: Int, val y2: Int) {
        operator fun contains(point: Pair<Int, Int>) = point.first in x1..x2 && point.second in y1..y2
    }

    override fun partOne(): Int {
        val ans = (-targetArea.y1 - 1).sequenceSum()

        println("Day 17 Part 1: $ans")

        return ans
    }

    override fun partTwo(): Int {
        val madeBasketVelocity = mutableListOf<Pair<Int, Int>>()

        for (x in 1..500) {
            for (y in -100..500) {

                var xVelocity = x
                var yVelocity = y
                var xPos = 0
                var yPos = 0

                while (!targetArea.contains(xPos to yPos) && !missedBasket(xPos, yPos)) {

                    xPos += xVelocity
                    yPos += yVelocity

                    xVelocity = applyDrag(xVelocity)
                    yVelocity--
                }

                if (targetArea.contains(xPos to yPos)) {
                    madeBasketVelocity.add(x to y)
                }
            }
        }

        val ans = madeBasketVelocity.size

        println("Day 17 Part 1: $ans")

        return ans
    }

    private fun missedBasket(x: Int, y: Int): Boolean = missedX(x) || missedY(y)

    private fun missedX(x: Int): Boolean = x > targetArea.x2

    private fun missedY(y: Int): Boolean = y < targetArea.y1

    private fun applyDrag(x: Int): Int = if (x > 1) x - 1 else 0

    private fun Int.sequenceSum(): Int {
        return (1 + this) * this / 2
    }
    

}