package tw.personal.jehuty.fsdation.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import tw.personal.jehuty.fsdation.data.AppRepository
import tw.personal.jehuty.fsdation.data.remote.CommodityService
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemRequest
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse

import javax.inject.Inject

class CommodityRepository @Inject constructor(
    private val commodityService: CommodityService
) : AppRepository() {
    suspend fun getInfo(request: CommodityItemRequest): CommodityItemResponse =
        callApi {
            commodityService.getInfo(request.type,request.page,request.offset)
        }

    suspend fun getCommodityDetail(id: String): CommodityDetailResponse =
        callApi { commodityService.getCommodityDetail(id) }
}