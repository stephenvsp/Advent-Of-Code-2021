import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {

    val day5 = Day5()

    @Test
    fun `day 5 part 1`() {
        assertEquals(6397, day5.partOne())
    }

    @Test
    fun `day 5 part 2`() {
        assertEquals(22335, day5.partTwo())
    }
}