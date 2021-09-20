package ai.andromeda.nordstarter.utils

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isUserNameValid(
        userName: String,
        minLength: Int = 3,
        maxLength: Int = 20
): Boolean {
    val userNameValidatorRegex = "^[A-Za-z]\\w*$minLength,$maxLength}$"
    val pattern = Pattern.compile(userNameValidatorRegex)
    return pattern.matcher(userName).matches()
}