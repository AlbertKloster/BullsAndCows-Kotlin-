package bullscows

import kotlin.random.Random

fun main() {
    try {
        println("Input the length of the secret code:")
        val length = readln().toInt()

        println("Input the number of possible symbols in the code:")
        val symbols = readln().toInt()

        if (length > symbols)
            throw RuntimeException("Error: can't generate a secret number with a length of $length because there aren't enough unique digits.")

        val secretCode = getRandomNumber(length, symbols)
        val grader = Grader(secretCode)

        val possibleSymbols = if (symbols < 11) {
            "(0-${symbols - 1})"
        } else if (symbols == 11) {
            "(0-9, a)"
        } else {
            "(0-9, a-${'a' + symbols - 11})"
        }

        println("The secret is prepared: ${"*".repeat(length)} $possibleSymbols.")

        println("Okay, let's start a game!")
        var turn = 1

        while (true) {
            println("Turn $turn:")
            val code = readln()
            val bulls = grader.countBulls(code)
            val cows = grader.countCows(code)

            val gradeBulls = if (bulls > 0) "$bulls bull${if (bulls == 1) "" else "s"}${if (cows > 0) " and " else ""}" else ""
            val gradeCows = if (cows > 0) "$cows cow${if (cows == 1) "" else "s"}" else ""
            println("Grade: ${if (bulls == 0 && cows == 0) "None" else gradeBulls + gradeCows}")
            if (bulls == length) break
            turn++
        }
        println("Congratulations! You guessed the secret code.")

    } catch (e: RuntimeException) {
        println(e.message)
    }
}

private fun getRandomNumber(length: Int, symbols: Int): String {
    val builder = StringBuilder()
    while (builder.length < length) {
        val nextDigit = Random.nextInt(symbols)
        val symbol = if (nextDigit < 10) nextDigit.toString().first() else 'a' + nextDigit - 10
        if (builder.contains(symbol)) continue
        builder.append(symbol)
    }
    return builder.toString()
}