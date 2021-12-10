import java.io.File
import java.util.*

class Day10 {

    private fun readFile() = File("src/main/resources/input10.txt").readLines()

    fun partOne(): Int {

        var invalidCharacterMap = mutableMapOf<Char, Int>()
        val matchingCharacters = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val invalidCharacterScore = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

        readFile().forEach { line ->

            val stack = Stack<Char>()
            var firstError = false

            line.forEach {
                if (it in matchingCharacters.keys) {
                    stack.push(it)
                }
                else {
                    if (stack.isEmpty() || matchingCharacters[stack.peek()] != it) {
                        if (!firstError) {
                            invalidCharacterMap[it] = invalidCharacterMap.getOrDefault(it, 0) + 1
                            firstError = true
                        }
                    }
                    else {
                        stack.pop()
                    }
                }
            }
        }

        val ans = invalidCharacterMap.map {
            it.value * invalidCharacterScore.getOrDefault(it.key, 0)
        }.sum()

        println("Day 10 Part 1: $ans")

        return ans
    }

    fun partTwo(): Long {

        val lines = readFile()

        val matchingCharacters = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val autoCompleteScores = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

        val scores = lines.map { line ->

            val stack = Stack<Char>()
            var valid = true

            line.forEach {
                if (it in matchingCharacters.keys) {
                    stack.push(it)
                }
                else {
                    if (stack.isEmpty() || matchingCharacters[stack.peek()] != it) {
                        valid = false
                    }
                    else {
                        stack.pop()
                    }
                }
            }

            if (stack.isNotEmpty() && valid) {
                stack.reversed().fold(0) { acc: Long, c ->
                    acc * 5 + autoCompleteScores[matchingCharacters[c]!!]!!
                }
            }
            else {
                0L
            }
        }.filter { it != 0L }

        val ans = scores.sorted()[scores.size / 2]

        println("Day 10 Part 2: $ans")

        return ans
    }
}