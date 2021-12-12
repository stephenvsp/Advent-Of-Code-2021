import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day11Test {

    val day11 = Day11()

    @Test
    fun `day 11 part 1`() {
        assertEquals(1749, day11.partOne())
    }

    @Test
    fun `day 11 part 2`() {
        assertEquals(285, day11.partTwo())
    }
}