import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2Test {

    val day2 = Day2()

    @Test
    fun `day 2 part 1`() {
        assertEquals(1507611, day2.partOne())
    }

    @Test
    fun `day 2 part 2`() {
        assertEquals(1880593125, day2.partTwo())
    }
}