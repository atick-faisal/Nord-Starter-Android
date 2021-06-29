package ai.andromeda.nordstarter.services.background

import ai.andromeda.nordstarter.base.worker.BaseWorker
import android.content.Context
import androidx.work.WorkerParameters

class DefaultWorker(context: Context, workerParameters: WorkerParameters) :
    BaseWorker(context, workerParameters) {
    override suspend fun performBackgroundWork() {
        val a = listOf(1, 3, 4)
        val b = a[9] + 10

        database.clear()
    }
}