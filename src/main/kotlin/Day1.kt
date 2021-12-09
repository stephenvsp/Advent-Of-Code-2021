import java.io.File

class Day1 : Day {

    private fun readFile(): List<Int> {
        val intList = mutableListOf<Int>()
        File("src/main/resources/input1.txt").useLines{ lines -> lines.forEach { intList.add(it.toInt() )}}

        return intList
    }

    override fun partOne(): Int {

        val ans = readFile().zipWithNext().filter { it.second > it.first }.size
        println("Day 1 Part 1: $ans")

        return ans

    }

    override fun partTwo(): Int {
        val intList = readFile()

        val ans = intList.windowed(3).map { it.sum() }.zipWithNext().filter { it.second > it.first }.size

        println("Day 1 Part 2: $ans")

        return ans
    }
}