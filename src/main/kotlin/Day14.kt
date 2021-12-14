import java.io.File

class Day14 {

    private val lines = File("src/main/resources/input14.txt").readLines()

    private var polymer = lines[0]

    private val rules = lines
        .takeLastWhile { it.isNotEmpty() }
        .map { it.split(" -> ") }
        .associate { (it[0] to it[1]) }

    fun partOne(): Int {

        repeat(10) {
            polymer = polymer.windowed(2).map { it[0] + rules[it]!! }.joinToString("") + polymer[polymer.length - 1]
        }
        val charMap = polymer.groupingBy { it }.eachCount()

        val ans = charMap.maxByOrNull { it.value }!!.value - charMap.minByOrNull { it.value }!!.value

        println("Day 14 Part 1: $ans")
        return ans
    }
}