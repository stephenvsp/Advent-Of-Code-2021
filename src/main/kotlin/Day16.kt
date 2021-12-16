import java.io.File

class Day16 {

    val input = File("src/main/resources/input16.txt").readLines().first()

    var binary = input.map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString("")

    var pointer = 0

    var versionCount = 0

    private fun Boolean.toLong() = if (this) 1L else 0L

    private fun readPacket(): Long {
        val version = binary.substring(pointer, pointer + 3).toInt(2)
        versionCount += version
        pointer += 3
        val tag = binary.substring(pointer, pointer + 3).toInt(2)
        pointer += 3

        return when (tag) {
            0 -> readOperator().sum()
            1 -> readOperator().reduce { a, b -> a * b }
            2 -> readOperator().minOrNull()!!
            3 -> readOperator().maxOrNull()!!
            4 -> readLiteral()
            5 -> {
                val values = readOperator()
                (values[0] > values[1]).toLong()
            }
            6 -> {
                val values = readOperator()
                (values[0] < values[1]).toLong()
            }
            7 -> {
                val values = readOperator()
                (values[0] == values[1]).toLong()
            }
            else -> {
                error("WTF")
            }
        }
    }

    private fun readLiteral(): Long {
        var numString = ""
        var group = ""
        do {
            group = binary.substring(pointer, pointer + 5)
            pointer += 5

            numString += group.substring(1)
        }
        while (group[0] == '1')

        return numString.toLong(2)
    }

    private fun readOperator(): List<Long> {
        var subPacketSums = mutableListOf<Long>()
        val lengthTypeId = binary.substring(pointer, pointer + 1).toInt(2)
        pointer += 1
        if (lengthTypeId == 0) {
            val length = binary.substring(pointer, pointer + 15).toInt(2)
            pointer += 15

            var subPacketEnd = pointer + length
            while (pointer < subPacketEnd) {
                subPacketSums.add(readPacket())
            }

        }
        else {
            val numSubPackets = binary.substring(pointer, pointer + 11).toInt(2)
            pointer += 11

            repeat(numSubPackets) {
                subPacketSums.add(readPacket())
            }

        }
        return subPacketSums
    }

    fun partOne(): Int {

        while (pointer < binary.length) {
            readPacket()
            pointer += 8 - pointer % 8
        }

        println("Day 16 Part 1: $versionCount")

        return versionCount
    }



    fun partTwo(): Long {
        val ans = readPacket()

        println("Day 16 Part 1: $ans")

        return ans
    }

}