package tw.personal.jehuty.fsdation.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.personal.jehuty.fsdation.data.AppRepository
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemRequest
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse
import tw.personal.jehuty.fsdation.data.repository.CommodityRepository
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
    private val commodityRepository: CommodityRepository
) : AppUseCase<CommodityItemRequest, CommodityItemResponse>() {
    override suspend fun invoke(request: CommodityItemRequest): CommodityItemResponse =
        withContext(Dispatchers.IO) {
            var result = commodityRepository.getInfo(request)
            result
        }
}