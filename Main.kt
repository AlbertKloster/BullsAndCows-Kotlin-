package bullscows

import kotlin.random.Random

fun main() {
    val secretCode = getCode(4)
    println("The secret code is prepared: ****.")

    for (turn in 1..4) {
        println("Turn $turn. Answer:")
        val code = if (turn == 4) secretCode else getCode(4)
        println(code)
        val bulls = countBulls(secretCode, code)
        val cows = countCows(secretCode, code)
        val gradeBulls = if (bulls > 0) {
            bulls.toString() + " bull" + if (bulls == 1) "" else "s"
            if (cows > 0) " and " else ""
        } else ""
        val gradeCows = if (cows > 0) {
            cows.toString() + " cow" + if (cows == 1) "" else "s"
        } else ""
        println("Grade: ${if (bulls == 0 && cows == 0) "None" else gradeBulls + gradeCows}.")
    }

    println("Congrats! The secret code is $secretCode.")
}

private fun countBulls(secretCode: String, code: String): Int {
    var count = 0
    for (i in code.indices) {
        if (code[i] == secretCode[i]) count++
    }
    return count
}

private fun countCows(secretCode: String, code: String): Int {
    var count = 0
    for (i in code.indices) {
        if (code[i] != secretCode[i] && secretCode.contains(code[i])) count++
    }
    return count
}

private fun getCode(digits: Int): String {
    val builder = StringBuilder()
    repeat(digits) { builder.append(Random.nextInt(10)) }
    return builder.toString()
}