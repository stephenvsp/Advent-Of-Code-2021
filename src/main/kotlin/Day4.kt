import java.io.File

class Day4 {

    private data class Number(val number: String, var called: Boolean)

    private data class Board(val rows : List<List<Number>>, var won: Boolean = false) {

        val rowScore = mutableListOf(0, 0, 0, 0, 0)
        val columnScore = mutableListOf(0, 0, 0, 0, 0)

        fun isBingo(): Boolean {
            return rowScore.contains(5) || columnScore.contains(5)
        }

        fun sumUnmarked(): Int {
            var sum = 0

            rows.forEach { row ->
                row.forEach {
                    if (!it.called) {
                        sum += it.number.toInt()
                    }
                }
            }

            return sum
        }

        fun markNumber(number: String) : Boolean {

            rows.forEachIndexed { y, column ->
                column.forEachIndexed { x, value ->
                    if (value.number == number) {
                       value.called = true
                        rowScore[y]++
                        columnScore[x]++

                        return true
                    }
                }
            }
            return false
        }
    }

    private fun readFile(): Pair<List<String>, List<Board>> {
        val boards = mutableListOf<Board>()

        val lines = File("src/main/resources/input4.txt").readLines()

        val numbers = lines[0].split(",")

        var rows = mutableListOf<List<Number>>()

        for (count in 2 until lines.size) {
            if (lines[count].isEmpty()) {
                boards.add(Board(rows))
                rows = mutableListOf()
            }
            else {
                val row = lines[count].trimStart().split("\\s+".toRegex())
                val numbers = row.map {
                    Number(it, false)
                }
                rows.add(numbers)
            }
        }
        boards.add(Board(rows))

        return Pair(numbers, boards)
    }

    fun partOne(): Int {
        val file = readFile()
        val numbers = file.first
        val boards = file.second

        numbers.forEach { calledNumber ->
            boards.forEach { board ->
                if (board.markNumber(calledNumber)) {
                    if (board.isBingo()) {
                        val ans = calledNumber.toInt() * board.sumUnmarked()
                        println("Day 4 Part 1: $ans")
                        return ans
                    }
                }
            }
        }

        return 0
    }

    fun partTwo(): Int {

        val file = readFile()
        val numbers = file.first
        val boards = file.second

        var lastBoard = Board(listOf())
        var numberCalledWhenWon = -1

        numbers.forEach { calledNumber ->
            boards.forEach { board ->
                if (!board.won) {
                    if (board.markNumber(calledNumber)) {
                        if (board.isBingo()) {
                            board.won = true
                            lastBoard = board
                            numberCalledWhenWon = calledNumber.toInt()
                        }
                    }
                }
            }
        }

        val ans = lastBoard.sumUnmarked() * numberCalledWhenWon
        println("Day 4 Part 2: $ans")
        return ans
    }
}