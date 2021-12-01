import java.io.File

class Day1 {

    private fun readFile(): List<Int> {
        val intList = mutableListOf<Int>()
        File("src/main/resources/input1.txt").useLines{ lines -> lines.forEach { intList.add(it.toInt() )}}

        return intList
    }

    fun partOne() {

        val ans = readFile().zipWithNext().filter { it.second > it.first }.size
        println("Day 1 Part 1: $ans")


    }
}