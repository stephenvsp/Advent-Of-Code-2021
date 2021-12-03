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

        val mostCommonBit = MutableList(report[0].length) { 0 }

        report.forEach {
            it.forEachIndexed { index, c ->
                if (c == '0') {
                    mostCommonBit[index]--
                }
                else {
                    mostCommonBit[index]++
                }
            }
        }

        val gammaRateBinary = mostCommonBit.map {
            if (it > 0) 1
            else 0
        }.joinToString("")

        val epsilonRateBinary = mostCommonBit.map {
            if (it < 1) 1
            else 0
        }.joinToString("")

        val gammaRateDecimal = gammaRateBinary.toInt(2)
        val epsilonRateDecimal = epsilonRateBinary.toInt(2)

        val ans = gammaRateDecimal * epsilonRateDecimal

        println("Day 3 Part 1: $ans")

        return ans
    }

    fun partTwo(): Int {
        val report = readFile()

        var oxygen = report.toMutableList()

        var index = 0

        while (oxygen.size != 1) {
            var count = 0

            oxygen.forEach{
                if (it[index] == '1') {
                    count++
                }
                else {
                    count--
                }
            }

            if (count >= 0) {
                oxygen.removeIf {
                    it[index] == '0'
                }
            }
            else {
                oxygen.removeIf {
                    it[index] == '1'
                }
            }

            index++
        }

        var co2 = report.toMutableList()

        index = 0

        while (co2.size != 1) {
            var count = 0

            co2.forEach{
                if (it[index] == '1') {
                    count++
                }
                else {
                    count--
                }
            }

            if (count >= 0) {
                co2.removeIf {
                    it[index] == '1'
                }
            }
            else {
                co2.removeIf {
                    it[index] == '0'
                }
            }

            index++
        }

        val oxygenRating = oxygen.first().toInt(2)
        val co2Rating = co2.first().toInt(2)

        val ans = oxygenRating * co2Rating

        println("Day 3 Part 2: $ans")

        return ans
    }
}