package bullscows

class Grader(private val secretCode: String) {

    fun countBulls(code: String): Int {
        var count = 0
        for (i in code.indices) {
            if (code[i] == secretCode[i]) count++
        }
        return count
    }

    fun countCows(code: String): Int {
        var count = 0
        for (i in code.indices) {
            if (code[i] != secretCode[i] && secretCode.contains(code[i])) count++
        }
        return count
    }

}