import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {

    val day1 = Day1()


    @Test
    fun `day 1 part 1`() {
        assertEquals(1754, day1.partOne())
    }

    @Test
    fun `day 1 part 2`() {
        assertEquals(1789, day1.partTwo())
    }
}