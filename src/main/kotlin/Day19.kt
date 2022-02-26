import java.io.File
import java.lang.Math.abs

class Day19() : Day {

    val input = File("src/main/resources/input19.txt").readText()

    private val scanners = input
        .split("""--- scanner \d+ ---""".toRegex())
        .filterNot { it.isEmpty() }
        .map { s ->
            Scanner(
                s.trim().lines()
                    .map { it.split(",") }
                    .map { (x, y, z) -> Point(x.toInt(), y.toInt(), z.toInt()) }
                    .toSet()
            )
        }

    override fun partOne() = assembleMap().beacons.size

    override fun partTwo() = assembleMap().scannersPositions.let { positions ->
        positions.flatMapIndexed { index, first -> positions.drop(index + 1).map { second -> first to second } }
            .maxOf { (first, second) -> first distanceTo second }
    }

    private fun assembleMap(): AssembledMap {
        val foundBeacons = scanners.first().beacons.toMutableSet()
        val foundScannersPositions = mutableSetOf(Point(0, 0, 0))

        val remaining = ArrayDeque<Scanner>().apply { addAll(scanners.drop(1)) }
        while (remaining.isNotEmpty()) {
            val candidate = remaining.removeFirst()
            when (val transformedCandidate = Scanner(foundBeacons).getTransformedIfOverlap(candidate)) {
                null -> remaining.add(candidate)
                else -> {
                    foundBeacons.addAll(transformedCandidate.beacons)
                    foundScannersPositions.add(transformedCandidate.position)
                }
            }
        }

        return AssembledMap(foundBeacons, foundScannersPositions)
    }

    private data class Scanner(val beacons: Set<Point>) {
        fun allRotations() = beacons.map { it.allRotations() }.transpose().map { Scanner(it) }

        fun getTransformedIfOverlap(otherScanner: Scanner): TransformedScanner? {
            return otherScanner.allRotations().firstNotNullOfOrNull { otherReoriented ->
                beacons.firstNotNullOfOrNull { first ->
                    otherReoriented.beacons.firstNotNullOfOrNull { second ->
                        val otherPosition = first + -second
                        val otherTransformed = otherReoriented.beacons.map { otherPosition + it }.toSet()
                        when ((otherTransformed intersect beacons).size >= 12) {
                            true -> TransformedScanner(otherTransformed, otherPosition)
                            false -> null
                        }
                    }
                }
            }
        }

        private fun List<Set<Point>>.transpose(): List<Set<Point>> {
            return when (all { it.isNotEmpty() }) {
                true -> listOf(map { it.first() }.toSet()) + map { it.drop(1).toSet() }.transpose()
                false -> emptyList()
            }
        }
    }

    private data class TransformedScanner(val beacons: Set<Point>, val position: Point)

    private data class AssembledMap(val beacons: Set<Point>, val scannersPositions: Set<Point>)

    private data class Point(val x: Int, val y: Int, val z: Int) {
        operator fun plus(other: Point) = Point(x + other.x, y + other.y, z + other.z)
        operator fun unaryMinus() = Point(-this.x, -this.y, -this.z)

        operator fun minus(other: Point) = Point(x - other.x, y - other.y, z - other.z)

        infix fun distanceTo(other: Point) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

        fun allRotations(): Set<Point> {
            return setOf(
                Point(x, y, z), Point(x, -z, y), Point(x, -y, -z), Point(x, z, -y),
                Point(-x, -y, z), Point(-x, -z, -y), Point(-x, y, -z), Point(-x, z, y),
                Point(-z, x, -y), Point(y, x, -z), Point(z, x, y), Point(-y, x, z), Point(z, -x, -y), Point(y, -x, z), Point(-z, -x, y),
                Point(-y, -x, -z), Point(-y, -z, x), Point(z, -y, x), Point(y, z, x), Point(-z, y, x),
                Point(z, y, -x), Point(-y, z, -x), Point(-z, -y, -x), Point(y, -z, -x),
            )
        }
    }

}