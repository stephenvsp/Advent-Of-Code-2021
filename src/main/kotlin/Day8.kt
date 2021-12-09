import java.io.File
import java.util.*

class Day8 {


    private data class Signal(val input: List<String>, val output: List<String>)

    private fun readFile(): List<Signal> {

        var signals = mutableListOf<Signal>()

        val lines = File("src/main/resources/input8.txt").readLines()

        lines.forEach {
            val parts = it.split(" ")

            val input = parts.subList(0, 10)
            val output = parts.subList(11, 15)

            signals.add(Signal(input, output))
        }

        return signals
    }

    fun partOne(): Int {
        val signals = readFile()

        var count = 0

        signals.forEach { signal ->
            signal.output.forEach {
                if (it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7) {
                    count++
                }
            }
        }

        println("Day 8 Part 1: $count")

        return count
    }

    fun partTwo(): Int {
        val signals = readFile()

        var count = 0

        signals.forEach { signal ->

            val codes = signal.input.map { it.toSet() }.toMutableSet()
            val decoded: MutableMap<Int, Set<Char>> = mutableMapOf()

            decoded[1] = codes.singleAndRemove(2)
            decoded[4] = codes.singleAndRemove(4)
            decoded[7] = codes.singleAndRemove(3)
            decoded[8] = codes.singleAndRemove(7)

            decoded[9] = codes.singleAndRemove(6, decoded.getValue(4))
            decoded[0] = codes.singleAndRemove(6, decoded.getValue(7))
            decoded[6] = codes.singleAndRemove(6)

            decoded[3] = codes.singleAndRemove(5, decoded.getValue(1))
            decoded[5] = codes.singleAndRemove(5, decoded.getValue(4) - decoded.getValue(1))
            decoded[2] = codes.single()

            count += signal.output.map { digit -> decoded.filterValues { it == digit.toSet() }.keys.single() }
                .joinToString("")
                .toInt()
        }

        println("Day 8 Part 2: $count")

        return count
    }

    fun MutableSet<Set<Char>>.singleAndRemove(
        segments: Int,
        containsAllSegments: Set<Char>? = null
    ): Set<Char> = single {
        it.size == segments && it.containsAll(containsAllSegments ?: emptySet())
    }.also { this.remove(it) }
}