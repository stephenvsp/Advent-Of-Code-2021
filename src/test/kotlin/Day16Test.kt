import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day16Test {

    val day16 = Day16()

    @Test
    fun `day 16 part 1`() {
        assertEquals(873, day16.partOne())
    }

    @Test
    fun `day 16 part 2`() {
        assertEquals(402817863665, day16.partTwo())
    }
}