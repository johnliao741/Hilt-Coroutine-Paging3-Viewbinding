package tw.personal.jehuty.fsdation.domain

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse
import tw.personal.jehuty.fsdation.data.repository.CommodityRepository
import java.lang.Exception
import javax.inject.Inject

class GetCommodityDetailUseCase @Inject constructor(
    private val commodityRepository: CommodityRepository
) : AppUseCase<String, CommodityDetailResponse>() {
    override suspend fun invoke(request: String): CommodityDetailResponse =
        withContext(Dispatchers.IO) {
            commodityRepository.getCommodityDetail(request)
        }
}