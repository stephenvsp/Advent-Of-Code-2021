import java.io.File

class Day2 {

    private enum class Direction(val direction: String) {
        FORWARD("forward"),
        UP("up"),
        DOWN("down"),
    }

    private data class Instruction(val direction: Direction, val distance: Int)

    private fun readFile(): List<Instruction> {
        val instructionList = mutableListOf<Instruction>()
        File("src/main/resources/input2.txt").useLines{
                lines -> lines.forEach {
                    val parts = it.split(" ")
                    val direction = when(parts[0]) {
                        "forward" -> Direction.FORWARD
                        "up" -> Direction.UP
                        else -> Direction.DOWN
                    }
                    val newInstruction = Instruction(direction, parts[1].toInt())
                    instructionList.add(newInstruction)
            }
        }

        return instructionList
    }

    fun partOne(): Int {
        val instructions = readFile()

        var depth = 0
        var horizontal = 0

        instructions.forEach {
            when(it.direction) {
                Direction.FORWARD -> horizontal += it.distance
                Direction.UP -> depth -= it.distance
                Direction.DOWN -> depth += it.distance
            }
        }

        val ans = depth * horizontal

        println("Day 2 Part 1: $ans")

        return ans
    }

    fun partTwo(): Int {
        val instructions = readFile()

        var depth = 0
        var horizontal = 0
        var aim = 0

        instructions.forEach {
            when(it.direction) {
                Direction.FORWARD -> {
                    horizontal += it.distance
                    depth += aim * it.distance
                }
                Direction.UP -> aim -= it.distance
                Direction.DOWN -> aim += it.distance
            }
        }

        val ans = depth * horizontal

        println("Day 2 Part 2: $ans")

        return ans
    }
}