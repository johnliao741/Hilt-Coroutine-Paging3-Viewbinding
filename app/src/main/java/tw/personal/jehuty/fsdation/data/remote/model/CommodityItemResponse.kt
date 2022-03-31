package tw.personal.jehuty.fsdation.data.remote.model

import com.squareup.moshi.JsonClass
import tw.personal.jehuty.fsdation.util.IntToCommodityType

@JsonClass(generateAdapter = true)
class CommodityItemResponse(
    val commodityList: List<CommodityItem>

) :BaseResponse(){
    override fun toString(): String {
        return "CommodityItemResponse(commodityList=$commodityList)"
    }
}
@JsonClass(generateAdapter = true)
class CommodityItem(
    val id:String,
    val url:String,
    val name:String,
    val price:Int,
    @IntToCommodityType val status:CommodityType

) {
    override fun toString(): String {
        return "CommodityItem(id='$id', url='$url', name='$name', price=$price, status=$status)"
    }
}
/**
 * typeValue => 0 : 貨到通知我 1 : 加入購物車
 *
 * **/
enum class CommodityType(
    val btnString:String,
    val typeValue:Int,
){
    DeliveryNotice("貨到通知我",0),
    AddShoppingCar("加入購物車",1)
}