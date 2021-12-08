import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8Test {

    val day8 = Day8()
    @Test
    fun `day 8 part 1`() {
        assertEquals(288, day8.partOne())
    }

    @Test
    fun `day 8 part 2`() {
        assertEquals(940724, day8.partTwo())
    }
}