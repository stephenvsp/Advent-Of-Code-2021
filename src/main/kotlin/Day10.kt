import java.io.File
import java.util.*

class Day10 : Day {

    private fun readFile() = File("src/main/resources/input10.txt").readLines()

    override fun partOne(): Int {


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

    override fun partTwo(): Int {
        TODO("Not yet implemented")
    }
}