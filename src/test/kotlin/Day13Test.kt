import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13Test {

    val day13 = Day13()

    @Test
    fun `day 13 part 1`() {
        assertEquals(731, day13.partOne())
    }

    @Test
    fun `day 13 part 2`() {
        assertEquals("ZKAUCFUC", day13.partTwo())
    }
}