import java.io.File

class Day12 : Day {

    private val connections = File("src/main/resources/input12.txt").readLines()
        .map { it.split("-") }
        .flatMap { (begin, end) -> listOf(begin to end, end to begin) }
        .filterNot { (_, end) -> end == "start" }
        .groupBy({ it.first }, { it.second })

    override fun partOne(): Int {
        return countPaths { cave, currentPath ->
            cave in currentPath
        }
    }

    override fun partTwo(): Int {
        return countPaths { cave, currentPath ->
            val counts = currentPath.filter { it.first().isLowerCase() }.groupingBy { it }.eachCount()
            cave in counts.keys && counts.any { it.value > 1 }
        }
    }

    private fun countPaths(
        cave: String = "start",
        path: List<String> = listOf(),
        isSmallCaveNotAllowed: (cave: String, currentPath: List<String>) -> Boolean
    ): Int {
        return when {
            cave == "end" -> 1
            cave.first().isLowerCase() && isSmallCaveNotAllowed(cave, path) -> 0
            else -> connections.getValue(cave).sumOf { countPaths(it, path + cave, isSmallCaveNotAllowed) }
        }
    }
}