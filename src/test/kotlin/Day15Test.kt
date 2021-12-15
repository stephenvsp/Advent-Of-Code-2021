import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15Test {

    val day15 = Day15()

    @Test
    fun `day 15 part 1`() {
        assertEquals(613, day15.partOne())
    }

    @Test
    fun `day 15 part 2`() {
        day15.partTwo()
    }
}