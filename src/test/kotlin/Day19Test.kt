import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day19Test {

    val day19 = Day19()

    @Test
    fun `day 19 part 1`() {
        assertEquals(365, day19.partOne())
    }

    @Test
    fun `day 19 part 2`() {
        assertEquals(11060, day19.partTwo())
    }
}