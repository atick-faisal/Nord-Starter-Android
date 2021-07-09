package ai.andromeda.nordstarter.extensions

import android.content.Context
import android.widget.Toast
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    return ((resources.displayMetrics.density) * dp).roundToInt()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}