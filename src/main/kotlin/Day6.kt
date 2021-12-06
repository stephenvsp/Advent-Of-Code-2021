import java.io.File

class Day6 {

    private fun readFile(): List<Int> {

        val lines = File("src/main/resources/input6.txt").readLines()[0].split(",")

        return lines.map { it.toInt() }

    }

    fun partOne(): Int {
        var schoolOfFish = readFile().toMutableList()

//        val ans = reproduceFish(80, fish)

        for (i in 1..80) {
            var count = 0
            for (j in 0 until schoolOfFish.size) {
                if (schoolOfFish[j] != 0) {
                    schoolOfFish[j]--
                }
                else {
                    schoolOfFish[j] = 6
                    count++
                }
            }
            schoolOfFish.addAll(MutableList(count) { 8 })
        }

        val ans = schoolOfFish.size

        println("Day 6 Part 1: $ans")

        return ans
    }

//    fun reproduceFish(daysLeft: Int, fish: List<Int>): Int {
//        return if (daysLeft == 0) {
//            fish.size
//        }
//        else {
//             reproduceFish(daysLeft - 1, List(fish.filter { it == 0 }.size) { 8 } + fish.map { Math.floorMod(it - 1, 7) })
//        }
//    }
}