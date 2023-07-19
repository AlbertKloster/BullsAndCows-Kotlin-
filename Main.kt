package bullscows

fun main() {
    val digits = readln().toInt()

    if (digits > 10)
        println("Error: can't generate a secret number with a length of $digits because there aren't enough unique digits.")
    else
        println("The random secret number is ${getPseudoRandomNumber(digits)}.")

/*    val secretCode = "9305"
    val grader = Grader(secretCode)

    val code = readln()
    val bulls = grader.countBulls(code)
    val cows = grader.countCows(code)

    val gradeBulls = if (bulls > 0) "$bulls bull(s)${if (cows > 0) " and " else ""}" else ""
    val gradeCows = if (cows > 0) "$cows cow(s)" else ""
    println("Grade: ${if (bulls == 0 && cows == 0) "None" else gradeBulls + gradeCows}. The secret code is $secretCode")*/
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