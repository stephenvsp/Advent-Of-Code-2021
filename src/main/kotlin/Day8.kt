import java.io.File

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
}