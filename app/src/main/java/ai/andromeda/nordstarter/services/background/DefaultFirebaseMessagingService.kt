package ai.andromeda.nordstarter.services.background

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class DefaultFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        inline fun subscribeToTopic(topic: String, crossinline onSuccess: () -> Unit) {
            FirebaseMessaging.getInstance()
                .subscribeToTopic(topic)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        Timber.e("firebase topic subscription was not successful!")
                    }
                }
        }
    }

    override fun onNewToken(token: String) {
        Timber.d("refreshed firebase token: $token")
        sendFcmTokenToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("firebase message receive from ${remoteMessage.from}")
        Timber.d("firebase message data ${remoteMessage.data}")
    }

    @Suppress("UNUSED_PARAMETER")
    private fun sendFcmTokenToServer(token: String) {
        // TODO: Send FCM token to backend
    }
}