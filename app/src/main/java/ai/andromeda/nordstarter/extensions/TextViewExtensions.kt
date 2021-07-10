package ai.andromeda.nordstarter.extensions

import android.widget.TextView

fun TextView.getValue(): String {
    return text?.trim().toString()
}

fun TextView.clear() {
    text = ""
}