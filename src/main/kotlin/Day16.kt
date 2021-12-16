import java.io.File

class Day16 : Day {

    val input = File("src/main/resources/input16.txt").readLines().first()

    var binary = input.chunked(2).joinToString("") { Integer.toBinaryString(it.toInt(16)).padStart(8, '0') }

    var pointer = 0

    var versionCount = 0


    private fun readPacket(): Long {
        val version = binary.substring(pointer, pointer + 3).toInt(2)
        versionCount += version
        pointer += 3
        val tag = binary.substring(pointer, pointer + 3).toInt(2)
        pointer += 3

        if (tag == 4) {
            //literal
            return readLiteral()
        }
        else {
            //operator
            val nums = readOperator()
        }
        return 0L
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

    private fun readOperator() {
        val lengthTypeId = binary.substring(pointer, pointer + 1).toInt(2)
        pointer += 1
        if (lengthTypeId == 0) {
            val length = binary.substring(pointer, pointer + 15).toInt(2)
            pointer += 15

            var subPacketEnd = pointer + length

            while (pointer < subPacketEnd) {
                readPacket()
            }

        }
        else {
            val numSubPackets = binary.substring(pointer, pointer + 11).toInt(2)
            pointer += 11

            repeat(numSubPackets) {
                readPacket()
            }

        }
    }

    override fun partOne(): Int {

        while (pointer < binary.length) {
            readPacket()
            pointer += 8 - pointer % 8
        }
        return versionCount
    }



    override fun partTwo(): Int {
            val ans = readPacket()
            return 0
    }

}