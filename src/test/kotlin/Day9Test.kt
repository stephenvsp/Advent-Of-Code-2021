import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day9Test {

    val day9 = Day9()

    @Test
    fun `day 9 part 1`() {
        assertEquals(417, day9.partOne())
    }

    @Test
    fun `day 9 part 2`() {
        assertEquals(1148965, day9.partTwo())
    }
}