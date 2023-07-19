package bullscows

fun main() {
    try {
        println("Please, enter the secret code's length:")
        val digits = readln().toInt()

        if (digits > 10)
            throw RuntimeException("Error: can't generate a secret number with a length of $digits because there aren't enough unique digits.")

        val secretCode = getPseudoRandomNumber(digits)
        val grader = Grader(secretCode)

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
            if (bulls == digits) break
            turn++
        }
        println("Congratulations! You guessed the secret code.")

    } catch (e: RuntimeException) {
        println(e.message)
    }
}

private fun getPseudoRandomNumber(digits: Int): String {
    while (true) {
        val pseudoRandomNumber = System.nanoTime().toString().take(digits)
        if (hasUniqueDigits(pseudoRandomNumber)) return pseudoRandomNumber
    }
}

private fun hasUniqueDigits(number: String): Boolean {
    for (char in number) {
        if (number.count { it == char } > 1) return false
    }
    return true
}