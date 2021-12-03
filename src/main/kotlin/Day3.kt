import java.io.File

class Day3 {

    private fun readFile(): List<String> {
        val binaryList = mutableListOf<String>()
        File("src/main/resources/input3.txt").useLines{
                lines -> lines.forEach {
                    binaryList.add(it)
                }
        }

        return binaryList
    }

    fun partOne(): Int {
        val report = readFile()

        var gamma = ""

        for (i in report[0].indices) {
            gamma += report.map { it[i] }.groupBy { it }.maxByOrNull { it.value.size }?.key
        }

        val epsilon = gamma.map { if (it == '0') '1' else 0 }.joinToString("")

        val ans = gamma.toInt(2) * epsilon.toInt(2)

        println("Day 3 Part 1: $ans")

        return ans
    }

    fun partTwo(): Int {
        val report = readFile()

        var filteredOxygen = report
        var filteredCO2 = report

        for (i in report[0].indices) {
            filteredOxygen = filteredOxygen.filterBy(i, "1") { it.maxByOrNull { it.value.size }?.key!! }
            filteredCO2 = filteredCO2.filterBy(i,"0") { it.minByOrNull { it.value.size }?.key!!}
        }

        val ans = filteredOxygen[0].toInt(2) * filteredCO2[0].toInt(2)

        println("Day 3 Part 2: $ans")

        return ans
    }

    private fun List<String>.filterBy(pos: Int, defaultWhenEqual: String, sort: (Map<Char, List<Char>>) -> Char): List<String> {
        if (this.size == 1) return this
        val groupedInput = this.map { it[pos] }.groupBy{ it }
        val isEqual = groupedInput.values.toMutableList()[0].size == groupedInput.values.toMutableList()[1].size
        return this.filter {
            if (isEqual) {
                it[pos].toString() == defaultWhenEqual
            }
            else {
                it[pos] == sort(groupedInput)
            }
        }
    }
}