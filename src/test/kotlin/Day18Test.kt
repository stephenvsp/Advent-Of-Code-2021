import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day18Test {

    val day18 = Day18()

    @Test
    fun `day 18 part 1`() {
        assertEquals(3763, day18.partOne())
    }

    @Test
    fun `day 18 part 2`() {
        assertEquals(4664, day18.partTwo())
    }
}