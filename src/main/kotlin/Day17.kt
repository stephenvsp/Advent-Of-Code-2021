class Day17 : Day {

    val xRange = IntRange(288, 330)
    val yRange = IntRange(-96, -50)

    override fun partOne(): Int {
        val madeBasketHighestYValues = mutableListOf<Int>()

        for (x in 1..1000) {
            for (y in -1000..1000) {
                var xVelocity = x
                var yVelocity = y
                var xPos = 0
                var yPos = 0
                val yPositions = mutableListOf<Int>()

                while (!inBasket(xPos, yPos) && !missedBasket(xPos, yPos)) {
                    yPositions.add(yPos)

                    xPos += xVelocity
                    yPos += yVelocity

                    xVelocity = applyDrag(xVelocity)
                    yVelocity--
                }

                if (inBasket(xPos, yPos)) {
                    madeBasketHighestYValues.add(yPositions.maxOf { it })
                }
            }
        }

        val ans = madeBasketHighestYValues.maxOf{ it }

        println("Day 17 Part 1: $ans")

        return ans
    }

    override fun partTwo(): Int {
        TODO("Not yet implemented")
    }

    private fun inBasket(x: Int, y: Int): Boolean {
        return x in xRange && y in yRange
    }

    private fun missedBasket(x: Int, y: Int): Boolean {
        return x > xRange.last || y < yRange.last
    }

    private fun applyDrag(x: Int): Int {
        return if (x > 1) x - 1 else 0
    }
}