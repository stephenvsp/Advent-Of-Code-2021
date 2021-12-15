import java.io.File

@ExperimentalStdlibApi
class Day14 {

    private val lines = File("src/main/resources/input14.txt").readLines()

    private var polymer = lines.first()

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

    fun partTwo(): Long {

        var pairMap = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }


        val pairsCount = (0 until 40).fold(pairMap) { current, _ ->
            var newPairMap = mutableMapOf<String, Long>()
            current.forEach { (oldString, count) ->
                val one = oldString[0] + rules[oldString]!!
                val two = rules[oldString]!! + oldString[1]

                newPairMap[one] = newPairMap.getOrDefault(one, 0) + count
                newPairMap[two] = newPairMap.getOrDefault(two, 0) + count
            }
            newPairMap
        }

        val charCount = buildMap<Char, Long> {
            put(polymer[0], 1)
            pairsCount.forEach { (pair, count) ->
                put(pair[1], getOrDefault(pair[1], 0) + count)
            }
        }

        val ans = charCount.maxOf { it.value } - charCount.minOf { it.value }

        println("Day 14 Part 2: $ans")
        return ans
    }

//    fun part2(): Long {
//        val initial = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
//
//        val pairsCount = (0 until 40).fold(initial) { current, _ ->
//            buildMap {
//                current.forEach { (pair, count) ->
//                    val first = pair[0] + rules.getValue(pair)
//                    val second = rules.getValue(pair) + pair[1]
//                    put(first, getOrDefault(first, 0) + count)
//                    put(second, getOrDefault(second, 0) + count)
//                }
//            }
//        }
//
//        val charsCount = buildMap<Char, Long> {
//            put(polymer[0], 1)
//            pairsCount.forEach { (pair, count) ->
//                put(pair[1], getOrDefault(pair[1], 0) + count)
//            }
//        }
//
//        val ans = charsCount.maxOf { it.value } - charsCount.minOf { it.value }
//
//        println("Day 14 Part 2: $ans")
//        return ans
//    }
}