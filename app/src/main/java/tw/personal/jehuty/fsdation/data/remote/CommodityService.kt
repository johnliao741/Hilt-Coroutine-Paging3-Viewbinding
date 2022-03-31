package tw.personal.jehuty.fsdation.data.remote

import retrofit2.http.*
import tw.personal.jehuty.fsdation.dagger.module.ProvideCommodityService
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse

@ProvideCommodityService
interface CommodityService {

    @GET("info")
    suspend fun getInfo(@Query("type") type : Int,@Query("page") page:Int,@Query("offset") offset:Int): CommodityItemResponse

    @GET("commodity/detail")
    suspend fun getCommodityDetail(@Query("id") id : String ): CommodityDetailResponse
}

