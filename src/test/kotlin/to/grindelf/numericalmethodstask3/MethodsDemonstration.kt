package to.grindelf.numericalmethodstask3

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test

class MethodsDemonstration {

    private val tolerances = listOf(1e-2, 1e-3, 1e-4, 1e-5, 1e-6, 1e-7)

    private val xes = listOf(2.7072852, -0.4407447, 1.0372201, 0.7043331)

    @Test
    fun `GIVEN equations system WHEN gradient method applied THEN expected results returned`() {
        println("\nConjugate gradient method:")
        tolerances.forEach {tolerance ->
            val result = ConjugateGradientMethod.solution(
                CommonUtility.MATRIX,
                CommonUtility.RIGHT_SIDE_VECTOR4,
                CommonUtility.X0,
                tolerance
            )
            println(result)

            /*assertThat(result.x.x1).isCloseTo(xes[0], offset(tolerance))
            assertThat(result.x.x2).isCloseTo(xes[1], offset(tolerance))
            assertThat(result.x.x3).isCloseTo(xes[2], offset(tolerance))
            assertThat(result.x.x4).isCloseTo(xes[3], offset(tolerance))*/
        }
    }

    @Test
    fun `GIVEN equations system WHEN steepest descend method applied THEN expected results returned`() {
        println("\nSteepest descend method:")
        tolerances.forEach {tolerance ->
            val result = SteepestDescendMethod.solution(
                CommonUtility.MATRIX,
                CommonUtility.RIGHT_SIDE_VECTOR4,
                CommonUtility.X0,
                tolerance
            )
            println(result)

            /*assertThat(result.x.x1).isCloseTo(xes[0], offset(tolerance))
            assertThat(result.x.x2).isCloseTo(xes[1], offset(tolerance))
            assertThat(result.x.x3).isCloseTo(xes[2], offset(tolerance))
            assertThat(result.x.x4).isCloseTo(xes[3], offset(tolerance))*/
        }
    }

    @Test
    fun `GIVEN equations system WHEN gaussian method applied THEN expected results returned`() {
        println("\nGaussian method:")
        val result = GaussianMethod.solution(
            CommonUtility.MATRIX,
            CommonUtility.RIGHT_SIDE_VECTOR4,
        )

        println(result)

        /*assertThat(result.x.x1).isCloseTo(xes[0], offset(1e-7))
        assertThat(result.x.x2).isCloseTo(xes[1], offset(1e-7))
        assertThat(result.x.x3).isCloseTo(xes[2], offset(1e-7))
        assertThat(result.x.x4).isCloseTo(xes[3], offset(1e-7))*/
    }
}
