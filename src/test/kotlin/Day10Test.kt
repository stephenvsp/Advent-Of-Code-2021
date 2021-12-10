import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day10Test {

    val day10 = Day10()

    @Test
    fun `day 10 part 1`() {
        assertEquals(392421, day10.partOne())
    }

    @Test
    fun `day 10 part 2`() {
        day10.partTwo()
    }
}