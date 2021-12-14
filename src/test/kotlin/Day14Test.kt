import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
internal class Day14Test {

    val day14 = Day14()

    @Test
    fun `day 14 part 1`() {
        assertEquals(3408, day14.partOne())
    }

    @Test
    fun `day 14 part 2`() {
        assertEquals(3724343376942L, day14.partTwo())
    }
}