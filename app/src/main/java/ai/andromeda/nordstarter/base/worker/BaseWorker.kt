package ai.andromeda.nordstarter.base.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class BaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    abstract suspend fun performBackgroundWork()

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                performBackgroundWork()
                Result.success()
            } catch (exception: Exception) {
                Timber.e(exception, "error performing background task!")
                Result.retry()
            }
        }
    }
}