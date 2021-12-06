import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6Test {

    val day6 = Day6()

    @Test
    fun `day 6 part 1`() {
        assertEquals(358214, day6.partOne())
    }

    @Test
    fun `day 6 part 2`() {
        assertEquals(1622533344325, day6.partTwo())
    }
}