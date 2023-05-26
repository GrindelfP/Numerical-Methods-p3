package to.grindelf.numericalmethodstask3

import kotlin.math.abs

object GaussianMethod {

    fun solution(matrix: Matrix44, vector: Vector4): Result {
        val a = arrayOf(
            doubleArrayOf(matrix.a1, matrix.a2, matrix.a3, matrix.a4, vector.x1),
            doubleArrayOf(matrix.b1, matrix.b2, matrix.b3, matrix.b4, vector.x2),
            doubleArrayOf(matrix.c1, matrix.c2, matrix.c3, matrix.c4, vector.x3),
            doubleArrayOf(matrix.d1, matrix.d2, matrix.d3, matrix.d4, vector.x4)
        )

        val n = a.size

        for (i in 0 until n) {
            var max = i
            for (j in i + 1 until n) {
                if (abs(a[j][i]) > abs(a[max][i])) {
                    max = j
                }
            }

            if (i != max) {
                for (j in 0..n) {
                    val temp = a[i][j]
                    a[i][j] = a[max][j]
                    a[max][j] = temp
                }
            }

            for (j in i + 1 until n) {
                val factor = a[j][i] / a[i][i]
                for (k in i..n) {
                    a[j][k] -= a[i][k] * factor
                }
            }
        }

        val x = DoubleArray(n)
        for (i in n - 1 downTo 0) {
            x[i] = a[i][n] / a[i][i]
            for (j in 0 until i) {
                a[j][n] -= a[j][i] * x[i]
            }
        }

        return Result(0, Vector4(x[0], x[1], x[2], x[3]), 0.0)
    }
}