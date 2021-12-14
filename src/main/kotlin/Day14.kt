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

    fun partTwo(): Long {

        var pairMap = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()

        repeat(40) {
            var newPairMap = pairMap.toMutableMap()
            pairMap.forEach { (oldString, count) ->
                val newStrings = """${oldString[0]}${rules[oldString]!!}${oldString[1]}""".windowed(2)

                newStrings.filter { it != oldString }.forEach {
                    newPairMap[it] = newPairMap.getOrDefault(it, 0) + count
                }

                if (!newStrings.contains(oldString)) {
                    newPairMap[oldString] = newPairMap[oldString]!! - count
                }
            }
            pairMap = newPairMap.filter { it.value != 0L }.toMutableMap()
        }


        var charMap = mutableMapOf<Char, Long>()
        pairMap.forEach {
            charMap[it.key[0]] = charMap.getOrDefault(it.key[0], 0) + it.value
            charMap[it.key[1]] = charMap.getOrDefault(it.key[1], 0) + it.value
        }

        charMap = charMap.mapValues { it.value / 2 }.toMutableMap()
        charMap[polymer[0]] = charMap.getOrDefault(polymer[0], 0) + 1
        charMap[polymer[polymer.lastIndex]] = charMap.getOrDefault(polymer[polymer.lastIndex], 0) + 1

        val max = charMap.values.maxByOrNull { it }!!
        val min = charMap.values.minByOrNull { it }!!

        val ans = max - min

        println("Day 14 Part 2: ans")

        return ans
    }
}