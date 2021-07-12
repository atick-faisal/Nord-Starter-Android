package ai.andromeda.nordstarter.services.background

import com.google.firebase.messaging.FirebaseMessagingService
import timber.log.Timber

class DefaultFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Timber.d("refreshed firebase token: $token")
        sendFcmTokenToServer(token)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun sendFcmTokenToServer(token: String) {
        // TODO: Send FCM token to backend
    }
}