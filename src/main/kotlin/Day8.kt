import java.io.File

class Day8 {


    private data class Signal(val input: List<String>, val output: List<String>)

    private fun readFile(): List<Signal> {

        var signals = mutableListOf<Signal>()

        val lines = File("src/main/resources/input8.txt").readLines()

        lines.forEach {
            val parts = it.split(" ")

            val input = parts.subList(0, 10)
            val output = parts.subList(11, 15).map {
                it.toList().sorted().joinToString()
            }


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

            val one = signal.input.find { it.length == 2 }!!
            val four = signal.input.find { it.length == 4 }!!
            val seven = signal.input.find { it.length == 3 }!!
            val eight = signal.input.find { it.length == 7 }!!

            val fourDiff = four.filter { !one.contains(it) }

            val lengthOfFive = signal.input.filter { it.length == 5}

            val three = lengthOfFive.find { it.contains(one[0]) && it.contains(one[1]) }
            val five = lengthOfFive.find { it.contains(fourDiff[0]) && it.contains(fourDiff[1]) }
            val two = lengthOfFive.find { it != three && it != five }

            var lengthOfSix = signal.input.filter { it.length == 6 }

            val nine = lengthOfSix.find { it.contains(four[0]) && it.contains(four[1]) && it.contains(four[2]) && it.contains(four[3]) }
            lengthOfSix = lengthOfSix.filter {
                it != nine
            }
            val six = lengthOfSix.find { it.contains(fourDiff[0]) && it.contains(fourDiff[1]) }
            val zero = lengthOfSix.find { it != six }

            val signalMap = mapOf(zero to 0, one to 1, two to 2, three to 3, four to 4, five to 5, six to 6, seven to 7, eight to 8, nine to 9).mapKeys {
                it.key!!.toList().sorted().joinToString()
            }

            val output = signal.output.map {
                signalMap[it]
            }

            count += output.joinToString("").toInt()

        }

        println("Day 8 Part 2: $count")

        return count
    }
}