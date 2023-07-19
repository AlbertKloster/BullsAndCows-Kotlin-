package bullscows

import kotlin.random.Random

fun main() {
    try {
        println("Input the length of the secret code:")
        val length = readln()
        if (!length.matches(Regex("\\d+")))
            throw RuntimeException("Error: \"$length\" isn't a valid number.")

        if (length.toInt() < 1)
            throw RuntimeException("Error: \"$length\" isn't a valid number.")

        println("Input the number of possible symbols in the code:")
        val symbols = readln()
        if (!symbols.matches(Regex("\\d+")))
            throw RuntimeException("Error: \"$symbols\" isn't a valid number.")

        if (symbols.toInt() > 36)
            throw RuntimeException("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).")

        if (length.toInt() > symbols.toInt())
            throw RuntimeException("Error: it's not possible to generate a code with a length of $length with $symbols unique symbols.")

        val secretCode = getRandomNumber(length.toInt(), symbols.toInt())
        val grader = Grader(secretCode)

        val possibleSymbols = if (symbols.toInt() < 11) {
            "(0-${symbols.toInt() - 1})"
        } else if (symbols.toInt() == 11) {
            "(0-9, a)"
        } else {
            "(0-9, a-${'a' + symbols.toInt() - 11})"
        }

        println("The secret is prepared: ${"*".repeat(length.toInt())} $possibleSymbols.")

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
            if (bulls == length.toInt()) break
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