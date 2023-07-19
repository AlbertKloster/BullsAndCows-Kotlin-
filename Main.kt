package bullscows

fun main() {
    val secretCode = "9305"
    val grader = Grader(secretCode)

    val code = readln()
    val bulls = grader.countBulls(code)
    val cows = grader.countCows(code)

    val gradeBulls = if (bulls > 0) "$bulls bull(s)${if (cows > 0) " and " else ""}" else ""
    val gradeCows = if (cows > 0) "$cows cow(s)" else ""
    println("Grade: ${if (bulls == 0 && cows == 0) "None" else gradeBulls + gradeCows}. The secret code is $secretCode")
}