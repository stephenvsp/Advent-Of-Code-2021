import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3Test {

    val day3 = Day3()

    @Test
    fun `day 3 part 1`() {
        assertEquals(2595824, day3.partOne())
    }

    @Test
    fun `day 3 part 2`() {
        assertEquals(2135254, day3.partTwo())
    }
}