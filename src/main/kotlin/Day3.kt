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
}