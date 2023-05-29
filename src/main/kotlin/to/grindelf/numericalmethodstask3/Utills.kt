package to.grindelf.numericalmethodstask3

import kotlin.math.sqrt

object CommonUtility {
    val MATRIX = Matrix44(
        1.0, -0.22, 0.11, -0.31,
        -0.38, 1.0, 0.12, -0.22,
        -0.11, -0.23, 1.0, 0.51,
        -0.17, 0.21, -0.31, 1.0
    ) * Matrix44(
        1.0, -0.22, 0.11, -0.31,
        -0.38, 1.0, 0.12, -0.22,
        -0.11, -0.23, 1.0, 0.51,
        -0.17, 0.21, -0.31, 1.0
    ).transpose()

    val X0 = Vector4(0.0, 0.0, 0.0, 0.0)

    val RIGHT_SIDE_VECTOR4 = Matrix44(
        1.0, -0.22, 0.11, -0.31,
        -0.38, 1.0, 0.12, -0.22,
        -0.11, -0.23, 1.0, 0.51,
        -0.17, 0.21, -0.31, 1.0
    ).transpose() * Vector4(2.7, -1.5, 1.2, -0.17)
}

data class Matrix44(
    val a1: Double,
    val a2: Double,
    val a3: Double,
    val a4: Double,
    val b1: Double,
    val b2: Double,
    val b3: Double,
    val b4: Double,
    val c1: Double,
    val c2: Double,
    val c3: Double,
    val c4: Double,
    val d1: Double,
    val d2: Double,
    val d3: Double,
    val d4: Double
) {
    operator fun times(other: Vector4) =
        Vector4(
            this.a1 * other.x1 + this.a2 * other.x2 + this.a3 * other.x3 + this.a4 * other.x4,
            this.b1 * other.x1 + this.b2 * other.x2 + this.b3 * other.x3 + this.b4 * other.x4,
            this.c1 * other.x1 + this.c2 * other.x2 + this.c3 * other.x3 + this.c4 * other.x4,
            this.d1 * other.x1 + this.d2 * other.x2 + this.d3 * other.x3 + this.d4 * other.x4
        )

    operator fun times(other: Matrix44) =
        Matrix44(
            this.a1 * other.a1 + this.a2 * other.b1 + this.a3 * other.c1 + this.a4 * other.d1,
            this.a1 * other.a2 + this.a2 * other.b2 + this.a3 * other.c2 + this.a4 * other.d2,
            this.a1 * other.a3 + this.a2 * other.b3 + this.a3 * other.c3 + this.a4 * other.d3,
            this.a1 * other.a4 + this.a2 * other.b4 + this.a3 * other.c4 + this.a4 * other.d4,
            this.b1 * other.a1 + this.b2 * other.b1 + this.b3 * other.c1 + this.b4 * other.d1,
            this.b1 * other.a2 + this.b2 * other.b2 + this.b3 * other.c2 + this.b4 * other.d2,
            this.b1 * other.a3 + this.b2 * other.b3 + this.b3 * other.c3 + this.b4 * other.d3,
            this.b1 * other.a4 + this.b2 * other.b4 + this.b3 * other.c4 + this.b4 * other.d4,
            this.c1 * other.a1 + this.c2 * other.b1 + this.c3 * other.c1 + this.c4 * other.d1,
            this.c1 * other.a2 + this.c2 * other.b2 + this.c3 * other.c2 + this.c4 * other.d2,
            this.c1 * other.a3 + this.c2 * other.b3 + this.c3 * other.c3 + this.c4 * other.d3,
            this.c1 * other.a4 + this.c2 * other.b4 + this.c3 * other.c4 + this.c4 * other.d4,
            this.d1 * other.a1 + this.d2 * other.b1 + this.d3 * other.c1 + this.d4 * other.d1,
            this.d1 * other.a2 + this.d2 * other.b2 + this.d3 * other.c2 + this.d4 * other.d2,
            this.d1 * other.a3 + this.d2 * other.b3 + this.d3 * other.c3 + this.d4 * other.d3,
            this.d1 * other.a4 + this.d2 * other.b4 + this.d3 * other.c4 + this.d4 * other.d4
        )

    fun transpose(): Matrix44 =
        Matrix44(
            a1 = this.a1, a2 = this.b1, a3 = this.c1, a4 = this.d1,
            b1 = this.a2, b2 = this.b2, b3 = this.c2, b4 = this.d2,
            c1 = this.a3, c2 = this.b3, c3 = this.c3, c4 = this.d3,
            d1 = this.a4, d2 = this.b4, d3 = this.c4, d4 = this.d4
        )
}

data class Result(val iterations: Int, val x: Vector4, val tolerance: Double) {

    private val results = listOf(
        String.format("%.7f", x.x1),
        String.format("%.7f", x.x2),
        String.format("%.7f", x.x3),
        String.format("%.7f", x.x4)
    )

    override fun toString(): String {
        var string = when {
            iterations == 0 && tolerance == 0.0 -> ""
            else -> "Tolerance: $tolerance "
        }
        results.forEachIndexed { index, result ->
            string += "x${index + 1} = $result, "
        }
        string += when {
            iterations == 0 && tolerance == 0.0 -> ""
            else -> "Iterations: $iterations"
        }

        return string
    }
}

data class Vector4(val x1: Double, val x2: Double, val x3: Double, val x4: Double) {
    operator fun minus(other: Vector4) =
        Vector4(this.x1 - other.x1, this.x2 - other.x2, this.x3 - other.x3, this.x4 - other.x4)

    operator fun times(other: Vector4): Double =
        this.x1 * other.x1 + this.x2 * other.x2 + this.x3 * other.x3 + this.x4 * other.x4

    operator fun times(other: Double) = Vector4(this.x1 * other, this.x2 * other, this.x3 * other, this.x4 * other)

    fun abs() = sqrt(this.x1 * this.x1 + this.x2 * this.x2 + this.x3 * this.x3 + this.x4 * this.x4)
}
