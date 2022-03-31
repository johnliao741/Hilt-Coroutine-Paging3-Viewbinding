package tw.personal.jehuty.fsdation.data

import android.util.Log
import androidx.paging.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.personal.jehuty.fsdation.data.remote.model.BaseResponse

abstract class AppRepository {
    suspend fun <T : Any> callApi(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend () -> T
    ) = withContext(dispatcher) {
        val response = action.invoke()
        if (response is BaseResponse && response.IS_VALID != "Y")
            throw Exception(response.MSG)
        response
    }
}