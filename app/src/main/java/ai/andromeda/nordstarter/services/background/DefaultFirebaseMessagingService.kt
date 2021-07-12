package ai.andromeda.nordstarter.services.background

import com.google.firebase.messaging.FirebaseMessagingService
import timber.log.Timber

class DefaultFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Timber.d("refreshed firebase token: $token")
    }
}