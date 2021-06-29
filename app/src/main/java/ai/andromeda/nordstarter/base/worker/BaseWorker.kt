package ai.andromeda.nordstarter.base.worker

import ai.andromeda.nordstarter.utils.LOG_TAG
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

abstract class BaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    abstract fun performBackgroundWork()

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                performBackgroundWork()
                Result.success()
            } catch (exception: Exception) {
                Log.e(LOG_TAG, "Error while performing background work", exception)
                Result.retry()
            }
        }
    }
}