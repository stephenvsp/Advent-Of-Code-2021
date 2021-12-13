import java.io.File

class Day12 : Day {

    private fun readFile(): Map<String, Set<String>> {

        val nodes = mutableMapOf<String, MutableSet<String>>()

        val lines = File("src/main/resources/input12.txt").readLines()

        lines.forEach {
            val split = it.split("-")

            if (split[0] !in nodes.keys) {
                nodes[split[0]] = mutableSetOf()
            }

            if (split[1] !in nodes.keys) {
                nodes[split[1]] = mutableSetOf()
            }

            nodes[split[0]]!!.addAll(mutableSetOf(split[1]))
            nodes[split[1]]!!.addAll(mutableSetOf(split[0]))


        }
        return nodes.toMap()
    }

    private fun isSmallCave(cave: String): Boolean {
        return cave[0].isLowerCase()
    }

    private fun depthFirstTraversal(currentCave: String, path: MutableList<String>, smallCavesTwice: Boolean = false) {
        if (currentCave == "end") {
            if (!paths.contains(path)) {
                paths.add(path)
            }
        }
        else {
            caves[currentCave]!!.forEach { nextCave ->
                if (smallCavesTwice) {

                    val smallCaveVisits = path.filter { isSmallCave(it) }.groupBy { it }.mapValues { it.value.size }
                    val canIGoIn = if (smallCaveVisits.values.contains(2)) {
                        !path.contains(nextCave)
                    } else {
                        !(nextCave == "start" || nextCave == "false")
                    }

                    if (!isSmallCave(nextCave) || canIGoIn) {
                        val newList = mutableListOf<String>()
                        newList.addAll(path)
                        newList.add(nextCave)
                        depthFirstTraversal(nextCave, newList, true)
                    }
                }
                else {
                    if (!isSmallCave(nextCave) || !path.contains(nextCave)) {
                        val newList = mutableListOf<String>()
                        newList.addAll(path)
                        newList.add(nextCave)
                        depthFirstTraversal(nextCave, newList)
                    }
                }
            }
        }
    }


    private val caves = readFile()
    private var paths = mutableListOf<List<String>>()

    override fun partOne(): Int {
        depthFirstTraversal("start", mutableListOf("start"))

        val ans = paths.size

        println("Day 12 Part 1: $ans")
        return ans
    }

    override fun partTwo(): Int {
        depthFirstTraversal("start", mutableListOf("start"), true)

        val ans = paths.size

        println("Day 12 Part 2: $ans")
        return ans
    }
}