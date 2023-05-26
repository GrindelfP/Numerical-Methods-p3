package to.grindelf.numericalmethodstask3

object SteepestDescendMethod {

    fun solution(initialMatrix: Matrix44, rightSideVector: Vector4, x0: Vector4, tolerance: Double): Result {
        var counter = 0
        var x = x0.copy()
        var xNext = x.copy()
        var r: Vector4 = initialMatrix * x - rightSideVector
        var tau: Double
        do {
            x = xNext
            tau = (r * r) / ((initialMatrix * r) * r)
            xNext = x - (r * tau)
            r = initialMatrix * xNext - rightSideVector
            counter++
        } while (iterationIsPossible(xNext, x, r, tolerance))

        return Result(x = x, tolerance = tolerance, iterations = counter)
    }
    private fun iterationIsPossible(xNext: Vector4, x: Vector4, r: Vector4, tolerance: Double) =
        ((xNext - x).abs() >= tolerance || r.abs() >= tolerance)
}