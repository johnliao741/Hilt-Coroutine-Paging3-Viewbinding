package tw.personal.jehuty.fsdation.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CommodityItemRequest(
    val type:Int,
    val page:Int,
    val offset:Int

) :BaseResponse(){
    override fun toString(): String {
        return "CommodityItemRequest(type=$type, page=$page, offset=$offset)"
    }
}
