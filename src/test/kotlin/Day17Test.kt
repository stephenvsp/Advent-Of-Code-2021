import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17Test {

    val day17 = Day17()

    @Test
    fun `day 17 part 1`() {
        assertEquals(4560, day17.partOne())
    }

    @Test
    fun `day 17 part 2`() {
        assertEquals(3344, day17.partTwo())
    }
}