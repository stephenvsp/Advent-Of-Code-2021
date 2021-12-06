import java.io.File

class Day6 {

    private fun readFile(): List<Int> {

        val lines = File("src/main/resources/input6.txt").readLines()[0].split(",")

        return lines.map { it.toInt() }

    }

    fun partOne(): Int {
        var schoolOfFish = readFile().toMutableList()

//        val ans = reproduceFish(80, fish)

        for (i in 1..80) {
            var count = 0
            for (j in 0 until schoolOfFish.size) {
                if (schoolOfFish[j] != 0) {
                    schoolOfFish[j]--
                }
                else {
                    schoolOfFish[j] = 6
                    count++
                }
            }
            schoolOfFish.addAll(MutableList(count) { 8 })
        }

        val ans = schoolOfFish.size

        println("Day 6 Part 1: $ans")

        return ans
    }

    fun partTwo(): Long {
        val schoolOfFish = readFile()

        val ans = simulatePufferPuffs(schoolOfFish, 256)

        println("Day 6 Part 2: $ans")

        return ans
    }

    fun simulatePufferPuffs(state : List<Int>, steps : Int) : Long {
        var initialState = state.groupBy { it }.mapValues { valueList -> valueList.value.size.toLong() }
        // map from puffer fish in state X to count of fish in that state
        var nextState = initialState.toMap()
        for (i in 0 until steps) {
            var countZeroFish = nextState.getOrDefault(0, 0)

            var newNextState = nextState
                .filterKeys { key -> key > 0 }
                .mapKeys { kv -> kv.key - 1 }
                .toMutableMap()

            if (countZeroFish > 0) {
                newNextState[8] = newNextState.getOrDefault(8, 0) + countZeroFish
                newNextState[6] = newNextState.getOrDefault(6, 0) + countZeroFish
            }

            nextState = newNextState
        }
        return nextState.values.sum()
    }
}