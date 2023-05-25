package to.grindelf.numericalmethodstask3

object ConjugateGradientMethod {
    fun solution(initialMatrix: Matrix44, rightSideVector: Vector4, x0: Vector4, tolerance: Double): Result {
        var counter = 0
        var x = x0.copy()
        var xNext = x.copy()
        var r: Vector4 = initialMatrix * x - rightSideVector
        var p = r.copy()
        var tau: Double
        var beta: Double
        do {
            x = xNext
            tau = (r * p) / ((initialMatrix * p) * p)
            xNext = x - (p * tau)
            r = initialMatrix * xNext - rightSideVector
            beta = ((initialMatrix * r) * p) / ((initialMatrix * p) * p)
            p = r - p * beta
            counter++
        } while (iterationIsPossible(xNext, x, r, tolerance))

        return Result(x = x, tolerance = tolerance, iterations = counter)
    }

    private fun iterationIsPossible(xNext: Vector4, x: Vector4, r: Vector4, tolerance: Double) =
        ((xNext - x).abs() >= tolerance || r.abs() >= tolerance)
}