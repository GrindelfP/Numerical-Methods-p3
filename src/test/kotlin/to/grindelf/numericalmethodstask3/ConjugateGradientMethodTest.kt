package to.grindelf.numericalmethodstask3

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConjugateGradientMethodTest {

    private val tolerances = listOf(1e-2, 1e-3, 1e-4, 1e-5, 1e-6, 1e-7)

    private val xes = listOf(2.7072852, -0.4407447, 1.0372201, 0.7043331)

    @Test
    fun test1() {
        tolerances.forEach {tolerance ->
            val result = ConjugateGradientMethod.solution(
                CommonUtility.MATRIX,
                CommonUtility.RIGHT_SIDE_VECTOR4,
                CommonUtility.X0,
                tolerance
            )
            println(result)

            assertThat(result.x.x1).isCloseTo(xes[0], offset(tolerance))
            assertThat(result.x.x2).isCloseTo(xes[1], offset(tolerance))
            assertThat(result.x.x3).isCloseTo(xes[2], offset(tolerance))
            assertThat(result.x.x4).isCloseTo(xes[3], offset(tolerance))
        }
    }
}
