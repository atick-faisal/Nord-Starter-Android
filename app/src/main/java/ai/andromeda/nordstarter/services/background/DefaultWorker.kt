package ai.andromeda.nordstarter.services.background

import ai.andromeda.nordstarter.base.worker.BaseWorker
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DefaultWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters
) : BaseWorker(context, workerParameters) {
    override suspend fun performBackgroundWork() {

    }
}