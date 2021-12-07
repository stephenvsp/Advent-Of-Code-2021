import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7Test {

    val day7 = Day7()

    @Test
    fun `day 7 part 1`() {
        assertEquals(335330, day7.partOne())
    }

    @Test
    fun `day 7 part 2`() {
        assertEquals(92439766, day7.partTwo())
    }
}