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

        var oxygen = report.toMutableList()
        var co2 = report.toMutableList()

        for (i in report[0].indices) {
            var oxygenCount = 0
            var co2Count = 0

            oxygen.forEach{
                if (it[i] == '1') {
                    oxygenCount++
                }
                else {
                    oxygenCount--
                }
            }

            co2.forEach{
                if (it[i] == '1') {
                    co2Count++
                }
                else {
                    co2Count--
                }
            }

            if (oxygen.size != 1) {
                if (oxygenCount >= 0) {
                    oxygen.removeIf {
                        it[i] == '0'
                    }
                }
                else {
                    oxygen.removeIf {
                        it[i] == '1'
                    }
                }
            }

            if (co2.size != 1) {
                if (co2Count >= 0) {
                    co2.removeIf {
                        it[i] == '1'
                    }
                }
                else {
                    co2.removeIf {
                        it[i] == '0'
                    }
                }
            }
        }



        val oxygenRating = oxygen.first().toInt(2)
        val co2Rating = co2.first().toInt(2)

        val ans = oxygenRating * co2Rating

        println("Day 3 Part 2: $ans")

        return ans
    }
}